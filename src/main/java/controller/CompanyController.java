package controller;


import com.github.pagehelper.PageInfo;
import domain.Dictionary;
import domain.Result;
import domain.Team;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DictionaryService;
import service.TeamService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private TeamService teamService;

    /**
     * 公司文化页面
     * @return
     */
    @RequestMapping("/culture")
    @ResponseBody
    public Result culturePage(){
        Dictionary dic  = new Dictionary();
        dic.setKey("公司文化");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/honor")
    @ResponseBody
    public Result honorPage(){
        Dictionary dic  = new Dictionary();
        dic.setKey("公司荣誉");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/team")
    @ResponseBody
    public Result teamPage(Team team){
        PageInfo<Team> pageInfo = teamService.queryByPage(team);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/mission")
    @ResponseBody
    public Result missionPage(){
        Dictionary dic = new Dictionary();
        dic.setKey("公司使命");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/target")
    @ResponseBody
    public Result targetPage(){
        Dictionary dic = new Dictionary();
        dic.setKey("公司目标");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/profile")
    @ResponseBody
    public Result profilePage(){
        Dictionary dic = new Dictionary();
        dic.setKey("公司简介");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }


    @RequestMapping("/develop")
    @ResponseBody
    public Result developPage(){
        Dictionary dic = new Dictionary();
        dic.setKey("公司发展");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

}
