package cn.cdy.mall.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartPO {
    private Integer id;
    private Integer goodsid;
    private Integer userid;
    private Integer goodsnumber;
    private double sumprice;
    private boolean checked;

}
