package cn.sleeper.common.log.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sleeper
 * @version 1.0
 * 2020/12/3
 */
public interface LogApi {
    Logger dlog = LoggerFactory.getLogger("digestLogger");
    Logger blog = LoggerFactory.getLogger("bizLogger");
    Logger slog = LoggerFactory.getLogger("statLogger");
    Logger elog = LoggerFactory.getLogger("errorLogger");
}
