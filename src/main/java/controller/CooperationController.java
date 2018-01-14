package controller;


import com.github.pagehelper.PageInfo;
import domain.Cooperation;
import domain.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CooperationService;

import javax.annotation.Resource;

@RequestMapping("/cooperation")
@Controller
public class CooperationController {

    @Resource
    private CooperationService cooperationService;

    private Byte CHINESE = 0;

    @RequestMapping("/add.do")
    @ResponseBody
    public Result addCooperation(@RequestBody Cooperation cooperation){
        cooperationService.add(cooperation);
        return new Result(true);
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public Result updateCooperation(@RequestBody Cooperation cooperation){
        cooperationService.update(cooperation);
        return new Result(true);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Result deleteCooperation(@RequestBody Cooperation cooperation){
        cooperationService.delete(cooperation);
        return new Result(true);
    }

    @RequestMapping("/index")
    @ResponseBody
    public Result toCooperationIndex(Cooperation cooperation){
        cooperation.setLang(CHINESE);
        PageInfo<Cooperation> pageInfo = cooperationService.queryByPage(cooperation);
        return new Result(true,pageInfo);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result toCooperationList(@RequestBody Cooperation cooperation){
        cooperation.setLang(CHINESE);
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
