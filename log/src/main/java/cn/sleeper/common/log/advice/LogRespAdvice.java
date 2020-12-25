package cn.sleeper.common.log.advice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


import static cn.sleeper.common.log.util.LogApi.blog;

/**
 * @author sleeper
 * @version 1.0
 * 2020/12/24
 */
@RestControllerAdvice
public class LogRespAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        blog.info("{}.{}() 响应报文：{}", getClassName(methodParameter), getMethodName(methodParameter), toJson(o));
        return o;
    }
    private String getClassName(MethodParameter methodParameter) {
        String s = methodParameter.getContainingClass().toString();
        return s.substring(s.lastIndexOf(".") + 1);
    }

    private String getMethodName(MethodParameter methodParameter) {
        return methodParameter.getMethod().getName();
    }

    private String toJson(Object result) {
        return result instanceof String ? (String) result : JSONObject.toJSONString(result);
    }

}
