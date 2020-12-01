package cn.sleeper.common.log.util;

import cn.sleeper.common.log.constant.ContextConstant;
import cn.sleeper.common.log.vo.GlobalContext;
import org.slf4j.MDC;

/**
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */
public class ContextUtil {
    private static ThreadLocal<GlobalContext> currentThreadLocal = ThreadLocal.withInitial(GlobalContext::new);

    public static void setCurrentContext(GlobalContext context) {
        currentThreadLocal.set(context);
        String traceId = context.getTraceId();
        if (traceId != null && traceId.length() > 0 && MDC.get(ContextConstant.TRACK_ID) == null) {
            MDC.put(ContextConstant.TRACK_ID, traceId);
        }
    }

    public static GlobalContext getCurrentContext() {
       return currentThreadLocal.get();
    }

    public static void clearContext() {
        MDC.clear();
        currentThreadLocal.remove();
    }
}


