package cn.chenxins.invest.model.json;

import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.entity.InvestValuation;

import java.util.List;

public class ProductPageJson extends BasePageJson {

    private List<InvestProduct> list;

    public ProductPageJson(Integer page, Integer perPage, Integer total, List<InvestProduct> list) {
        super(page, perPage, total);
        this.list = list;
    }

    public List<InvestProduct> getList() {
        return list;
    }

    public void setList(List<InvestProduct> list) {
        this.list = list;
    }
}
