package cn.cdy.mall;

import cn.cdy.mall.entity.vo.*;
import cn.cdy.mall.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test1(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        TypeService service = (TypeService)ac.getBean("TypeService");
        List<TypeVO> type = service.getAllType();
        System.out.println(type);
    }
    @Test
    public void test2(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        GoodsService service = (GoodsService)ac.getBean("GoodsService");
        List<GoodsVO> animalsList = service.getAnimalsList();
        System.out.println(animalsList);
    }
    @Test
    public void test3(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        GoodsService service = (GoodsService)ac.getBean("GoodsService");
        List<GoodsVO> goodsVOList = service.showMaxList();
        System.out.println(goodsVOList);
    }
    @Test
    public void test4(){
        String config1 = "applicationContext.xml";
        String config2 = "applicationContext-tx.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config1,config2);
        UserService service = (UserService) ac.getBean("UserService");
        UserVO userVO = new UserVO();
        userVO.setAvatarUrl("http://123.com");
        userVO.setCity("zz");
        userVO.setLanguage("123");
        userVO.setCountry("zh");
        userVO.setGender(1);
        userVO.setNickName("tom");
        userVO.setProvince("hb");
        service.addUserInfo(userVO);
        AddressVO addressVO = new AddressVO();
        addressVO.setName("cdy");
        addressVO.setUserVO(userVO);
        addressVO.setBeizhu("none");
        addressVO.setFloor("10");
        addressVO.setPhoneNum("123456");
        addressVO.setDetailaddress("qweret");
        addressVO.setDistrict("qjs");
        AddressService addressService = (AddressService) ac.getBean("AddressService");
        addressService.addAddressInfo(addressVO);
    }
    @Test
    public void test5(){

    }


}
