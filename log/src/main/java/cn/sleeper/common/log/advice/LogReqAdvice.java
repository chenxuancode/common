package cn.sleeper.common.log.advice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

import static cn.sleeper.common.log.util.LogApi.blog;

/**
 * @author sleeper
 * @version 1.0
 * 2020/12/24
 */
@RestControllerAdvice
public class LogReqAdvice implements RequestBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        blog.info("{}.{} 请求报文：{}", getClassName(methodParameter), getMethodName(methodParameter), toJson(o));
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
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
