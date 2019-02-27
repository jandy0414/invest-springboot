package cn.chenxins.invest.controller;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.invest.model.entity.InvestReport;
import cn.chenxins.invest.model.entity.InvestTrade;
import cn.chenxins.invest.model.json.ReportPageJson;
import cn.chenxins.invest.model.json.TradePageJson;
import cn.chenxins.invest.service.ReportService;
import cn.chenxins.invest.service.TradeService;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("invest/report")
public class ReportController {

    @Autowired
    private ReportService baseService;

    @GetMapping("list")
    @Authorization
    public ResultJson getPageList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer perPage,
                                  @RequestParam(required = false) Integer tradeId, @RequestParam(required = false) String day, @RequestParam(required = false) String envetuate, @RequestParam(required = false) Float endValue){
        ReportPageJson pageJson=null;
        try {
            if (page==null) {
                page=1;
            }
            if (perPage==null) {
                perPage=10;
            }
            pageJson=baseService.getListPageS(page,perPage,tradeId,day,envetuate,endValue);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess(pageJson);
    }


    @PostMapping("add")
    @Authorization
    public ResultJson addModel(@RequestBody InvestReport json){

        try{
            InvestReport.ValidRequiredAdd(json);
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
    public ResultJson updateModel(@RequestBody InvestReport json){

        try{
            InvestReport.ValidRequiredUpd(json);
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