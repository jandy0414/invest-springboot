package cn.chenxins.invest.service;


import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.invest.model.entity.InvestReport;
import cn.chenxins.invest.model.entity.InvestTrade;
import cn.chenxins.invest.model.entity.mapper.InvestReportMapper;
import cn.chenxins.invest.model.entity.mapper.InvestTradeMapper;
import cn.chenxins.invest.model.json.ReportPageJson;
import cn.chenxins.invest.model.json.TradePageJson;
import cn.chenxins.utils.JdateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private InvestReportMapper baseMapper;


    @Transactional(propagation = Propagation.SUPPORTS)
    public ReportPageJson getListPageS(Integer page, Integer perPage, Integer tradeId, String day, String eventuate, Float endValue) {
        // 开始分页
        PageHelper.startPage(page, perPage);

        Example example = new Example(InvestReport.class);
        Example.Criteria criteria = example.createCriteria();

        if( tradeId !=null ){
            criteria.andEqualTo("tradeId", tradeId);
        }
        if( day !=null && !"".equals(day.trim())){
            criteria.andLike("day", "%" + day.trim() + "%");
        }
        if( eventuate !=null && !"".equals(eventuate.trim())){
            criteria.andEqualTo("eventuate", eventuate);
        }
        if( endValue !=null ){
            criteria.andEqualTo("endValue",  endValue );
        }
        example.orderBy("id").desc();
//        if (sort !=null && "-id".equals(sort)){
//            example.orderBy("id").desc();
//        } else{
//            example.orderBy("id").asc();
//        }
        List<InvestReport> alist=baseMapper.selectByExample(example);
        int total=baseMapper.selectCountByExample(example);

        return new ReportPageJson(page,perPage,total,alist);

    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void addmodelS(InvestReport json) throws BussinessErrorException,Exception{
        int exists=baseMapper.selectCount(json);
        if (exists==0){
            json.setCreateTime(JdateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            baseMapper.insert(json);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updModelS(InvestReport json) throws Exception{
//        InvestValuation baseDist=new InvestValuation(json);
        baseMapper.updateByPrimaryKeySelective(json);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delModelS(Integer id) throws BussinessErrorException,Exception{
        InvestReport model=baseMapper.selectByPrimaryKey(id);
        if (model==null){
            throw new BussinessErrorException("此信息不存在");
        }

        baseMapper.deleteByPrimaryKey(id);
    }


}
