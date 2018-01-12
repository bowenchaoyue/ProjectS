package controller;


import com.github.pagehelper.PageInfo;
import domain.Cooperation;
import domain.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CooperationService;

import javax.annotation.Resource;

@RequestMapping("/cooperation")
@Controller
public class CooperationController {

    @Resource
    private CooperationService cooperationService;

    @RequestMapping("/index")
    @ResponseBody
    public Result toCooperationIndex(Cooperation cooperation){
        PageInfo<Cooperation> pageInfo = cooperationService.queryByPage(cooperation);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result toCooperationList(Cooperation cooperation){
        PageInfo<Cooperation> pageInfo = cooperationService.queryByPage(cooperation);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Result toCooperationDetail(Long id){
        Cooperation cooperation = new Cooperation();
        cooperation.setId(id);
        Cooperation query = cooperationService.queryById(cooperation);
        return  new Result(true,query);
    }
}
