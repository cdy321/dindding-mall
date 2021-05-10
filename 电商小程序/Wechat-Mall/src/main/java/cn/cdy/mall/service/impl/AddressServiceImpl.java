package cn.cdy.mall.service.impl;

import cn.cdy.mall.dao.AddressDao;
import cn.cdy.mall.dao.UserDao;
import cn.cdy.mall.entity.po.AddressPO;
import cn.cdy.mall.entity.vo.AddressVO;
import cn.cdy.mall.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.cdy.mall.service.impl.UserServiceImpl.userid;


@Service("AddressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private UserDao userDao;
    @Override
    public void addAddressInfo(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO,addressPO);

        addressPO.setUserid(userid);
        addressDao.insertAddressInfo(addressPO);
    }
}
