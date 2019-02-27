package cn.chenxins.invest.model.entity;

import cn.chenxins.exception.ParamValueException;
import cn.chenxins.utils.StringUtil;

import javax.persistence.*;

@Table(name = "invest_assets")
public class InvestAssets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "report_day")
    private String reportDay;

    @Column(name = "start_value")
    private Float startValue;

    private Float count;

    @Column(name = "unit_price")
    private Float unitPrice;

    @Column(name = "end_value")
    private Float endValue;

    /**
     * 盈利状态：1盈利，0亏损
     */
    private Integer eventuate;

    @Column(name = "create_time")
    private String createTime;


    public static void ValidRequiredAdd(InvestAssets tmp) throws ParamValueException {
        if (!StringUtil.isNotBlank(tmp.getProductCode())){
            throw new ParamValueException("产品编码不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getReportDay())){
            throw new ParamValueException("报告日期不能为空");
        }
        if (tmp.getEndValue()==null) {
            throw new ParamValueException("期末净值不能为空");
        }

    }
    public static void ValidRequiredRegen(InvestAssets tmp) throws ParamValueException {
        if (tmp.getId() == null) {
            throw new ParamValueException("ID不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getProductCode())){
            throw new ParamValueException("产品编码不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getReportDay())){
            throw new ParamValueException("评估日期不能为空");
        }


    }
    public static void ValidRequiredUpd(InvestAssets tmp) throws ParamValueException {
        if (tmp.getId() == null) {
            throw new ParamValueException("ID不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getProductCode())){
            throw new ParamValueException("产品编码不能为空");
        }
        if (!StringUtil.isNotBlank(tmp.getReportDay())){
            throw new ParamValueException("报告日期不能为空");
        }
        if (tmp.getEndValue()==null) {
            throw new ParamValueException("期末净值不能为空");
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
     * @return report_day
     */
    public String getReportDay() {
        return reportDay;
    }

    /**
     * @param reportDay
     */
    public void setReportDay(String reportDay) {
        this.reportDay = reportDay;
    }

    /**
     * @return start_value
     */
    public Float getStartValue() {
        return startValue;
    }

    /**
     * @param startValue
     */
    public void setStartValue(Float startValue) {
        this.startValue = startValue;
    }

    /**
     * @return count
     */
    public Float getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Float count) {
        this.count = count;
    }

    /**
     * @return unit_price
     */
    public Float getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice
     */
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return end_value
     */
    public Float getEndValue() {
        return endValue;
    }

    /**
     * @param endValue
     */
    public void setEndValue(Float endValue) {
        this.endValue = endValue;
    }

    /**
     * 获取盈利状态：1盈利，0亏损
     *
     * @return eventuate - 盈利状态：1盈利，0亏损
     */
    public Integer getEventuate() {
        return eventuate;
    }

    /**
     * 设置盈利状态：1盈利，0亏损
     *
     * @param eventuate 盈利状态：1盈利，0亏损
     */
    public void setEventuate(Integer eventuate) {
        this.eventuate = eventuate;
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