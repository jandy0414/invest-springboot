package cn.chenxins.authorization.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * @author ScienJus
 * @date 2015/7/31.
 */
public class TokenModel {

    //用户id
    private long userId;


    //随机生成的uuid
    private String token;

    private Integer roleId;

    private Integer orgId;

    private Integer scope;

    private String userName;

    private String avatar;

    private String nickName;

    private String roleName;

    private String orgName;

    private List<AuthResJson> res;

    public TokenModel() {
    }

    public TokenModel(long userId, String token, Integer roleId, Integer orgId, Integer scope) {
        this.userId = userId;
        this.token = token;
        this.roleId = roleId;
        this.orgId = orgId;
        this.scope = scope;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<AuthResJson> getRes() {
        return res;
    }

    public void setRes(List<AuthResJson> res) {
        this.res = res;
    }

    public void appendRes(List<AuthResJson> res2){
        if ( this.res==null){
            this.res=new ArrayList<>();
        }
        for(int i=0; i<res2.size();i++){
            this.res.add(res2.get(i));
        }
    }
}
