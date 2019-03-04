package cn.chenxins.invest.model.entity.mapper;

import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.utils.MyMapper;

import java.util.List;

public interface InvestProductMapper extends MyMapper<InvestProduct> {

    List<String> getCodeForLineChart();
}