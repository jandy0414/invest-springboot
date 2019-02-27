package cn.chenxins.invest.model.json;

import cn.chenxins.invest.model.entity.InvestValuation;

import java.util.List;

public class ValuationPageJson extends BasePageJson {

    private List<InvestValuation> list;

    public ValuationPageJson(Integer page, Integer perPage, Integer total, List<InvestValuation> list) {
        super(page, perPage, total);
        this.list = list;
    }

    public List<InvestValuation> getList() {
        return list;
    }

    public void setList(List<InvestValuation> list) {
        this.list = list;
    }
}
