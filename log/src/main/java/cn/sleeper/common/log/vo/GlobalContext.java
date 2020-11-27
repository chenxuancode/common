package cn.sleeper.common.log.vo;

/**
 * 全局上下文
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */

public class GlobalContext {
    /**
     * 全局链路ID
     */
    private String traceId;
    /**
     * 请求IP
     */
    private String clientIp;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
