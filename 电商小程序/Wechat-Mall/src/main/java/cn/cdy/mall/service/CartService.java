package cn.cdy.mall.service;


import cn.cdy.mall.entity.vo.CartVO;
import cn.cdy.mall.entity.vo.GoodsVO;

import java.util.List;

public interface CartService {
    void addGoodsToCart(CartVO cartVO);
    List<CartVO> getCartInfo(Integer userid);
    void removeCartById(Integer id);
}
