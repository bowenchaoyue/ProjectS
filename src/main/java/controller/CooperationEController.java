package controller;


import com.github.pagehelper.PageInfo;
import domain.Cooperation;
import domain.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CooperationService;

import javax.annotation.Resource;

@RequestMapping("/english/cooperation")
@Controller
public class CooperationEController {

    @Resource
    private CooperationService cooperationService;

    private Byte ENGLISH = 1;

    @RequestMapping(value = "/index",method = RequestMethod.POST)
    @ResponseBody
    public Result toCooperationIndex(Cooperation cooperation){
        cooperation.setLang(ENGLISH);
        PageInfo<Cooperation> pageInfo = cooperationService.queryByPage(cooperation);
        return new Result(true,pageInfo);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result toCooperationList(Cooperation cooperation){
        cooperation.setLang(ENGLISH);
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
