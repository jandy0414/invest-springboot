package cn.chenxins.utils;

public class ResultJson {

    private Integer errCode;
    private String msg;
    private Object data;

    public static ResultJson Sucess(){
        return new ResultJson(0,"操作成功",null);
    }
    public static ResultJson Sucess(Object data){
        return new ResultJson(0,"操作成功",data);
    }

    public static ResultJson DelSucess(){
        return new ResultJson(0,"删除操作成功",null);
    }

    public static ResultJson DelSucess(Object data){
        return new ResultJson(0,"删除操作成功",data);
    }

    public static ResultJson ServerError(){
        return new ResultJson(9999,"系统错误，请联系开发人员！",null);
    }

    public static ResultJson ParameterError(){
        return new ResultJson(1000,"请求的参数出错了",null);
    }

    public static ResultJson ParameterException(String emsg, Object data){
        return new ResultJson(1001,emsg,data);
    }

    public static ResultJson TokenRedisException(){
        return new ResultJson(1005,"token存储redis时出错",null);
    }

    public static ResultJson BussinessException(String emsg){
        return new ResultJson(1002,emsg,null);
    }


    public static ResultJson Forbidden(String msg){
        return new ResultJson(1003,msg,null);
    }


    public static ResultJson NotFound(){
        return new ResultJson(1004,"请求内容未找到",null);
    }




    public ResultJson(Integer errCode, String msg, Object data) {
        this.errCode = errCode;
        this.msg = msg;
        this.data=data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
