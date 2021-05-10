package cn.cdy.mall.dao;

import cn.cdy.mall.entity.po.ReservePO;
import cn.cdy.mall.entity.vo.GoodsVO;

import java.util.List;

public interface ReserveDao {
    void insertReserve(ReservePO reservePO);
    void deleteReserve(ReservePO reservePO);
    List<ReservePO> selectAllReserve(Integer userid);
}
