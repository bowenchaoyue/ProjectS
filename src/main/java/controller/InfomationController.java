package controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import domain.Message;
import domain.Result;
import domain.dto.InfomationDTO;
import domain.dto.MessageDTO;
import domain.vo.InfomationVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.InfomationService;
import service.MessageService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/infomation")
public class InfomationController extends BaseController{

    @Resource
    private InfomationService infomationService;

    @Resource
    private MessageService messageService;


    @RequestMapping("/index")
    @ResponseBody
    public Result toInfomationIndex(InfomationDTO dto){
        PageInfo<InfomationVO> pageInfo = infomationService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/list")
    public Result toInfomationList(InfomationDTO dto){
        PageInfo<InfomationVO> pageInfo = infomationService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/detail")
    public Result toInfomationDetail(Long id){
        InfomationVO infomationVO = infomationService.queryById(id);
        return new Result(true,infomationVO);
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public Result addMessage(MessageDTO message, String validCode, HttpServletRequest request){

        //验证验证码
        Result result = checkValidcode(request, validCode);
        if (result != null){
            return result;
        }
        //保存留言
        return messageService.add(message);
    }
}
