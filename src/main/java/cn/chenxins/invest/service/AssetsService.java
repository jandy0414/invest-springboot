package cn.chenxins.invest.service;


import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.entity.mapper.InvestAssetsMapper;
import cn.chenxins.invest.model.json.HomePanelJson;
import cn.chenxins.invest.model.json.AssetsPageJson;
import cn.chenxins.utils.JdateUtils;
import cn.chenxins.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AssetsService {

    @Autowired
    private InvestAssetsMapper baseMapper;

    public HomePanelJson getPanelGroupData(){
        return baseMapper.getForHome();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<InvestAssets> getHomeLineChartData(List listP) {
        Example example = new Example(InvestAssets.class);
        Example.Criteria criteria = example.createCriteria();

//        criteria.andEqualTo("valDay", valDay);
        if (listP !=null && !listP.isEmpty())
        {
            criteria.andIn("productCode",  listP);
        }
        criteria.andIsNotNull("reportDay");

        example.orderBy("reportDay").asc();

        List<InvestAssets> alist=baseMapper.selectByExample(example);
        return alist;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public AssetsPageJson getListPageS(Integer page, Integer perPage, String reportDay, String productCode, Integer eventuate) {
        // 开始分页
        PageHelper.startPage(page, perPage);

        Example example = new Example(InvestAssets.class);
        Example.Criteria criteria = example.createCriteria();

        if(StringUtil.isNotBlank(reportDay)){
            criteria.andEqualTo("reportDay",  reportDay.trim());
        }
        if( StringUtil.isNotBlank(productCode) ){
            criteria.andLike("productCode", "%" + productCode.trim() + "%");
        }
        if( eventuate !=null ){
            criteria.andEqualTo("eventuate",  eventuate );
        }
        example.orderBy("id").desc();
//        if (sort !=null && "-id".equals(sort)){
//            example.orderBy("id").desc();
//        } else{
//            example.orderBy("id").asc();
//        }
        List<InvestAssets> alist=baseMapper.selectByExample(example);
        int total=baseMapper.selectCountByExample(example);

        return new AssetsPageJson(page,perPage,total,alist);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int genModels(List<InvestAssets> list,String reportDay) throws BussinessErrorException,Exception{
        InvestAssets delAssets=new InvestAssets();
        delAssets.setReportDay(reportDay);
        baseMapper.delete(delAssets);
        int cn=baseMapper.insertList(list);
        return cn;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reGenModels(List<InvestAssets> list,InvestAssets json) throws BussinessErrorException,Exception{
        baseMapper.deleteByPrimaryKey(json.getId());
        baseMapper.insertList(list);

    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void addmodelS(InvestAssets json) throws BussinessErrorException,Exception{
        int exists=baseMapper.selectCount(json);
        if (exists==0){
            json.setCreateTime(JdateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            baseMapper.insert(json);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updModelS(InvestAssets json) throws Exception{
//        InvestValuation baseDist=new InvestValuation(json);
        baseMapper.updateByPrimaryKeySelective(json);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delModelS(Integer id) throws BussinessErrorException,Exception{
        InvestAssets model=baseMapper.selectByPrimaryKey(id);
        if (model==null){
            throw new BussinessErrorException("此信息不存在");
        }

        baseMapper.deleteByPrimaryKey(id);
    }


}
