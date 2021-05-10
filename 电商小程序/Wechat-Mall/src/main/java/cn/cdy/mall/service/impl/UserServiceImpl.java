package cn.cdy.mall.service.impl;

import cn.cdy.mall.dao.UserDao;
import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.UserVO;
import cn.cdy.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserDao userDao;
    public static volatile int userid = -100;
    @Override
    public void addUserInfo(UserVO userVO) {
        userid = userDao.insertUserInfo(userVO);
    }

    @Override
    public int getUserInfoId(String nickName) {
        userid = userDao.selectUserInfoId(nickName);
        return userid;
    }

    @Override
    public UserVO getUserInfoById(Integer userid) {
        return userDao.selectUserInfoById(userid);
    }
}
