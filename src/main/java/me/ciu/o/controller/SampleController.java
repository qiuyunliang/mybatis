package me.ciu.o.controller;

import me.ciu.o.dao.mapper.UserMapper;
import me.ciu.o.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/o/v2")
public class SampleController {

    @Autowired
    UserMapper mapper;

    // METHOD: GET, URI：http://localhost:8082/dev/o/v2/index
    // 在@Controller注解下，若没有添加@ResponseBody则会返回resources/templates目录下的index.html页面，但需要pom.xml中需要添加spring-boot-starter-thymeleaf依赖
    @RequestMapping(method = RequestMethod.GET, path = "/index")
    public String index() {
        return "index";
    }

    // METHOD: GET, URI：http://localhost:8082/dev/o/v2/user?id=1
    @RequestMapping(method = RequestMethod.GET, path = "/user")
    @ResponseBody
    public ResponseEntity queryPathParam(@PathParam("id") String id, HttpServletRequest request) {
        System.out.println(request.toString());
        return ResponseEntity.ok(mapper.selectUser(Integer.parseInt(id)));
    }

    // METHOD: GET, URI：http://localhost:8082/dev/o/v2/user/request/param?id=1
    @RequestMapping(method = RequestMethod.GET, path = "/user/request/param")
    @ResponseBody
    public User queryRequestParam(@RequestParam("id") String id, HttpServletRequest request) {
        System.out.println(request.toString());
        return mapper.selectUser(Integer.parseInt(id));
    }

    // METHOD: GET, URI：http://localhost:8082/dev/o/v2/user/1
    @RequestMapping(method = RequestMethod.GET, path = "/user/{id}")
    @ResponseBody
    public ResponseEntity queryPathVariable(@PathVariable("id") String id, HttpServletRequest request) {
        System.out.println(request.toString());
        return ResponseEntity.ok(mapper.selectUser(Integer.parseInt(id)));
    }

    // METHOD: PUT, URI：http://localhost:8082/dev/o/v2/user/10
    @RequestMapping(method = RequestMethod.PUT, path = "/user/{id}")
    @ResponseBody
    public ResponseEntity modify(@PathVariable("id") String id, HttpServletRequest request) {
        System.out.println(request.toString());
        User user = User.builder().id(Integer.parseInt(id)).userName("new").build();
        return ResponseEntity.ok(mapper.insert(user));
    }

    @RequestMapping( method = RequestMethod.POST, value = "/user",produces = "application/json")
    public ResponseEntity save(@RequestBody User user) {
        System.out.println(user.toString());
        mapper.insert(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping( method = RequestMethod.DELETE, value = "/user/{id}",produces = "application/json")
    public ResponseEntity delete(@PathVariable("id") String id, HttpServletRequest request) {
        System.out.println(id);
        return ResponseEntity.ok().build();
    }
}