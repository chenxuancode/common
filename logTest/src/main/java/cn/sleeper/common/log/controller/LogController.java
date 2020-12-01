package cn.sleeper.common.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sleeper
 * @version 1.0
 * 2020/11/27
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class LogController {
    @GetMapping
    public void log() {
        log.info("text log");
    }
}
