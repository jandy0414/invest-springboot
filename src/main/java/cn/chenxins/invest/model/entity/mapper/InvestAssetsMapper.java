package cn.chenxins.invest.model.entity.mapper;

import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.json.HomePanelJson;
import cn.chenxins.utils.MyMapper;

public interface InvestAssetsMapper extends MyMapper<InvestAssets> {

    HomePanelJson getForHome();
}