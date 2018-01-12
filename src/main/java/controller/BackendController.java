package controller;


import com.google.common.collect.Maps;
import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.UserService;
import utils.CipherUtils;
import utils.CookieUtils;
import utils.MD5Util;
import utils.ValidateCodeUtils;

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
    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录验证接口
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result doLogin(HttpServletRequest request,String name,String password,String code){
        //验证验证码
        Result result = checkValidcode(request, code);
        if (result != null){
            return result;
        }
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
     * 上传图片到图片空间
     */
//    @RequestMapping(value = "/pic/doUpload")
//    @ResponseBody
//    public Result doUpload(MultipartFile myfile, HttpServletRequest request, HttpServletResponse response, String type,
//                           String catalogId, @CookieValue(value="t_zallgo-web", defaultValue="") String token) {
//        Result rs = new Result();
//        try {
//            StringBuffer contentType = new StringBuffer("image/");
//            myfile.transferTo(new File(myfile.getOriginalFilename()));
//            File one = new File(myfile.getOriginalFilename());
//            FilePart fp = new FilePart("myfile", one);
//            PostMethod filePost = new PostMethod("http://piccenter.zallgo.com/pic/upload");
//            fp.setContentType(contentType.append(this.getExtensionName(one.getPath())).toString());
//            Part[] parts = { fp, new StringPart("token", token),
//                    new StringPart("ut", String.valueOf(PicRoomFacadeService.USER_TYPE_FRONT)),
//                    new StringPart("groupId", catalogId), new StringPart("type", type) };
//            // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
//            MultipartRequestEntity mre = new MultipartRequestEntity(parts, filePost.getParams());
//            filePost.setRequestEntity(mre);
//            HttpClient client = new HttpClient();
//            client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);// 设置连接时间
//            int status = client.executeMethod(filePost);
//            if (status == HttpStatus.SC_OK) {
//                Map<String, Object> resultMap = JsonUtils.fromJson(filePost.getResponseBodyAsString(), Map.class);
//                if (resultMap.containsKey("state") && Integer.valueOf(resultMap.get("state").toString()) == 1) {
//                    Map data = (Map) resultMap.get("data");
//                    if (null != data) {
//                        Map picResponse = (Map) data.get("picResponse");
//                        if (picResponse == null) {
//                            rs.setState(0);
//                            rs.setSuccess(false);
//                            rs.setMessage("图片上传失败");
//                        } else {
//                            String url = picResponse.get("url").toString();
//                            String pid = picResponse.get("picRoomId").toString();
//                            Map<String, String> result = new HashMap<String, String>();
//                            result.put("url", url);
//                            result.put("pid", pid);
//                            rs.addAttribute("pic", result);
//                        }
//                    }
//                } else {
//                    rs.setState(0);
//                    rs.setSuccess(false);
//                    rs.setMessage(resultMap.get("message").toString());
//                }
//            } else {
//                rs.setState(0);
//                rs.setSuccess(false);
//                rs.setMessage("图片上传失败");
//            }
//        } catch (Exception e) {
//            rs.setState(0);
//            rs.setSuccess(false);
//            rs.setMessage("图片上传失败");
//        }
//        return rs;
//    }


    /**
     * 单文件上传
     * @param file
     * @param req
     * @return
     */
    @RequestMapping("fileUpload")
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest req){
        String filePath = saveFile(file, req);
        Map<String,String> filePathMap = Maps.newHashMap();
        filePathMap.put(file.getOriginalFilename(),filePath);
        return new Result(true,filePathMap);
    }

    /**
     * 多文件上传
     * @param files
     * @param req
     * @return
     */
    @RequestMapping("filesUpload")
    @ResponseBody
    public Result filesUpload(@RequestParam("files") MultipartFile[] files, HttpServletRequest req) {
        //判断file数组不能为空并且长度大于0
        Map<String,String> filePathMap = Maps.newHashMap();
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                String filePath = saveFile(file, req);
                if(filePath != null){
                    filePathMap.put(file.getOriginalFilename(),filePath);
                }
            }
        }
        // 重定向
        return new Result(true,filePathMap);
    }

    private String saveFile(MultipartFile file,HttpServletRequest request) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
                        + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
                return filePath;
            } catch (Exception e) {
                logger.error(file.getOriginalFilename()+"上传失败！");
            }
        }
        return null;
    }




}
