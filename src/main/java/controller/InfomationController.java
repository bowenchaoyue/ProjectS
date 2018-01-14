package controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import domain.Category;
import domain.Message;
import domain.Result;
import domain.dto.InfomationDTO;
import domain.dto.MessageDTO;
import domain.vo.InfomationVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CategoryService;
import service.InfomationService;
import service.MessageService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/infomation")
public class InfomationController extends BaseController{

    @Resource
    private InfomationService infomationService;

    @Resource
    private MessageService messageService;

    @Resource
    private CategoryService categoryService;


    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public Result addInfomation(@RequestBody InfomationDTO dto,HttpServletRequest request){
        dealWithDTO(request,dto);
        infomationService.add(dto);
        return new Result(true);
    }

    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public Result updateInfomation(@RequestBody InfomationDTO dto,HttpServletRequest request){
        dealWithDTO(request,dto);
        infomationService.update(dto);
        return new Result(true);
    }

    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteInfomation(@RequestBody InfomationDTO dto,HttpServletRequest request){
        dealWithDTO(request,dto);
        infomationService.delete(dto);
        return new Result(true);
    }

    @RequestMapping("/index")
    @ResponseBody
    public Result toInfomationIndex(InfomationDTO dto){
        PageInfo<InfomationVO> pageInfo = infomationService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Result toInfomationList(@RequestBody InfomationDTO dto){
        dto.setLang(CHINESE);
        PageInfo<InfomationVO> pageInfo = infomationService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Result toInfomationDetail(Long id){
        InfomationVO infomationVO = infomationService.queryById(id);
        return new Result(true,infomationVO);
    }

    @RequestMapping(value = "/addMessage",method = RequestMethod.POST)
    @ResponseBody
    public Result addMessage(@RequestBody MessageDTO message, HttpServletRequest request){
        //验证验证码
//        Result result = checkValidcode(request, message.getValidCode());
//        if (result != null){
//            return result;
//        }
        //保存留言
        return messageService.add(message);
    }

    /**
     * 获取信息类别
     * @return
     */
    @RequestMapping("/getCategory")
    @ResponseBody
    public Result getCategory(){
        Category category = new Category();
        List<Category> categories = categoryService.queryByPage(category);
        return new Result(true,categories);
    }

}
