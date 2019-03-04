package cn.chenxins.invest.service;


import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.invest.model.json.ValuationPageJson;
import cn.chenxins.utils.JdateUtils;
import cn.chenxins.invest.model.entity.mapper.InvestValuationMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValuationService {


    @Autowired
    private InvestValuationMapper baseMapper;

//    @Transactional(propagation = Propagation.SUPPORTS)
//    public List<InvestValuation> getHomeLineChartData(List listP) {
//        Example example = new Example(InvestValuation.class);
////        Example.Criteria criteria = example.createCriteria();
//
////        criteria.andEqualTo("valDay", valDay);
//        if (listP !=null && !listP.isEmpty())
//        {
//            example.and().orIn("inCode",  listP)
//                    .orIn("outCode",listP);
//        }
//        example.orderBy("valDay").asc();
//
//        List<InvestValuation> alist=baseMapper.selectByExample(example);
//        return alist;
//    }
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<InvestValuation> getListByPlistAndValDay(List listP,String valDay) {
        Example example = new Example(InvestValuation.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("valDay", valDay);
        example.and().orIn("inCode",  listP)
                .orIn("outCode",listP);

//        criteria.andIn("inCode",  listP );
//        criteria.orIn("outCode",listP);

        List<InvestValuation> alist=baseMapper.selectByExample(example);
        return alist;
    }

    /**
     * 设置期末净值，期末额，及盈利标志
     */
    public void computeEndValueAndEventure(List<InvestAssets> listA, List<InvestValuation> listV,String reportDay){
        InvestAssets tmp;
        InvestValuation tmp2;
        for (int i=0; i<listA.size(); i++){
            tmp=listA.get(i);
            tmp.setCreateTime(JdateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            tmp.setReportDay(reportDay);
            if (tmp.getProductCode().startsWith("hb")) {
                tmp.setUnitPrice((float)1);
                tmp.setEndValue(tmp.getStartValue());
                tmp.setEventuate(1);//货币基金必盈利
            }
            for (int j=0; j<listV.size();j++){
                tmp2=listV.get(j);
                if (tmp.getProductCode().equals(tmp2.getInCode()) || tmp.getProductCode().equals(tmp2.getOutCode())){
                    tmp.setUnitPrice(tmp2.getBookValue());
                    if (tmp.getCount()!=null && tmp.getUnitPrice()!=null)
                    {
                        tmp.setEndValue(tmp.getCount()*tmp.getUnitPrice());
                    }
                    else {
                        System.out.println("出现未知错误，数量："+tmp.getCount()+",单价："+tmp.getUnitPrice());
                        tmp.setEndValue((float)0);
                    }
                    if (tmp.getEndValue()>tmp.getStartValue()) {
                        tmp.setEventuate(1); //盈利
                    } else {
                        tmp.setEventuate(0); //亏损
                    }
                }
            }
        }
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public ValuationPageJson getListPageS(Integer page, Integer perPage, String name, String code, String valDay) {
        // 开始分页
        PageHelper.startPage(page, perPage);

        Example example = new Example(InvestValuation.class);
        Example.Criteria criteria = example.createCriteria();

        if( name !=null && !"".equals(name.trim())){
            criteria.andLike("name", "%" + name.trim() + "%");
        }
        if( valDay !=null && !"".equals(valDay.trim())){
            criteria.andEqualTo("valDay",  valDay.trim() );
        }
        if( code !=null && !"".equals(code.trim())){
            example.and().orLike("inCode", "%" + code.trim() + "%")
            .orLike("outCode","%" + code.trim() + "%");
//            criteria.orLike("outCode","%" + code.trim() + "%");
        }

        example.orderBy("id").desc();
//        if (sort !=null && "-id".equals(sort)){
//            example.orderBy("id").desc();
//        } else{
//            example.orderBy("id").asc();
//        }
        List<InvestValuation> alist=baseMapper.selectByExample(example);
        int total=baseMapper.selectCountByExample(example);

        return new ValuationPageJson(page,perPage,total,alist);

    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void addmodelS(InvestValuation json) throws BussinessErrorException,Exception{
        int exists=baseMapper.selectCount(json);
        if (exists==0){
            json.setCreateTime(JdateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            baseMapper.insert(json);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updModelS(InvestValuation json) throws Exception{
//        InvestValuation baseDist=new InvestValuation(json);
//        baseMapper.updateByPrimaryKeySelective(json);  //更新属性不为null的值
        baseMapper.updateByPrimaryKey(json); // 根据主键更新实体全部字段，null值会被更新

    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delModelS(Integer id) throws BussinessErrorException,Exception{
        InvestValuation model=baseMapper.selectByPrimaryKey(id);
        if (model==null){
            throw new BussinessErrorException("此信息不存在");
        }

        baseMapper.deleteByPrimaryKey(id);
    }


}
