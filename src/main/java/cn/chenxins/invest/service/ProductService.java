package cn.chenxins.invest.service;


import cn.chenxins.exception.BussinessErrorException;
import cn.chenxins.invest.model.entity.InvestProduct;
import cn.chenxins.invest.model.entity.InvestValuation;
import cn.chenxins.invest.model.entity.mapper.InvestProductMapper;
import cn.chenxins.invest.model.json.ProductPageJson;
import cn.chenxins.utils.JdateUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private InvestProductMapper baseMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<InvestProduct> getInvestProductList() {
        Example example = new Example(InvestProduct.class);
        Example.Criteria criteria = example.createCriteria();

//        criteria.andNotLike("code", "hb%");
        criteria.andEqualTo("status",  1 );


        return baseMapper.selectByExample(example);
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    public ProductPageJson getListPageS(Integer page, Integer perPage, String name, String code, Integer isInCode, Integer status) {
        // 开始分页
        PageHelper.startPage(page, perPage);

        Example example = new Example(InvestProduct.class);
        Example.Criteria criteria = example.createCriteria();

        if( name !=null && !"".equals(name.trim())){
            criteria.andLike("name", "%" + name.trim() + "%");
        }
        if( code !=null && !"".equals(code.trim())){
            criteria.andLike("code", "%" + code.trim() + "%");
        }
        if( isInCode !=null ){
            criteria.andEqualTo("isInCode",  isInCode );
        }
        if( status !=null ){
            criteria.andEqualTo("status",  status );
        }
        example.orderBy("createTime").desc();
//        if (sort !=null && "-id".equals(sort)){
//            example.orderBy("id").desc();
//        } else{
//            example.orderBy("id").asc();
//        }
        List<InvestProduct> alist=baseMapper.selectByExample(example);
        int total=baseMapper.selectCountByExample(example);

        return new ProductPageJson(page,perPage,total,alist);

    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void addmodelS(InvestProduct json) throws BussinessErrorException,Exception{
        int exists=baseMapper.selectCount(json);
        if (exists==0){
            json.setCreateTime(JdateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
            baseMapper.insert(json);
        } else {
            throw new BussinessErrorException("此信息已经存在,请不要重复创建");
        }

    }



    @Transactional(propagation = Propagation.REQUIRED)
    public void updModelS(InvestProduct json) throws Exception{
//        InvestValuation baseDist=new InvestValuation(json);
        baseMapper.updateByPrimaryKeySelective(json);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void delModelS(String id) throws BussinessErrorException,Exception{
        InvestProduct model=baseMapper.selectByPrimaryKey(id);
        if (model==null){
            throw new BussinessErrorException("此信息不存在");
        }

        baseMapper.deleteByPrimaryKey(id);
    }


}
