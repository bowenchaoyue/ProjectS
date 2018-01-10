package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/custom")
public class CustomServiceController {

    @RequestMapping("/philosophy")
    public String toPhilosophyPage(){
        //todo 查询服务理念信息
        return "custom/philosophy";
    }

    @RequestMapping("/net")
    public String toNetPage(){
        //todo 查询服务网络信息
        return "custom/net";
    }

}
