package cn.sleeper.common.log.vo;


/**
 * @author sleeper
 * @version 1.0
 * 2020/12/24
 */
public enum RespCodeEnum {

    SUCCESS("请求成功！", 000000),
    FAIL("请求失败！", 999999),
    PARAM_ERROR("参数异常!", 900001),
    SERVICE_ERROR("服务异常!", 900002),
    BIZ_ERROR("业务异常！", 900003);

    private final String resMsg;
    private final Integer resCode;

    RespCodeEnum(String resMsg, Integer resCode) {
        this.resMsg = resMsg;
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public Integer getResCode() {
        return resCode;
    }}
