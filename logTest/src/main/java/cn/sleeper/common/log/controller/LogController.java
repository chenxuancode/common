package cn.sleeper.common.log.controller;

import cn.sleeper.common.log.Req.LogTestReq;
import cn.sleeper.common.log.vo.Resp;
import cn.sleeper.common.log.vo.RespCodeEnum;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.sleeper.common.log.util.LogApi.*;

/**
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */
@RestController
@RequestMapping("/test")
public class LogController  {
    @RequestMapping
    public Resp<String> log(@RequestBody LogTestReq req) {
        blog.trace("测试业务日志 trace");
        blog.debug("测试业务日志 debug");
        blog.info("测试业务日志 info");
        blog.warn("测试业务日志 warn");
        blog.error("测试业务日志 error");

        elog.trace("测试异常日志 trace");
        elog.debug("测试异常日志 debug");
        elog.info("测试异常日志 info");
        elog.warn("测试异常日志 warn");
        elog.error("测试异常日志 error");

        slog.trace("测试统计日志 trace");
        slog.debug("测试统计日志 debug");
        slog.info("测试统计日志 info");
        slog.warn("测试统计日志 warn");
        slog.error("测试统计日志 error");

        dlog.trace("测试摘要日志 trace");
        dlog.debug("测试摘要日志 debug");
        dlog.info("测试摘要日志 info");
        dlog.warn("测试摘要日志 warn");
        dlog.error("测试摘要日志 error");
        return new Resp( "data",RespCodeEnum.SUCCESS);
    }
}
