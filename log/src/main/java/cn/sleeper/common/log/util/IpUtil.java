package cn.sleeper.common.log.util;


import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */
public class IpUtil {

    /**
     * 获取客户端访问真实地址
     */
    public static String getClientAddress (HttpServletRequest request) {
        String ip = request.getHeader(X_REAL_IP);
        boolean ipExsit= ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip);
        if (ipExsit) {
            ip = request.getHeader(X_FORWARDED_FOR);
        }
        if (ipExsit) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ipExsit) {
            ip = request.getHeader(HTTP_CLIENT_IP);
        }
        if (ipExsit) {
            ip = request.getHeader(HTTP_X_FORWARDED_FOR);
        }
        if (ipExsit) {
            ip = request.getRemoteAddr();
        }
        if (LOCALHOST_1.equals(ip) || LOCALHOST_2.equals(ip)) {
            ip = getHostIp();
        }
        return ip;
    }

    private static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip =  addresses.nextElement();
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(":")){
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static final String LOCALHOST_1 = "127.0.0.1";

    private static final String LOCALHOST_2 = "0:0:0:0:0:0:0:1";

    private static final String X_REAL_IP = "X-Real-IP";

    private static final String X_FORWARDED_FOR = "X-Forwarded-For";

    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";

    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";

    private static final String UNKNOWN = "unknown";

}
