package cn.chenxins.authorization.manager.impl;


import cn.chenxins.authorization.manager.TokenManager;
import cn.chenxins.authorization.model.TokenModel;
import cn.chenxins.exception.TokenException;
import cn.chenxins.utils.ConstConfig;
import cn.chenxins.utils.JsonUtils;
import cn.chenxins.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通过Redis存储和验证token的实现类
 * @see cn.chenxins.authorization.manager.TokenManager
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {


    @Autowired
    private RedisOperator redis;


    public void createToken(TokenModel tokenModel) throws TokenException {

        String key=tokenModel.getToken();

        String tokenSerial = JsonUtils.objectToJson(tokenModel);

        //存储到redis并设置过期时间
        redis.set(key,tokenSerial,ConstConfig.TOKEN_EXPIRES_HOUR*3600);

    }


    public TokenModel checkToken(String authentication)throws Exception {
        TokenModel tokenModel=null;
        if (authentication == null || "".equals(authentication.trim())) {
            return null;
        }
//        String token = redisTemplate.boundValueOps(model.getUserId()).get();
        String token = redis.get(authentication);
        if (token == null ) {
            return null;
        } else {
            tokenModel=JsonUtils.jsonToPojo(token,TokenModel.class);
            if (tokenModel==null) {
                return null;
            }
            if (!tokenModel.getToken().equals(authentication)){
                return null;
            }
        }

        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.expire(authentication,ConstConfig.TOKEN_EXPIRES_HOUR*3600);
        return tokenModel;
    }

    public void deleteToken(String tokenKey) {
//       redisTemplate.delete(userId);
        redis.del(tokenKey);
    }
}
