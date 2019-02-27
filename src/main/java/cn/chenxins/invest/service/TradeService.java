package cn.chenxins.invest.service;


import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.invest.model.entity.InvestAssets;
import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.entity.InvestTrade;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.invest.model.entity.mapper.InvestTradeMapper;
import cn.chenxins.invest.model.entity.mapper.InvestValuationMapper;
import cn.chenxins.invest.model.json.TradePageJson;
import cn.chenxins.invest.model.json.ValuationPageJson;
import cn.chenxins.utils.JdateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private InvestTradeMapper baseMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<InvestTrade> getListByProducts(List<String> listP) {

        Example example = new Example(InvestTrade.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("productCode",listP);
        example.orderBy("id").asc();

        List<InvestTrade> alist=baseMapper.selectByExample(example);

        return alist;

    }

    /**
     * 计算持有量，与投入金额
     * @return
     */
    public List<InvestAssets> computeInCountAndAmount(List<String> listP, List<InvestTrade> listT){
        List<InvestAssets> list=new ArrayList<>();
        InvestAssets tmp;
        InvestTrade tmp2;
        for (int i=0; i<listP.size(); i++){
            tmp=new InvestAssets();
            tmp.setProductCode(listP.get(i));
            tmp.setCount((float)0);
            tmp.setStartValue((float)0);
            for (int j=0; j<listT.size();j++){
                tmp2=listT.get(j);
                if (tmp.getProductCode().equals(tmp2.getProductCode())){
                   if (tmp2.getOperate()== 1 || tmp2.getOperate()== 3 || tmp2.getOperate()== 4) {   //买入  认购  申购
                      tmp.setCount(tmp.getCount()+tmp2.getCount());
                      tmp.setStartValue(tmp.getStartValue()+tmp2.getMount());
                   } else if (tmp2.getOperate()== 2 || tmp2.getOperate()== 5) {   //  卖出  赎回
                       tmp.setCount(tmp.getCount()-tmp2.getCount());
                       tmp.setStartValue(tmp.getStartValue()-tmp2.getMount());
                   } else {
                       System.out.println("出现未知操作代码:"+tmp2.getOperate());
                   }
                }
            }
            list.add(tmp);
        }
        return list;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public TradePageJson getListPageS(Integer page, Integer perPage, String applyDay, String productCode, Float mount) {
        // 开始分页
        PageHelper.startPage(page, perPage);

        Example example = new Example(InvestTrade.class);
        Example.Criteria criteria = example.createCriteria();

        if( applyDay !=null && !"".equals(applyDay.trim())){
            criteria.andLike("applyDay", "%" + applyDay.trim() + "%");
        }
        if( productCode !=null && !"".equals(productCode.trim())){
            criteria.andLike("productCode", "%" + productCode.trim() + "%");
        }
        if( mount !=null ){
            criteria.andEqualTo("mount",  mount );
        }
        example.orderBy("id").desc();
//        if (sort !=null && "-id".equals(sort)){
//            example.orderBy("id").desc();
//        } else{
//            example.orderBy("id").asc();
//        }
        List<InvestTrade> alist=baseMapper.selectByExample(example);
        int total=baseMapper.selectCountByExample(example);

        return new TradePageJson(page,perPage,total,alist);

    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void addmodelS(InvestTrade json) throws BussinessErrorException,Exception{
        int exists=baseMapper.selectCount(json);
        if (exists==0){
            json.setCreateTime(JdateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            baseMapper.insert(json);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updModelS(InvestTrade json) throws Exception{
//        InvestValuation baseDist=new InvestValuation(json);
        baseMapper.updateByPrimaryKeySelective(json);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delModelS(Integer id) throws BussinessErrorException,Exception{
        InvestTrade model=baseMapper.selectByPrimaryKey(id);
        if (model==null){
            throw new BussinessErrorException("此信息不存在");
        }

        baseMapper.deleteByPrimaryKey(id);
    }


}
