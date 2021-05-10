package cn.cdy.mall.service;

import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.UserVO;

public interface UserService {
    void addUserInfo(UserVO userVO);
    int getUserInfoId(String nickName);
    UserVO getUserInfoById(Integer userid);
}
