package cn.cdy.mall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressVO {
    private String name;
    private String phoneNum;
    private String district;
    private String detailaddress;
    private String floor;
    private String beizhu;
    private UserVO userVO;
}
