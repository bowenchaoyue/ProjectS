package controller;

import com.github.pagehelper.PageInfo;
import domain.Category;
import domain.Result;
import domain.dto.InfomationDTO;
import domain.dto.MessageDTO;
import domain.vo.InfomationVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CategoryService;
import service.InfomationService;
import service.MessageService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/english/infomation")
public class InfomationEController extends BaseController{

    @Resource
    private InfomationService infomationService;

    @Resource
    private MessageService messageService;

    @Resource
    private CategoryService categoryService;

    private Byte ENGLISH = 1;



    @RequestMapping("/index")
    @ResponseBody
    public Result toInfomationIndex(InfomationDTO dto){
        dto.setLang(ENGLISH);
        PageInfo<InfomationVO> pageInfo = infomationService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result toInfomationList(@RequestBody InfomationDTO dto){
        dto.setLang(ENGLISH);
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
