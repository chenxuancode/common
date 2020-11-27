package cn.sleeper.common.log.filter;

import cn.sleeper.common.log.util.ContextUtil;
import cn.sleeper.common.log.util.IpUtil;
import cn.sleeper.common.log.vo.GlobalContext;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.ContentHandler;
import java.util.UUID;


/**
 * 流量染色
 *
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */
@WebFilter(filterName = "DyeFilter")
public class DyeFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        initGlobalContext(request);
        try {
            chain.doFilter(req, resp);
        } finally {
            ContextUtil.clearContext();
        }
    }

    private static void initGlobalContext(HttpServletRequest request) {
        GlobalContext context = new GlobalContext();
        context.setClientIp(IpUtil.getClientAddress(request));
        context.setTraceId(UUID.randomUUID().toString());
        ContextUtil.setCurrentContext(context);
    }


    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
