package cn.sleeper.common.log.util;

import cn.sleeper.common.log.vo.GlobalContext;
import org.slf4j.MDC;

/**
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */
public class ContextUtil {
    private static ThreadLocal<GlobalContext> currentThreadLocal = new ThreadLocal<GlobalContext>() {
        @Override
        protected GlobalContext initialValue() {
            return new GlobalContext();
        }
    };

    public static void setCurrentContext(GlobalContext context) {
        currentThreadLocal.set(context);
        String traceId = context.getTraceId();
        if (traceId != null && traceId.length() > 0 && MDC.get("traceId") == null) {
            MDC.put("traceId", traceId);
        }
    }

    public static void clearContext() {
        MDC.clear();
        currentThreadLocal.remove();
    }
}


