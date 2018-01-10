package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cooperation")
@Controller
public class CooperationController {

    @RequestMapping("/index")
    public String toCooperationIndex(){
        //todo 查询合作伙伴相关的信息
        return "cooperation/index";
    }

    @RequestMapping("/list")
    public String toCooperationList(){
        //todo 查询合作伙伴相关的信息
        return "cooperaton/list";
    }

    @RequestMapping("/detail")
    public String toCooperationDetail(){
        //todo 查询合作伙伴详情信息
        return "cooperation/detail";
    }
}
