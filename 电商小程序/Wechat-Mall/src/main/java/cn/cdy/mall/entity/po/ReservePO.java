package cn.cdy.mall.entity.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservePO {
    private Integer id;
    private Integer goodsid;
    private Integer userid;
}
