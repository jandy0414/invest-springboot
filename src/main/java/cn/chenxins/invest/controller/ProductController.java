package cn.chenxins.invest.controller;

import cn.chenxins.authorization.annotation.Authorization;
import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.exception.ParamValueException;
import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.json.ProductPageJson;
import cn.chenxins.invest.service.ProductService;
import cn.chenxins.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("invest/product")
public class ProductController {

    @Autowired
    private ProductService baseService;

    @GetMapping("list")
    @Authorization
    public ResultJson getPageList(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer perPage,
                                  @RequestParam(required = false) String name, @RequestParam(required = false) String code,
                                  @RequestParam(required = false) Integer isInCode, @RequestParam(required = false) Integer status){
        ProductPageJson jsonList=null;
        try {
            if (page==null) {
                page=1;
            }
            if (perPage==null) {
                perPage=10;
            }
            jsonList=baseService.getListPageS(page,perPage,name,code,isInCode,status);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResultJson.ServerError();
        }
        return ResultJson.Sucess(jsonList);
    }


    @PostMapping("add")
    @Authorization
    public ResultJson addModel(@RequestBody InvestProduct json){

        try{
            InvestProduct.ValidRequiredAdd(json);
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
    public ResultJson updateModel(@RequestBody InvestProduct json){

        try{
            InvestProduct.ValidRequiredUpd(json);
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
    public ResultJson deleteModel(@PathVariable String id){

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