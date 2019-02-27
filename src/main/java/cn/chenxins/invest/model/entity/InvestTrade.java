package cn.chenxins.invest.model.entity;

import cn.chenxins.exception.ParamValueException;

import javax.persistence.*;

@Table(name = "invest_trade")
public class InvestTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作方向-数据字典
     */
    private Integer operate;

    /**
     * 交易申请日
     */
    @Column(name = "apply_day")
    private String applyDay;

    /**
     * 交易确认日
     */
    @Column(name = "confirm_day")
    private String confirmDay;

    /**
     * 交易金额
     */
    private Float mount;

    /**
     * 渠道-数据字典
     */
    private String way;

    @Column(name = "product_code")
    private String productCode;

    /**
     * 数量/份额
     */
    private Float count;

    /**
     * 确认后单位净值
     */
    @Column(name = "unit_price")
    private Float unitPrice;

    private Float fee;

    private String remark;

    @Column(name = "create_time")
    private String createTime;

    public static void ValidRequiredAdd(InvestTrade tmp) throws ParamValueException {
        if (tmp.getOperate()==null ){
            throw new ParamValueException("操作行为是必填项，不能为空");
        }
        if (tmp.getApplyDay() ==null || "".equals(tmp.getApplyDay().trim())){
            throw new ParamValueException("申请日是必填项，不能为空");
        }
        if (tmp.getMount()==null ){
            throw new ParamValueException("交易金额为是必填项，不能为空");
        }
        if (tmp.getProductCode() ==null || "".equals(tmp.getProductCode().trim())){
            throw new ParamValueException("产品编码是必填项，不能为空");
        }


    }
    public static void ValidRequiredUpd(InvestTrade tmp) throws ParamValueException {
        if (tmp.getId()==null ){
            throw new ParamValueException("ID是必填项，不能为空");
        }
        if (tmp.getOperate()==null ){
            throw new ParamValueException("操作行为是必填项，不能为空");
        }
        if (tmp.getApplyDay() ==null || "".equals(tmp.getApplyDay().trim())){
            throw new ParamValueException("申请日是必填项，不能为空");
        }
        if (tmp.getMount()==null ){
            throw new ParamValueException("交易金额为是必填项，不能为空");
        }
        if (tmp.getProductCode() ==null || "".equals(tmp.getProductCode().trim())){
            throw new ParamValueException("产品编码是必填项，不能为空");
        }

    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取操作方向-数据字典
     *
     * @return operate - 操作方向-数据字典
     */
    public Integer getOperate() {
        return operate;
    }

    /**
     * 设置操作方向-数据字典
     *
     * @param operate 操作方向-数据字典
     */
    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    /**
     * 获取交易申请日
     *
     * @return apply_day - 交易申请日
     */
    public String getApplyDay() {
        return applyDay;
    }

    /**
     * 设置交易申请日
     *
     * @param applyDay 交易申请日
     */
    public void setApplyDay(String applyDay) {
        this.applyDay = applyDay;
    }

    /**
     * 获取交易确认日
     *
     * @return confirm_day - 交易确认日
     */
    public String getConfirmDay() {
        return confirmDay;
    }

    /**
     * 设置交易确认日
     *
     * @param confirmDay 交易确认日
     */
    public void setConfirmDay(String confirmDay) {
        this.confirmDay = confirmDay;
    }

    /**
     * 获取交易金额
     *
     * @return mount - 交易金额
     */
    public Float getMount() {
        return mount;
    }

    /**
     * 设置交易金额
     *
     * @param mount 交易金额
     */
    public void setMount(Float mount) {
        this.mount = mount;
    }

    /**
     * 获取渠道-数据字典
     *
     * @return way - 渠道-数据字典
     */
    public String getWay() {
        return way;
    }

    /**
     * 设置渠道-数据字典
     *
     * @param way 渠道-数据字典
     */
    public void setWay(String way) {
        this.way = way;
    }

    /**
     * @return product_code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取数量/份额
     *
     * @return count - 数量/份额
     */
    public Float getCount() {
        return count;
    }

    /**
     * 设置数量/份额
     *
     * @param count 数量/份额
     */
    public void setCount(Float count) {
        this.count = count;
    }

    /**
     * 获取确认后单位净值
     *
     * @return unit_price - 确认后单位净值
     */
    public Float getUnitPrice() {
        return unitPrice;
    }

    /**
     * 设置确认后单位净值
     *
     * @param unitPrice 确认后单位净值
     */
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return fee
     */
    public Float getFee() {
        return fee;
    }

    /**
     * @param fee
     */
    public void setFee(Float fee) {
        this.fee = fee;
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
}