package cn.chenxins.authorization.interceptor;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.authorization.manager.TokenManager;
import cn.chenxins.authorization.model.AuthResJson;
import cn.chenxins.authorization.model.TokenModel;
import cn.chenxins.utils.ConstConfig;
import cn.chenxins.utils.JsonUtils;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.List;

@Component
public class AuthorizationInterceptor   extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果方法没有注明了Authorization，则直接返回通过
        if (method.getAnnotation(Authorization.class)==null)
        {
              return true;
        }
        try {
            //从header中得到token
            String authorization = request.getHeader(ConstConfig.AUTHORIZATION);
            String tokens=getBasicAuthToken(authorization);
            if (tokens==null)
            {
                return returnResponseMsg(response, HttpServletResponse.SC_FORBIDDEN,"无法正常获取TOKEN");
            }
            //验证token
            TokenModel model = manager.checkToken(tokens);
            if (model == null){
                return returnResponseMsg(response, HttpServletResponse.SC_FORBIDDEN,"无法正常获取TOKEN对象");
            }
            if (model.getScope()==2)
            {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(ConstConfig.CURRENT_USER_TOKEN, model);
                return true;
            } else {   //需要验证是否有访问资源的权限
                String requestUrl=request.getRequestURI();
                List<AuthResJson> lis=model.getRes();
                if (requestUrl!=null&& !"".equals(requestUrl) && lis!=null)
                {
                    if(isUrlInResList(lis,requestUrl)){
                        //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                        request.setAttribute(ConstConfig.CURRENT_USER_TOKEN, model);
                        return true;
                    }
                }

                // 未完成，待res写完，才写。
                return returnResponseMsg(response, HttpServletResponse.SC_METHOD_NOT_ALLOWED,"无权限访问该资源");
            }


        }catch (Exception e) {
            e.printStackTrace();
            return returnResponseMsg(response, HttpServletResponse.SC_FORBIDDEN,"处理授权出现异常");
        }
    }

    private boolean isUrlInResList(List<AuthResJson> list, String requestUrl){
        boolean isIn=false;
        AuthResJson tmp;
        for(int i=0;i<list.size();i++){
            tmp=list.get(i);
            if (tmp.getUrl()!=null && requestUrl.equals(tmp.getUrl())){
                isIn=true;
                break;
            }
        }

        return isIn;
    }

    private String getBasicAuthToken(String authss) throws Exception{
        if (authss==null)
        {
            return null;
        }
        String[] aa=authss.split("\\ ");
        if (aa.length!=2)
            return null;
        String baseToken=aa[1];
        String ss= new String(Base64.getDecoder().decode(baseToken),"utf-8");
        ss=ss.substring(0,ss.length()-1);
        return ss;
    }

    private boolean returnResponseMsg(HttpServletResponse response, int code, String msg) {
        try {
            response.setStatus(code);
            ResultJson outJson=ResultJson.Forbidden(msg);

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));

            writer.write(JsonUtils.objectToJson(outJson));
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            return false;
        }
    }
}
