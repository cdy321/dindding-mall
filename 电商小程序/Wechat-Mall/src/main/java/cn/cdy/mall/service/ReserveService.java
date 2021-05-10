package cn.cdy.mall.service;

import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.ReserveVO;

import java.util.List;

public interface ReserveService {
    void addReserve(ReserveVO reserveVO);
    void removeReserve(ReserveVO reserveVO);
    List<GoodsVO> getAllReserve(Integer userid);
}
