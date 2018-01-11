package utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * 获取本机IP工具类, 修复skynet的OsUtil缺陷
 *
 * @author junliao
 */
public final class IpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(IpUtil.class);

    private static final String LOCALHOST_IP = "127.0.0.1";

    private static final String HTTP_PREFIX = "http://";

    private static String HOST_IP ;

    private IpUtil() {
    }

    static {
        HOST_IP = getLocalhostIp();
    }

    /**
     * 获取本机IP
     * @return
     */
    public static String getIPAddress() {
        return HOST_IP;
    }

    private static String getLocalhostIp() {

        List<String> ips = getAllIPs();
        Iterator iterator = ips.iterator();

        String ip;
        String _ip = "";
        while (iterator.hasNext()) {
            ip = (String) iterator.next();
            LOGGER.debug(ip);
            if (!LOCALHOST_IP.equals(ip)) {
                _ip = ip;
                break;
            }
        }
        if (StringUtils.isBlank(_ip)) {//不为空则返回，skynet不判断，导致总是获取到127.0.0.1
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                ip = inetAddress.getHostAddress();
                LOGGER.debug("localhost" + ip);
                if (!StringUtils.isBlank(ip)) {
                    _ip = ip;
                }
            } catch (UnknownHostException var3) {
                LOGGER.error(var3.getMessage());
            }

            if (StringUtils.isBlank(_ip)) {
                _ip = LOCALHOST_IP;
            }
        }


        return _ip;
    }


    private static List<String> getAllIPs() {
        List<String> ips = new ArrayList<String>();
        Enumeration allNetInterfaces = null;

        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException var5) {
            LOGGER.error("getNetworkInterfaces error.", var5);
        }

        InetAddress ip = null;

        if (allNetInterfaces != null) {
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration addresses = netInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    ip = (InetAddress) addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        ips.add(ip.getHostAddress());
                    }
                }
            }
        }

        return ips;
    }

    public static String getHttpAddress() {
        return HTTP_PREFIX + HOST_IP;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
