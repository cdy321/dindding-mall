package cn.cdy.mall.service.impl;

import cn.cdy.mall.dao.CartDao;
import cn.cdy.mall.dao.GoodsDao;
import cn.cdy.mall.dao.UserDao;
import cn.cdy.mall.entity.po.CartPO;
import cn.cdy.mall.entity.vo.CartVO;
import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.UserVO;
import cn.cdy.mall.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cn.cdy.mall.service.impl.UserServiceImpl.userid;

@Service("CartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private UserDao userDao;

    //添加商品到购物车
    @Override
    public void addGoodsToCart(CartVO cartVO) {
        CartPO cartPO = new CartPO();
        Integer goodsid = cartVO.getGoodsVO().getId();
        BeanUtils.copyProperties(cartVO,cartPO);
        cartPO.setGoodsid(goodsid);
        cartPO.setUserid(userid);
        cartDao.insertCartInfo(cartPO);
    }
    //显示购物车
    @Override
    public List<CartVO> getCartInfo(Integer userid) {
        //通过goodsid和userid查出goodsvo和uservo
        List<CartPO> cartPOList = cartDao.selectAllCart(userid);
        List<CartVO> cartVOList = new ArrayList<>();
        for (CartPO cartPO : cartPOList) {
            CartVO cartVO = new CartVO();
            BeanUtils.copyProperties(cartPO,cartVO);
            Integer goodsid = cartPO.getGoodsid();
            Integer user_id = cartPO.getUserid();
            GoodsVO goodsVO = goodsDao.queryById(goodsid);
            UserVO userVO = userDao.selectUserInfoById(user_id);
            cartVO.setGoodsVO(goodsVO);
            cartVO.setUserVO(userVO);
            cartVO.setChecked(false);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }

    @Override
    public void removeCartById(Integer id) {
        cartDao.deleteCartById(id);
    }
}
