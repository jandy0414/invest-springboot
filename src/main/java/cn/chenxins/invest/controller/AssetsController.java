package cn.chenxins.invest.controller;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.entity.InvestTrade;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.invest.model.json.AssetsPageJson;
import cn.chenxins.invest.service.AssetsService;
import cn.chenxins.invest.service.ProductService;
import cn.chenxins.invest.service.TradeService;
import cn.chenxins.invest.service.ValuationService;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("invest/assets")
public class AssetsController {

    @Autowired
    private AssetsService baseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private ValuationService valuationService;

    @GetMapping("list")
    @Authorization
    public ResultJson getPageList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer perPage,
                                  @RequestParam(required = false) String reportDay, @RequestParam(required = false) String productCode, @RequestParam(required = false) Integer eventuate){
        AssetsPageJson pageJson=null;
        try {
            if (page==null) {
                page=1;
            }
            if (perPage==null) {
                perPage=10;
            }
            pageJson=baseService.getListPageS(page,perPage,reportDay,productCode,eventuate);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess(pageJson);
    }

    @PostMapping("gens")
    @Authorization
    public ResultJson getModels(@RequestParam String reportDay){
        try {
            List<InvestProduct> listP=productService.getInvestProductList();
            if (listP ==null || listP.size()==0)
            {
                return ResultJson.BussinessException("无状态为：在投 的产品");
            }
            List<String> listPCode=new ArrayList<>();
            for (int i=0; i<listP.size(); i++)
            {
                listPCode.add(listP.get(i).getCode());
            }

            List<InvestValuation> listV=valuationService.getListByPlistAndValDay(listPCode,reportDay);
            if (listV ==null || listV.size()==0){
                return ResultJson.BussinessException("根据评估日："+reportDay+",找不到估值信息");
            }

            List<InvestTrade> listT=tradeService.getListByProducts(listPCode);
            if (listT ==null || listT.size()==0){
                return ResultJson.ServerError();
            }

            List<InvestAssets> listA=tradeService.computeInCountAndAmount(listPCode,listT);

            valuationService.computeEndValueAndEventure(listA,listV,reportDay);

            int cn=baseService.genModels(listA,reportDay);

            return ResultJson.Sucess("成功评估的"+cn+"个产品");
        }catch (BussinessErrorException be){
//            be.printStackTrace();
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }
    @PutMapping("regen")
    @Authorization
    public ResultJson reGenAsset(@RequestBody InvestAssets json) {
        try{
            InvestAssets.ValidRequiredRegen(json);
        }catch (ParamValueException pe){
            pe.printStackTrace();
            return ResultJson.ParameterException(pe.getLocalizedMessage(),json);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }
        try{
            List<String> listPCode=new ArrayList<>();
            listPCode.add(json.getProductCode());

            List<InvestValuation> listV=valuationService.getListByPlistAndValDay(listPCode,json.getReportDay());
            if (listV ==null || listV.size()==0){
                return ResultJson.BussinessException("根据评估日："+json.getReportDay()+",找不到估值信息");
            }

            List<InvestTrade> listT=tradeService.getListByProducts(listPCode);
            if (listT ==null || listT.size()==0){
                return ResultJson.ServerError();
            }

            List<InvestAssets> listA=tradeService.computeInCountAndAmount(listPCode,listT);

            valuationService.computeEndValueAndEventure(listA,listV,json.getReportDay());

            baseService.reGenModels(listA,json);
            return ResultJson.Sucess();
        }catch (BussinessErrorException be){
//            be.printStackTrace();
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }

    }

    @PostMapping("add")
    @Authorization
    public ResultJson addModel(@RequestBody InvestAssets json){

        try{
            InvestAssets.ValidRequiredAdd(json);
        }catch (ParamValueException pe){
//            pe.printStackTrace();
            return ResultJson.ParameterException(pe.getLocalizedMessage(),json);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            baseService.addmodelS(json);
        }catch (BussinessErrorException be){
//            be.printStackTrace();
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess();
    }

    @PutMapping("upd")
    @Authorization
    public ResultJson updateModel(@RequestBody InvestAssets json){

        try{
            InvestAssets.ValidRequiredUpd(json);
        }catch (ParamValueException pe){
            pe.printStackTrace();
            return ResultJson.ParameterException(pe.getLocalizedMessage(),json);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ParameterError();
        }

        try {
            baseService.updModelS(json);
        }catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess();
    }

    @DeleteMapping("del/{id}")
    @Authorization
    public ResultJson deleteModel(@PathVariable Integer id){

        try {
            baseService.delModelS(id);
        }catch (BussinessErrorException be){
            return ResultJson.BussinessException(be.getLocalizedMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.DelSucess();
    }
}