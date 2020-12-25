package cn.sleeper.common.log.vo;

import lombok.Setter;

import java.io.Serializable;

/**
 * @author sleeper
 * @version 1.0
 * 2020/12/24
 */
@Setter
public class Resp<T> implements Serializable {
    private static final long serialVersionUID = 5952689219411916553L;

    public Resp(T data, String message, Integer resCode) {
        this.data = data;
        this.message = message;
        this.resCode = resCode;
    }

    public Resp(String message, Integer resCode) {
        this.message = message;
        this.resCode = resCode;
    }


    public Resp(T data, RespCodeEnum respCodeEnum) {
        this.data=data;
        this.message = respCodeEnum.getResMsg();
        this.resCode = respCodeEnum.getResCode();
    }

    public T data;
    public String message;
    public Integer resCode;
}
