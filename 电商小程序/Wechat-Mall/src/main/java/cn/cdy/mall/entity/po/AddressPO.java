package cn.cdy.mall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressPO {
    private Integer id;
    private String name;
    private String phoneNum;
    private String district;
    private String detailaddress;
    private String floor;
    private String beizhu;
    private Integer userid;
}
