package cn.chenxins.invest.model.entity;

import cn.chenxins.exception.ParamValueException;
import cn.chenxins.utils.StringUtil;

import javax.persistence.*;

@Table(name = "invest_product")
public class InvestProduct {
    @Id
    private String code;

    private String name;

    private String descri;

    /**
     * 是否是场内编码 1是
     */
    @Column(name = "is_in_code")
    private Integer isInCode;

    /**
     * 发行机构
     */
    private String own;

    private String remark;

    @Column(name = "create_time")
    private String createTime;

    /**
     * 产品状态
     */
    private Integer status;


    public static void ValidRequiredAdd(InvestProduct tmp) throws ParamValueException {
        if (!StringUtil.isNotBlank(tmp.getCode())){
            throw new ParamValueException("产品编码不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getName())){
            throw new ParamValueException("产品名称不能为空");
        }

    }
    public static void ValidRequiredUpd(InvestProduct tmp) throws ParamValueException {
        if (!StringUtil.isNotBlank(tmp.getCode())){
            throw new ParamValueException("产品编码不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getName())){
            throw new ParamValueException("产品名称不能为空");
        }

    }
    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return descri
     */
    public String getDescri() {
        return descri;
    }

    /**
     * @param descri
     */
    public void setDescri(String descri) {
        this.descri = descri;
    }

    /**
     * 获取是否是场内编码 1是
     *
     * @return is_in_code - 是否是场内编码 1是
     */
    public Integer getIsInCode() {
        return isInCode;
    }

    /**
     * 设置是否是场内编码 1是
     *
     * @param isInCode 是否是场内编码 1是
     */
    public void setIsInCode(Integer isInCode) {
        this.isInCode = isInCode;
    }

    /**
     * 获取发行机构
     *
     * @return own - 发行机构
     */
    public String getOwn() {
        return own;
    }

    /**
     * 设置发行机构
     *
     * @param own 发行机构
     */
    public void setOwn(String own) {
        this.own = own;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return create_time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取产品状态
     *
     * @return status - 产品状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置产品状态
     *
     * @param status 产品状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}