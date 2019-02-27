package cn.chenxins.invest.model.entity;

import cn.chenxins.exception.ParamValueException;

import javax.persistence.*;

@Table(name = "invest_report")
public class InvestReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 交易ID
     */
    @Column(name = "trade_id")
    private Integer tradeId;

    /**
     * 报告日
     */
    private String day;

    /**
     * 浮盈结果
     */
    private String eventuate;

    /**
     * 期末净值
     */
    @Column(name = "end_value")
    private Float endValue;

    @Column(name = "create_time")
    private String createTime;

    private String remark;

    public static void ValidRequiredAdd(InvestReport tmp) throws ParamValueException {
        if (tmp.getTradeId()==null ){
            throw new ParamValueException("交易记录是必填项，不能为空");
        }
        if (tmp.getDay() ==null || "".equals(tmp.getDay().trim())){
            throw new ParamValueException("报告日是必填项，不能为空");
        }
        if (tmp.getEndValue()==null ){
            throw new ParamValueException("期末净值为是必填项，不能为空");
        }

    }
    public static void ValidRequiredUpd(InvestReport tmp) throws ParamValueException {
        if (tmp.getId()==null ){
            throw new ParamValueException("ID是必填项，不能为空");
        }
        if (tmp.getTradeId()==null ){
            throw new ParamValueException("交易记录是必填项，不能为空");
        }
        if (tmp.getDay() ==null || "".equals(tmp.getDay().trim())){
            throw new ParamValueException("报告日是必填项，不能为空");
        }
        if (tmp.getEndValue()==null ){
            throw new ParamValueException("期末净值为是必填项，不能为空");
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
     * 获取交易ID
     *
     * @return trade_id - 交易ID
     */
    public Integer getTradeId() {
        return tradeId;
    }

    /**
     * 设置交易ID
     *
     * @param tradeId 交易ID
     */
    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取报告日
     *
     * @return day - 报告日
     */
    public String getDay() {
        return day;
    }

    /**
     * 设置报告日
     *
     * @param day 报告日
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * 获取浮盈结果
     *
     * @return eventuate - 浮盈结果
     */
    public String getEventuate() {
        return eventuate;
    }

    /**
     * 设置浮盈结果
     *
     * @param eventuate 浮盈结果
     */
    public void setEventuate(String eventuate) {
        this.eventuate = eventuate;
    }

    /**
     * 获取期末净值
     *
     * @return end_value - 期末净值
     */
    public Float getEndValue() {
        return endValue;
    }

    /**
     * 设置期末净值
     *
     * @param endValue 期末净值
     */
    public void setEndValue(Float endValue) {
        this.endValue = endValue;
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
}