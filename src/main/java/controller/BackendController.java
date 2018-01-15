package controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import domain.*;
import domain.dto.MessageDTO;
import domain.vo.MessageVO;
import jodd.datetime.JDateTime;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.MessageService;
import service.PictureService;
import service.TeamService;
import service.UserService;
import utils.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/backend")
public class BackendController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(BackendController.class);

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    @Resource
    private MessageService messageService;


    /**
     * 登录后的首页
     * @return
     */
    @RequestMapping("/index.do")
    public String toLogin(){
        return "backend";
    }

    @RequestMapping("/start")
    public String toStart(){
        return "starter";
    }
    @RequestMapping("product")
    public String toProduct(){
        return "product";
    }

    @RequestMapping("infomation")
    public String toInfomation(){
        return "infomation";
    }
    @RequestMapping("message")
    public String toMessage(){
        return "message";
    }
    @RequestMapping("cooperation")
    public String toCooperation(){
        return "cooperation";
    }
    @RequestMapping("team")
    public String toTeam(){
        return "team";
    }
    /**
     * 登录验证接口
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(HttpServletRequest request,String name,String password){
        //验证用户
        User user = new User();
        user.setName(name);
        User login = userService.login(user);
        //比较密码是否相同
        String encodePassword = null;
        try {
            encodePassword = MD5Util.MD5(password);
        }catch (Exception e){

        }
        if(login.getPassword().equals(encodePassword)){
            //登录成功
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_KEY,login);
            return new Result(true);
        }else {
            //登录失败
            return  new Result(false,"用户名或密码错误");
        }
    }

    private boolean validate(Date date) {
        return new Date().getTime() - date.getTime()>60*1000;
    }


    /**
     * 退出接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/doLogout")
    @ResponseBody
    public Result doLogout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.setAttribute("_const_cas_assertion_", null);
            session.invalidate();
        }
        request.getSession().removeAttribute(Constants.SESSION_KEY);
        request.getSession().invalidate();
        return new Result(true);
    }


    /**
     * 单文件上传
     * @param file
     * @param req
     * @return
     */
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file,Byte type,HttpServletRequest req){

        //模拟登陆
        User user = new User();
        user.setId(1L);
        req.getSession().setAttribute(Constants.SESSION_KEY,user);
        //模拟登陆
        String filePath = saveFile(file,type, req);
        Map<String,String> filePathMap = Maps.newHashMap();
        filePathMap.put(file.getOriginalFilename(),filePath);
        return new Result(true,filePathMap);
    }


    @RequestMapping(value = "/picUpload",method = RequestMethod.POST)
    @ResponseBody
    public Result picUpload(@RequestParam("file") MultipartFile file,HttpServletRequest req){
        //模拟登陆
        User user = new User();
        user.setId(1L);
        req.getSession().setAttribute(Constants.SESSION_KEY,user);
        //模拟登陆
        String filePath = saveFile(file, req);
        Map<String,String> filePathMap = Maps.newHashMap();
        filePathMap.put(file.getOriginalFilename(),filePath);
        return new Result(true,filePathMap);
    }

    private String saveFile(MultipartFile file, HttpServletRequest req) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String timeStr = new JDateTime().toString("YYYYMMDDhhmmss");
                String dirStr = new JDateTime().toString("ss");
                String filedir = StoreHelper.getPicStorePath()+dirStr;
                if (!new File(filedir).exists()){
                    new File(filedir).mkdirs();
                }
                String filePath = filedir+File.separator+timeStr+file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                String urlPath = IpUtil.getHttpAddress()+":8081"+File.separator+dirStr+File.separator+timeStr+file.getOriginalFilename();
                return urlPath;
            } catch (Exception e) {
                logger.error(file.getOriginalFilename()+"上传失败！");
            }
        }
        return null;
    }

    /**
     * 多文件上传
     * @param files
     * @param req
     * @return
     */
    @RequestMapping("filesUpload")
    @ResponseBody
    public Result filesUpload(@RequestParam("files") MultipartFile[] files,Byte type, HttpServletRequest req) {

        //判断file数组不能为空并且长度大于0
        Map<String,String> filePathMap = Maps.newHashMap();
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                String filePath = saveFile(file,type,req);
                if(filePath != null){
                    filePathMap.put(file.getOriginalFilename(),filePath);
                }
            }
        }
        // 重定向
        return new Result(true,filePathMap);
    }

    private String saveFile(MultipartFile file,Byte type,HttpServletRequest request) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String timeStr = new JDateTime().toString("YYYYMMDDhhmmss");
                String dirStr = new JDateTime().toString("ss");
                String filedir = StoreHelper.getPicStorePath()+dirStr;
                if (!new File(filedir).exists()){
                    new File(filedir).mkdirs();
                }
                String filePath = filedir+File.separator+timeStr+file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                String urlPath = IpUtil.getHttpAddress()+File.separator+dirStr+File.separator+timeStr+file.getOriginalFilename();
                //保存到图片表里
                Picture picture = new Picture();
                picture.setType(type);
                picture.setPicUrl(urlPath);
                picture.setCreateId(getUser(request).getId());
                pictureService.add(picture);
                return urlPath;
            } catch (Exception e) {
                logger.error(file.getOriginalFilename()+"上传失败！");
            }
        }
        return null;
    }

    /**
     * 获取图片接口
     * @return
     */
    @RequestMapping(value = "/getPics",method = RequestMethod.POST)
    @ResponseBody
    public Result getPics(Picture picture){
        PageInfo<Picture> pageInfo = pictureService.queryByPage(picture);
        return new Result(true,pageInfo);
    }


    /**
     * 获取留言接口
     * @return
     */
    @RequestMapping(value = "/getMessages",method = RequestMethod.POST)
    @ResponseBody
    public Result getMessages(@RequestBody MessageDTO dto){
        PageInfo<MessageVO> pageInfo = messageService.queryByPage(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping("/getMessageDetail")
    @ResponseBody
    public Result getMessageDetail(Long id){
        MessageDTO dto = new MessageDTO();
        dto.setId(id);
        MessageVO pageInfo = messageService.queryById(dto);
        return new Result(true,pageInfo);
    }

    @RequestMapping(value = "/delMessage.do",method = RequestMethod.POST)
    @ResponseBody
    public Result delMessage(@RequestBody MessageDTO dto){
        return messageService.delete(dto);
    }

}
