package cn.chenxins.invest.model.json;

import cn.chenxins.invest.model.entity.InvestReport;
import cn.chenxins.invest.model.entity.InvestTrade;

import java.util.List;

public class ReportPageJson extends BasePageJson {

    private List<InvestReport> list;

    public ReportPageJson(Integer page, Integer perPage, Integer total, List<InvestReport> list) {
        super(page, perPage, total);
        this.list = list;
    }

    public List<InvestReport> getList() {
        return list;
    }

    public void setList(List<InvestReport> list) {
        this.list = list;
    }
}
