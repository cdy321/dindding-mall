package cn.cdy.mall.dao;

import cn.cdy.mall.entity.po.TypePO;
import cn.cdy.mall.entity.vo.GoodsVO;


import java.util.List;

public interface TypeDao {
    List<TypePO> selectAllType();
    List<GoodsVO> selectGoodsListByType();
}
