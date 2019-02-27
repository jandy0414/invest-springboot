package cn.chenxins.invest.model.entity;

import cn.chenxins.exception.ParamValueException;

import javax.persistence.*;

@Table(name = "invest_valuation")
public class InvestValuation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "val_day")
    private String valDay;

    /**
     * 场内编码
     */
    @Column(name = "in_code")
    private String inCode;

    /**
     * 场外编码
     */
    @Column(name = "out_code")
    private String outCode;

    private String name;

    private String descri;

    /**
     * 单位净值
     */
    @Column(name = "book_value")
    private Float bookValue;

    /**
     * 市净率
     */
    @Column(name = "price_book_ratio")
    private Float priceBookRatio;

    /**
     * 市盈率
     */
    @Column(name = "price_earnings_ratio")
    private Float priceEarningsRatio;

    /**
     * 盈利收益率
     */
    @Column(name = "earnings_yield_ratio")
    private Float earningsYieldRatio;

    /**
     * 股息率
     */
    @Column(name = "dividend_yield_ratio")
    private Float dividendYieldRatio;

    /**
     * 净资产收益率ROE
     */
    @Column(name = "return_on_equity")
    private Float returnOnEquity;

    @Column(name = "create_time")
    private String createTime;

    public static void ValidRequiredAdd(InvestValuation tmp) throws ParamValueException {
        if (tmp.getName()==null || "".equals(tmp.getName().trim())){
            throw new ParamValueException("名称是必填项，不能为空");
        }
        if (tmp.getValDay()==null || "".equals(tmp.getValDay().trim())){
            throw new ParamValueException("估值日是必填项，不能为空");
        }


    }
    public static void ValidRequiredUpd(InvestValuation tmp) throws ParamValueException {
        if (tmp.getId()==null ){
            throw new ParamValueException("ID是必填项，不能为空");
        }
        if (tmp.getName()==null || "".equals(tmp.getName().trim())){
            throw new ParamValueException("名称是必填项，不能为空");
        }
        if (tmp.getValDay()==null || "".equals(tmp.getValDay().trim())){
            throw new ParamValueException("估值日是必填项，不能为空");
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
     * @return val_day
     */
    public String getValDay() {
        return valDay;
    }

    /**
     * @param valDay
     */
    public void setValDay(String valDay) {
        this.valDay = valDay;
    }

    /**
     * 获取场内编码
     *
     * @return in_code - 场内编码
     */
    public String getInCode() {
        return inCode;
    }

    /**
     * 设置场内编码
     *
     * @param inCode 场内编码
     */
    public void setInCode(String inCode) {
        this.inCode = inCode;
    }

    /**
     * 获取场外编码
     *
     * @return out_code - 场外编码
     */
    public String getOutCode() {
        return outCode;
    }

    /**
     * 设置场外编码
     *
     * @param outCode 场外编码
     */
    public void setOutCode(String outCode) {
        this.outCode = outCode;
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
     * 获取单位净值
     *
     * @return book_value - 单位净值
     */
    public Float getBookValue() {
        return bookValue;
    }

    /**
     * 设置单位净值
     *
     * @param bookValue 单位净值
     */
    public void setBookValue(Float bookValue) {
        this.bookValue = bookValue;
    }

    /**
     * 获取市净率
     *
     * @return price_book_ratio - 市净率
     */
    public Float getPriceBookRatio() {
        return priceBookRatio;
    }

    /**
     * 设置市净率
     *
     * @param priceBookRatio 市净率
     */
    public void setPriceBookRatio(Float priceBookRatio) {
        this.priceBookRatio = priceBookRatio;
    }

    /**
     * 获取市盈率
     *
     * @return price_earnings_ratio - 市盈率
     */
    public Float getPriceEarningsRatio() {
        return priceEarningsRatio;
    }

    /**
     * 设置市盈率
     *
     * @param priceEarningsRatio 市盈率
     */
    public void setPriceEarningsRatio(Float priceEarningsRatio) {
        this.priceEarningsRatio = priceEarningsRatio;
    }

    /**
     * 获取盈利收益率
     *
     * @return earnings_yield_ratio - 盈利收益率
     */
    public Float getEarningsYieldRatio() {
        return earningsYieldRatio;
    }

    /**
     * 设置盈利收益率
     *
     * @param earningsYieldRatio 盈利收益率
     */
    public void setEarningsYieldRatio(Float earningsYieldRatio) {
        this.earningsYieldRatio = earningsYieldRatio;
    }

    /**
     * 获取股息率
     *
     * @return dividend_yield_ratio - 股息率
     */
    public Float getDividendYieldRatio() {
        return dividendYieldRatio;
    }

    /**
     * 设置股息率
     *
     * @param dividendYieldRatio 股息率
     */
    public void setDividendYieldRatio(Float dividendYieldRatio) {
        this.dividendYieldRatio = dividendYieldRatio;
    }

    /**
     * 获取净资产收益率ROE
     *
     * @return return_on_equity - 净资产收益率ROE
     */
    public Float getReturnOnEquity() {
        return returnOnEquity;
    }

    /**
     * 设置净资产收益率ROE
     *
     * @param returnOnEquity 净资产收益率ROE
     */
    public void setReturnOnEquity(Float returnOnEquity) {
        this.returnOnEquity = returnOnEquity;
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