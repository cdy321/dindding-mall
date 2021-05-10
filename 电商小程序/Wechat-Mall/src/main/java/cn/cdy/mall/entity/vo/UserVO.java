package cn.cdy.mall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UserVO {
    private Integer id;
    private String nickName;
    private String city;
    private String country;
    private Integer gender;
    private String avatarUrl;
    private String province;
    private String language;

}
