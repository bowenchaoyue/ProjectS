package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/infomation")
public class InfomationController {

    @RequestMapping("/index")
    public String toInfomationIndex(){
        //todo 查询资讯信息
        return "infomation/index";
    }

    @RequestMapping("/list")
    public String toInfomationList(){
        //todo 查询资讯信息
        return "infomation/list";
    }

    @RequestMapping("/detail")
    public String toInfomationDetail(){
        //todo 查询资讯信息详情
        return "infomation/detail";
    }
}
