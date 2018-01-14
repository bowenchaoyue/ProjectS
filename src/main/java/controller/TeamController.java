package controller;

import com.github.pagehelper.PageInfo;
import domain.Result;
import domain.Team;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TeamService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/team")
public class TeamController extends BaseController {

    @Resource
    private TeamService teamService;

    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public Result addTeam(@RequestBody Team team, HttpServletRequest request){
        dealWithDTO(request,team);
        teamService.add(team);
        return  new Result(true);
    }

    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public Result updateTeam(@RequestBody Team team, HttpServletRequest request){
        dealWithDTO(request,team);
        teamService.update(team);
        return  new Result(true);
    }

    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteTeam(@RequestBody Team team, HttpServletRequest request){
        dealWithDTO(request,team);
        teamService.delete(team);
        return  new Result(true);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result getTeam(@RequestBody Team team, HttpServletRequest request){
        team.setLang(CHINESE);
        PageInfo<Team> pageInfo = teamService.queryByPage(team);
        return  new Result(true,pageInfo);
    }
}
