package cn.cdy.mall.service;

import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.TypeVO;

import java.util.List;

public interface GoodsService {
    GoodsVO show(Integer id);
    List<GoodsVO> showList();
    List<GoodsVO> showDetail(String content);
    List<GoodsVO> getShoesList();
    List<GoodsVO> getAnimalsList();
    List<GoodsVO> getMedicineList();
    List<GoodsVO> getFamilyList();
    List<GoodsVO> getVegitableList();
    List<GoodsVO> getFruitList();
    List<GoodsVO> gettHometoolList();
    List<GoodsVO> getElectricList();
    List<GoodsVO> showMaxList();
}
