package cn.cdy.mall.dao;

import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.TypeVO;

import java.util.List;

public interface GoodsDao {
    GoodsVO queryById(Integer id);
    List<GoodsVO> queryAll();
    List<GoodsVO> selectDetail(String content);
    List<GoodsVO> selectShoesList();
    List<GoodsVO> selectAnimalsList();
    List<GoodsVO> selectMedicineList();
    List<GoodsVO> selectFamilyList();
    List<GoodsVO> selectVegitableList();
    List<GoodsVO> selectFruitList();
    List<GoodsVO> selectHometoolList();
    List<GoodsVO> selectElectricList();
    List<GoodsVO> selectMaxGoods();
}
