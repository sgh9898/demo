package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.exception.JsonException;
import com.demo.util.ResultUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.examples.Example;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * General Controller (用于测试各项基础功能)
 *
 * @author Song gh on 2022/3/28.
 */
@Tag(name = "General Controller", description = "用于测试基础功能")
@Slf4j
@RestController
@RequestMapping("/basic")
public class GeneralController {

    @Operation(summary = "AOP 记录日志")
    @PostMapping("/aop/log")
    public Map<String, Object> aopLog() {
        return ResultUtil.success();
    }

    @Operation(summary = "Json Exception 处理")
    @PostMapping("/exception/json")
    public Map<String, Object> handleJsonException() {
        throw new JsonException("这是异常信息");
    }
}
