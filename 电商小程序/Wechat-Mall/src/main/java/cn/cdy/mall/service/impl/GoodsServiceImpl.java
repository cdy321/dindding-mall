package cn.cdy.mall.service.impl;

import cn.cdy.mall.dao.GoodsDao;
import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.TypeVO;
import cn.cdy.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public GoodsVO show(Integer id) {
        return goodsDao.queryById(id);
    }

    @Override
    public List<GoodsVO> showList() {
        return goodsDao.queryAll();
    }

    @Override
    public List<GoodsVO> showDetail(String content) {
        return goodsDao.selectDetail(content);
    }

    @Override
    public List<GoodsVO> getShoesList() {
        return goodsDao.selectShoesList();
    }

    @Override
    public List<GoodsVO> getAnimalsList() {
        return goodsDao.selectAnimalsList();
    }

    @Override
    public List<GoodsVO> getMedicineList() {
        return goodsDao.selectMedicineList();
    }

    @Override
    public List<GoodsVO> getFamilyList() {
        return goodsDao.selectFamilyList();
    }

    @Override
    public List<GoodsVO> getVegitableList() {
        return goodsDao.selectVegitableList();
    }

    @Override
    public List<GoodsVO> getFruitList() {
        return goodsDao.selectFruitList();
    }

    @Override
    public List<GoodsVO> gettHometoolList() {
        return goodsDao.selectHometoolList();
    }

    @Override
    public List<GoodsVO> getElectricList() {
        return goodsDao.selectElectricList();
    }

    @Override
    public List<GoodsVO> showMaxList() {
        return goodsDao.selectMaxGoods();
    }
}
