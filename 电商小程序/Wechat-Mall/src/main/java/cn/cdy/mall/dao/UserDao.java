package cn.cdy.mall.dao;


import cn.cdy.mall.entity.vo.UserVO;

public interface UserDao {
    int insertUserInfo(UserVO userVO);
    int selectUserInfoId(String nickName);
    UserVO selectUserInfoById(Integer userid);
}
