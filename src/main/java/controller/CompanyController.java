package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    /**
     * 公司文化页面
     * @return
     */
    @RequestMapping("/culture")
    public String culturePage(){
        //todo 查询公司文化信息
        return "company/culture";
    }

    @RequestMapping("/honor")
    public String honorPage(){
        //todo 查询公司荣誉信息
        return "company/honor";
    }

    @RequestMapping("/team")
    public String teamPage(){
        //todo 查询公司团队信息
        return "company/team";
    }

    @RequestMapping("/mission")
    public String missionPage(){
        //todo 查询公司使命信息
        return "company/mission";
    }

    @RequestMapping("/target")
    public String targetPage(){
        //todo 查询公司目标信息
        return "company/target";
    }

    @RequestMapping("/profile")
    public String profilePage(){
        //todo 查询公司简介信息
        return "company/profile";
    }


    @RequestMapping("/develop")
    public String developPage(){
        //todo 查询公司发展信息
        return "company/develop";
    }

}
