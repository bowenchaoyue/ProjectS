package controller;

import com.google.common.collect.Maps;
import domain.CookieNameConstant;
import domain.Result;
import domain.ValidateCodeReturn;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import utils.CipherUtils;
import utils.CookieUtils;
import utils.DateUtils;
import utils.ValidateCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    //记录用户的验证码
    private Map<String,String> keyCodeMap = Maps.newConcurrentMap();
    //记录验证码创建时间
    private Map<String,Date> validCodeTimeMap = Maps.newConcurrentMap();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });

        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });

        // Timestamp 类型转换
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                Date date = DateUtils.parseDate(text);
                setValue(date==null?null:new Timestamp(date.getTime()));
            }
        });
    }

    @RequestMapping("/login/getValidateCode")
    public void getValidateCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取验证码信息
        ValidateCodeReturn validateCode = ValidateCodeUtils.getValidateCode(80, 30, 4, new Color(242, 242, 242));
        //生成获取验证码的key保存到cookie中
        String validateCodeKey = CipherUtils.MD5Encode(UUID.randomUUID().toString());
        //移除之前的cookie值
        Cookie oldCookie = CookieUtils.getCookie(CookieNameConstant.VALIDATE_CODE, req);
        String path = "/";
        CookieUtils.deleteCookie(oldCookie,resp,null,path);
        //保存到cookie
        Cookie cookie = new Cookie(CookieNameConstant.VALIDATE_CODE,validateCodeKey);
        cookie.setPath("/");
        resp.addCookie(cookie);
        keyCodeMap.put(validateCodeKey,validateCode.getValidateCode());

        logger.debug("***validCode***："+validateCode.getValidateCode());

        validCodeTimeMap.put(validateCode.getValidateCode(),new Date());
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(validateCode.getValidateCodeImg(), "jpeg", sos);
        sos.close();
    }


    public Result checkValidcode(HttpServletRequest request,String code){
        // 获取cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
//            log.error("登录校验获取验证码COOKIE信息失败，用户名【" + backendOperatorDTO.getUserName() + "】");
            return new Result(-1,false,"获取验证码失败");
        }
        // 获取cookie中的验证码KEY
        String validateCodeKey = "";
        for (Cookie cookie : cookies) {
            // 找到匹配的COOKIE信息
            if (CookieNameConstant.VALIDATE_CODE.equals(cookie.getName())) {
                validateCodeKey = cookie.getValue();
            }
        }
        if (validateCodeKey == "") {
//            log.error("登录校验获取验证码COOKIE信息失败，用户名【" + backendOperatorDTO.getUserName() + "】");
            return new Result(-1,false,"获取验证码失败");
        }
        // 去keyCodeMap中查找验证码
//        String validateCode = redisUtils.get(validateCodeKey);
        String validateCode = keyCodeMap.get(validateCodeKey);
        Date date = validCodeTimeMap.get(validateCode);
        if(date == null || validate(date)){
            return new Result(-1,false,"获取验证码失败");
        }
        //删除记录
        keyCodeMap.remove(validateCodeKey);
        validCodeTimeMap.remove(validateCode);
        if (!validateCode.equals(code)){
            return new Result(-1,false,"验证码错误");
        }else {
            return null;
        }
    }

    private boolean validate(Date date) {
        return new Date().getTime() - date.getTime()>60*1000;
    }






}
