package domain;

public class CookieNameConstant {
    /**
     * 登录用
     */
    public static final String LOGIN_COOKIE = "bgsn";

    /**
     * 登录验证码用
     */
    public static final String VALIDATE_CODE = "bgsn1";
    /**
     * 7天自动登录用
     */
    public static final String AUTO_LOGIN_COOKIE = "bgsn2";
    /**
     * 营业执照公示验证码
     */
    public static final String LICENSE_PUBLICITY_COOKIE = "license_publicity";
    /**
     * portal登录用`
     */
    public static final String PORTAL_LOGIN_COOKIE = "portal_bgsn";
    
    /**
     * portal 登录验证码用
     */
    public static final String PORTAL_VALIDATE_CODE = "portal_bgsn1";
    
    /**
     * portal7天自动登录用
     */
    public static final String PORTAL_AUTO_LOGIN_COOKIE = "portal_bgsn2";
    
    /**
     * portal 注册验证码用
     */
    public static final String PORTAL_REGISTER_VALIDATE_CODE = "portal_register_bgsn1";

    /**
     * 分站切换站点
     */
    //设置cookie的生命周期（以秒为单位，1个月：3600*24*30）
    public static final Integer COOKIE_MAXAGE = 3600*24*30;

    //省份ID保存在cookie中的名称常量
    public static final String PROVINCESID_COOKIE_NAME = "pr";

    //市ID保存在cookie中的名称常量
    public static final String CITYID_COOKIE_NAME = "ci";

    //区域ID保存在cookie中的名称常量
    public static final String COUNTYID_COOKIE_NAME = "co";

}
