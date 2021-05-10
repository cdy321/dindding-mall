package cn.cdy.mall.entity.vo;

public class GoodsVO {
    private Integer id;
    private String name;
    private String type;
    private double price;
    private Integer stock;
    private Integer saleNum;
    private String picturepath;
    private String detailpicturepath;

    public GoodsVO() {
    }

    public GoodsVO(Integer id, String name, String type, double price, Integer stock, Integer saleNum, String picturepath, String detailpicturepath) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.stock = stock;
        this.saleNum = saleNum;
        this.picturepath = picturepath;
        this.detailpicturepath = detailpicturepath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getPicturepath() {
        return picturepath;
    }

    public void setPicturepath(String picturepath) {
        this.picturepath = picturepath;
    }

    public String getDetailpicturepath() {
        return detailpicturepath;
    }

    public void setDetailpicturepath(String detailpicturepath) {
        this.detailpicturepath = detailpicturepath;
    }

    @Override
    public String toString() {
        return "GoodsVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", saleNum=" + saleNum +
                ", picturepath='" + picturepath + '\'' +
                ", detailpicturepath='" + detailpicturepath + '\'' +
                '}';
    }
}
