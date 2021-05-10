package cn.cdy.mall.entity.vo;


import java.util.List;

public class TypeVO {

    private Integer id;
    private String type;
    private List<GoodsVO> goodsVOList;

    public TypeVO() {
    }
    public TypeVO(Integer id, String type,List<GoodsVO> goodsVOList) {
        this.id = id;
        this.type = type;
        this.goodsVOList = goodsVOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GoodsVO> getGoodsVOList() {
        return goodsVOList;
    }

    public void setGoodsVOList(List<GoodsVO> goodsVOList) {
        this.goodsVOList = goodsVOList;
    }

    @Override
    public String toString() {
        return "TypeVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", goodsVOList=" + goodsVOList +
                '}';
    }
}
