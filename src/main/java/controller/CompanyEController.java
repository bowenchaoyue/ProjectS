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
@RequestMapping("/english/company")
public class CompanyEController {

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private TeamService teamService;

    private Byte ENGLISH = 1;

    /**
     * 公司文化页面
     * @return
     */
    @RequestMapping("/culture")
    @ResponseBody
    public Result culturePage(){
        Dictionary dic  = new Dictionary();
        dic.setKey("companyCulture");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/honor")
    @ResponseBody
    public Result honorPage(){
        Dictionary dic  = new Dictionary();
        dic.setKey("companyHonor");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/team")
    @ResponseBody
    public Result teamPage(Team team){
        team.setLang(ENGLISH);
        PageInfo<Team> pageInfo = teamService.queryByPage(team);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/mission")
    @ResponseBody
    public Result missionPage(){
        Dictionary dic = new Dictionary();
        dic.setKey("companyMission");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/target")
    @ResponseBody
    public Result targetPage(){
        Dictionary dic = new Dictionary();
        dic.setKey("companyTarget");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

    @RequestMapping("/profile")
    @ResponseBody
    public Result profilePage(){
        Dictionary dic = new Dictionary();
        dic.setKey("companyProfile");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }


    @RequestMapping("/develop")
    @ResponseBody
    public Result developPage(){
        Dictionary dic = new Dictionary();
        dic.setKey("companyDevelop");
        List<Dictionary> dictionaries = dictionaryService.queryByKey(dic);
        return new Result(true,dictionaries.get(0));
    }

}
