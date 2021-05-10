package cn.cdy.mall.service.impl;

import cn.cdy.mall.dao.TypeDao;
import cn.cdy.mall.entity.po.TypePO;
import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.TypeVO;
import cn.cdy.mall.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("TypeService")
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    @Override
    public List<TypeVO> getAllType() {
        //获取商品类型对应的不同商品列表
        List<GoodsVO> goodsList = typeDao.selectGoodsListByType();
        //复制属性
        List<TypePO> typePOList = typeDao.selectAllType();
        List<TypeVO> typeVOList = new ArrayList<>();
        for (TypePO typePO : typePOList) {
            TypeVO typeVO = new TypeVO();
            BeanUtils.copyProperties(typePO,typeVO);
            //创建一个临时集合用来存放goodsvo
            List<GoodsVO> tempList = new ArrayList<>();
            //加入条件 goodVO中的type是否存在typePO的type
            for (GoodsVO goodsVO : goodsList) {
                if(typePO.getType().equals(goodsVO.getType())){
                    tempList.add(goodsVO);
                    typeVO.setGoodsVOList(tempList);
                }
            }
            typeVOList.add(typeVO);
        }
        return typeVOList;
    }
}
