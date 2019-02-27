package cn.chenxins.invest.model.json;

import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.entity.InvestTrade;

import java.util.List;

public class AssetsPageJson extends BasePageJson {

    private List<InvestAssets> list;

    public AssetsPageJson(Integer page, Integer perPage, Integer total, List<InvestAssets> list) {
        super(page, perPage, total);
        this.list = list;
    }

    public List<InvestAssets> getList() {
        return list;
    }

    public void setList(List<InvestAssets> list) {
        this.list = list;
    }
}
