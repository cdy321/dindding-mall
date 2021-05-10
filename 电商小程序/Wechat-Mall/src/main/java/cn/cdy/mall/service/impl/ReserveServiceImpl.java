package cn.cdy.mall.service.impl;

import cn.cdy.mall.dao.GoodsDao;
import cn.cdy.mall.dao.ReserveDao;
import cn.cdy.mall.entity.po.ReservePO;
import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.ReserveVO;
import cn.cdy.mall.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.cdy.mall.service.impl.UserServiceImpl.userid;

@Service("ReserveService")
public class ReserveServiceImpl implements ReserveService {
    @Autowired
    private ReserveDao reserveDao;
    @Autowired
    private GoodsDao goodsDao;
    //添加到收藏夹
    @Override
    public void addReserve(ReserveVO reserveVO) {
        Integer goodsid = reserveVO.getGoodsVO().getId();
        ReservePO reservePO = new ReservePO();
        reservePO.setGoodsid(goodsid);
        reservePO.setUserid(userid);
        reserveDao.insertReserve(reservePO);
    }
    //删除收藏夹
    @Override
    public void removeReserve(ReserveVO reserveVO) {
        Integer goodsid = reserveVO.getGoodsVO().getId();
        ReservePO reservePO = new ReservePO();
        reservePO.setGoodsid(goodsid);
        reservePO.setUserid(userid);
        reserveDao.deleteReserve(reservePO);
    }
    //显示收藏夹
    @Override
    public List<GoodsVO> getAllReserve(Integer userid) {
        List<ReservePO> reservePOList = reserveDao.selectAllReserve(userid);
        List<GoodsVO> goodsVOList = new ArrayList<>();
        for (ReservePO reservePO : reservePOList) {
            Integer goodsid = reservePO.getGoodsid();
            GoodsVO goodsVO = goodsDao.queryById(goodsid);
            goodsVOList.add(goodsVO);
        }
        return goodsVOList;
    }
}
