package cn.chenxins.invest.controller;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.entity.InvestTrade;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.invest.model.json.AssetsPageJson;
import cn.chenxins.invest.model.json.HomeLineChartJson;
import cn.chenxins.invest.service.AssetsService;
import cn.chenxins.invest.service.ProductService;
import cn.chenxins.invest.service.TradeService;
import cn.chenxins.invest.service.ValuationService;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("invest/home")
public class HomeController {

    @Autowired
    private AssetsService assetsService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private ValuationService valuationService;

    @GetMapping("panel")
    @Authorization
    public Object getPanelGroupData(){
        return assetsService.getPanelGroupData();
    }

    @GetMapping("line")
    @Authorization
    public Object getLineChartData(){
        List<String> listP=productService.getHomeLineChartPCode();
        List<InvestAssets> listA=assetsService.getHomeLineChartData(listP);
        if(listA.isEmpty())
        {
            return ResultJson.ServerError();
        }

        List<String> xV=new ArrayList<>();
        List<Float> endV=new ArrayList<>();
        List<Float> profitV=new ArrayList<>();
        Float sumEndV=(float)0;
        Float sumProfitV=(float)0;

        String tmpReportDay="";
        InvestAssets assets;

        for(int i=0;i<listA.size();i++){
            assets=listA.get(i);

            /**
             * 设置时间轴，列表
             */
            if (!tmpReportDay.equals(assets.getReportDay())) {
                tmpReportDay=assets.getReportDay();
                xV.add(tmpReportDay);

                sumEndV=(float)0;
                sumProfitV=(float)0;
                endV.add(sumEndV);
                profitV.add(sumProfitV);
            }
            sumEndV=endV.get(endV.size()-1)+assets.getEndValue();
            sumProfitV=profitV.get(profitV.size()-1)+(assets.getEndValue()-assets.getStartValue());
            endV.set(endV.size()-1,sumEndV);
            profitV.set(profitV.size()-1,sumProfitV);
        }


        if (xV.size()!=endV.size()){
            return ResultJson.BussinessException("数组长度不一致！");
        }


        HomeLineChartJson  homeLineChartJson=new HomeLineChartJson();
        homeLineChartJson.setxValue(xV);
        homeLineChartJson.setEndValule(endV);
        homeLineChartJson.setProfitValue(profitV);
        return homeLineChartJson;

    }



}