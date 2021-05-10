package cn.cdy.mall.dao;

import cn.cdy.mall.entity.po.CartPO;

import java.util.List;

public interface CartDao {
    void insertCartInfo(CartPO cartPO);
    List<CartPO> selectAllCart(Integer userid);
    void deleteCartById(Integer id);
}
