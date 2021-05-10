package cn.cdy.mall.controller;

import cn.cdy.mall.entity.vo.TypeVO;
import cn.cdy.mall.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class showTypeListController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/get/goods/type/list.json")
    public List<TypeVO> showTypeList(){
        return typeService.getAllType();
    }
}
