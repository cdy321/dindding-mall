package cn.cdy.mall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReserveVO {
    private Integer id;
    private GoodsVO goodsVO;
    private UserVO userVO;
}
