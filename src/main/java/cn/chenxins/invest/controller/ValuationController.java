package cn.chenxins.invest.controller;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.utils.ResultJson;
import cn.chenxins.invest.model.json.ValuationPageJson;
import cn.chenxins.invest.service.ValuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("invest/valuation")
public class ValuationController {

    @Autowired
    private ValuationService baseService;

    @GetMapping("list")
    @Authorization
    public ResultJson getPageList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer perPage,
                                  @RequestParam(required = false) String name, @RequestParam(required = false) String code, @RequestParam(required = false) String valDay){
        ValuationPageJson distPageJson=null;
        try {
            if (page==null) {
                page=1;
            }
            if (perPage==null) {
                perPage=10;
            }
            distPageJson=baseService.getListPageS(page,perPage,name,code,valDay);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess(distPageJson);
    }


    @PostMapping("add")
    @Authorization
    public ResultJson addModel(@RequestBody InvestValuation json){

        try{
            InvestValuation.ValidRequiredAdd(json);
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
    public ResultJson updateModel(@RequestBody InvestValuation json){

        try{
            InvestValuation.ValidRequiredUpd(json);
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