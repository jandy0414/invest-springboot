package cn.chenxins.invest.model.json;

import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.entity.InvestTrade;

import java.util.List;

public class TradePageJson extends BasePageJson {

    private List<InvestTrade> list;

    public TradePageJson(Integer page, Integer perPage, Integer total, List<InvestTrade> list) {
        super(page, perPage, total);
        this.list = list;
    }

    public List<InvestTrade> getList() {
        return list;
    }

    public void setList(List<InvestTrade> list) {
        this.list = list;
    }
}
