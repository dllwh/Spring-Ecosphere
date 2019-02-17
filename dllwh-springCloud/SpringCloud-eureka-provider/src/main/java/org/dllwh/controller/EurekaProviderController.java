package org.dllwh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@Api(value = "电影Controller", tags = { "电影访问接口" })
@RequestMapping("film")
public class EurekaProviderController {

}