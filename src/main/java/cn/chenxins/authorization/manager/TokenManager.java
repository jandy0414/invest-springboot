package cn.chenxins.authorization.manager;

import cn.chenxins.authorization.model.TokenModel;
import cn.chenxins.exception.TokenException;

/**
 * 对Token进行操作的接口
 * @author ScienJus
 * @date 2015/7/31.
 */
public interface TokenManager {

    /**
     * 创建一个token关联上指定用户
     */
    public void createToken(TokenModel tokenModel) throws TokenException;

    /**
     * 检查token是否有效
     * @param tokenKey
     * @return 是否有效
     */
    public TokenModel checkToken(String tokenKey)throws Exception;



    /**
     * 清除token
     * @param tokenKey 登录用户的id
     */
    public void deleteToken(String tokenKey);

}
