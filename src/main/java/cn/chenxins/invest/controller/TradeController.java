package cn.chenxins.invest.controller;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.invest.model.entity.InvestTrade;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.invest.model.json.TradePageJson;
import cn.chenxins.invest.model.json.ValuationPageJson;
import cn.chenxins.invest.service.TradeService;
import cn.chenxins.invest.service.ValuationService;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("invest/trade")
public class TradeController {

    @Autowired
    private TradeService baseService;

    @GetMapping("list")
    @Authorization
    public ResultJson getPageList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer perPage,
                                  @RequestParam(required = false) String applyDay, @RequestParam(required = false) String productCode, @RequestParam(required = false) Float mount){
        TradePageJson pageJson=null;
        try {
            if (page==null) {
                page=1;
            }
            if (perPage==null) {
                perPage=10;
            }
            pageJson=baseService.getListPageS(page,perPage,applyDay,productCode,mount);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess(pageJson);
    }


    @PostMapping("add")
    @Authorization
    public ResultJson addModel(@RequestBody InvestTrade json){

        try{
            InvestTrade.ValidRequiredAdd(json);
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
    public ResultJson updateModel(@RequestBody InvestTrade json){

        try{
            InvestTrade.ValidRequiredUpd(json);
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