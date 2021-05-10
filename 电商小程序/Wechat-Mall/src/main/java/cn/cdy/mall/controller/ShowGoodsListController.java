package cn.cdy.mall.controller;



import cn.cdy.mall.entity.vo.GoodsVO;
import cn.cdy.mall.entity.vo.TypeVO;
import cn.cdy.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ShowGoodsListController {
    @Autowired
    private GoodsService goodsService;
    //根据id查询商品
    @RequestMapping("/get/goods/detail.json")
    public GoodsVO showGoodsDetail(@RequestParam("id") Integer id){
        return goodsService.show(id);
    }
    //首页销量排行
    @RequestMapping("/get/goods/list/bySaleNum.json")
    public List<GoodsVO> showMaxGoodsList(){
        return goodsService.showMaxList();
    }
    //显示商品列表
    @RequestMapping("/get/goods/list.json")
    public List<GoodsVO> showGoodsList(){
        return goodsService.showList();
    }
    //查询商品信息
    @RequestMapping("/search/goods/info.json")
    public List<GoodsVO> showGoodsDetail(@RequestParam("content") String content){
        return goodsService.showDetail(content);
    }
    //商品鞋子页面
    @RequestMapping("/get/goods/list/shoes.json")
    public List<GoodsVO> showShoesList(){
        return goodsService.getShoesList();
    }
    //商品宠物页面
    @RequestMapping("/get/goods/list/animals.json")
    public List<GoodsVO> showAnimalsList(){
        return goodsService.getAnimalsList();
    }
    //商品药品页面
    @RequestMapping("/get/goods/list/medicine.json")
    public List<GoodsVO> showMedicineList(){
        return goodsService.getMedicineList();
    }
    //商品母婴页面
    @RequestMapping("/get/goods/list/family.json")
    public List<GoodsVO> showFamilyList(){
        return goodsService.getFamilyList();
    }
    //商品电子页面
    @RequestMapping("/get/goods/list/electric.json")
    public List<GoodsVO> showElectricList(){
        return goodsService.getElectricList();
    }
    //商品水果页面
    @RequestMapping("/get/goods/list/fruit.json")
    public List<GoodsVO> showFruitList(){
        return goodsService.getFruitList();
    }
    //商品蔬菜页面
    @RequestMapping("/get/goods/list/vegitable.json")
    public List<GoodsVO> showVegitableList(){
        return goodsService.getVegitableList();
    }
    //商品家具页面
    @RequestMapping("/get/goods/list/hometool.json")
    public List<GoodsVO> showHometoolList(){
        return goodsService.gettHometoolList();
    }

}
