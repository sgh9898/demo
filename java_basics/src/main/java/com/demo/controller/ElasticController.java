package com.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Elastic Controller
 *
 * @author Song gh on 2022/3/28.
 */
@Tag(name = "ElasticSearch Controller", description = "ElasticSearch 相关")
@Slf4j
@RestController
@RequestMapping("/elastic")
public class ElasticController {
}
