package com.rocky.boot.web;

import com.rocky.boot.core.CustomExcption;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rocky
 * @Description: 异常统一处理
 * @Date: Created in 2019/3/14
 */
@RestController
@RequestMapping("exception")
public class ExceptionController {

    @GetMapping("/handle")
    public void handle() {
        throw new CustomExcption("自定义异常，直接抛出，统一处理！");
    }
}
