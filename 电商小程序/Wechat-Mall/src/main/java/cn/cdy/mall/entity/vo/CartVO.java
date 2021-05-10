package cn.cdy.mall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartVO {
    private Integer id;
    private GoodsVO goodsVO;
    private UserVO userVO;
    private Integer goodsnumber;
    private double sumprice;
    private boolean checked;
}
