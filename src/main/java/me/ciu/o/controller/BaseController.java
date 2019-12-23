package me.ciu.o.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class BaseController {

    @RequestMapping(method = RequestMethod.GET, path = "/index")
    public String index() {
        return "index";
    }
}