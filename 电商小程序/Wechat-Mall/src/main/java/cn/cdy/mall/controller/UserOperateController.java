package cn.cdy.mall.controller;

import cn.cdy.mall.entity.vo.*;
import cn.cdy.mall.service.AddressService;
import cn.cdy.mall.service.CartService;
import cn.cdy.mall.service.ReserveService;
import cn.cdy.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserOperateController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ReserveService reserveService;
    @Autowired
    private CartService cartService;

    //微信信息存入数据库
    @RequestMapping("add/user/info.json")
    public String addUser(@RequestBody UserVO userVO){
        try{
            userService.addUserInfo(userVO);
            return "success";
        }catch (Exception e){
            //如果报主键重复异常，就查找userid
            e.printStackTrace();
            String nickName = userVO.getNickName();
            int userInfoId = userService.getUserInfoId(nickName);
            return "该用户已存在:userInfoId:"+userInfoId;
        }
    }
    //保存收获地址
    @RequestMapping("/add/user/address/info.json")
    public String addAddressInfo(@RequestBody AddressVO addressVO){
        try{
            System.out.println(addressVO.getUserVO());
            addressService.addAddressInfo(addressVO);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    //加入收藏夹
    @RequestMapping("/add/user/reserve/info.json")
    public String addReserve(@RequestBody ReserveVO reserveVO){
        try{
            reserveService.addReserve(reserveVO);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    //移出收藏夹
    @RequestMapping("/remove/user/reserve/info.json")
    public String removeReserve(@RequestBody ReserveVO reserveVO){
        try{
            reserveService.removeReserve(reserveVO);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    //在收藏夹中显示
    @RequestMapping("/get/user/reserve/info.json")
    public List<GoodsVO> showReserveList(@RequestParam("nickName") String nickName){
        System.out.println(nickName);
        int userInfoId = userService.getUserInfoId(nickName);
        return reserveService.getAllReserve(userInfoId);
    }
    //添加商品到购物车
    @RequestMapping("add/user/shoppingcart/info.json")
    public String addGoodsToCart(@RequestBody CartVO cartVO){
        try{
            cartService.addGoodsToCart(cartVO);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    //购物车列表显示
    @RequestMapping("/get/user/shoppingcart/info.json")
    public List<CartVO> showCartList(@RequestParam("nickName") String nickName){
        int userInfoId = userService.getUserInfoId(nickName);
        return cartService.getCartInfo(userInfoId);
    }
    //删除购物车
    @RequestMapping("/remove/user/shoppingcart/info.json")
    public String removeCartInfo(@RequestParam("id") Integer id){
        try{
            System.out.println(id);
            cartService.removeCartById(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

}
