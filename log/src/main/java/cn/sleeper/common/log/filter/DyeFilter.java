package cn.sleeper.common.log.filter;

import cn.sleeper.common.log.constant.ContextConstant;
import cn.sleeper.common.log.util.ContextUtil;
import cn.sleeper.common.log.util.IpUtil;
import cn.sleeper.common.log.vo.GlobalContext;
import com.alibaba.fastjson.JSON;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        initGlobalContext(request);
        try {
            chain.doFilter(req, resp);
        } finally {
            ContextUtil.clearContext();
        }
    }

    private static void initGlobalContext(HttpServletRequest servletRequest) {
        GlobalContext context = new GlobalContext();
        String contextStr = servletRequest.getHeader(ContextConstant.REQUEST_CONTEXT);
        if (!StringUtils.isEmpty(contextStr)){
            context = JSON.parseObject(contextStr,GlobalContext.class);
        }else{
            context.setTraceId(UUID.randomUUID().toString());
            context.setClientIp(IpUtil.getClientAddress(servletRequest));
        }
        ContextUtil.setCurrentContext(context);
    }

    @Override
    public void init(FilterConfig config){
    }

    @Override
    public void destroy() {
    }
}
