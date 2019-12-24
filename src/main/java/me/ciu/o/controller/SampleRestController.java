package me.ciu.o.controller;

import me.ciu.o.dao.mapper.DeviceMapper;
import me.ciu.o.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// RestController注解默认集成了@Controller和@ResponseBody两个注解，接口返回会按值返回，不会跳转至.html页面
// Restful Web服务控制器只返回对象，对象数据作为JSON/XML直接写入HTTP响应。
@RestController
@RequestMapping("/o/v1")
public class SampleRestController {

    @Autowired
    DeviceMapper mapper;

    // 典型的Spring MVC REST工作流：
    // 1. 客户端以URI形式向Web服务发送请求。
    // 2. 该请求被DispatcherServlet拦截，该服务器查找Handler Mappings及其类型。
    //    应用程序上下文文件中定义的Handler Mappings部分告诉DispatcherServlet使用哪种策略根据传入请求查找控制器。
    //    Spring MVC支持三种不同类型的映射请求URI到控制器：注释，名称约定和显式映射。
    // 3. 请求由Controller处理，响应返回到DispatcherServlet，然后DispatcherServlet将调度到视图。
    @RequestMapping(method = RequestMethod.GET, path = "/index")
    public String index() {
        return "index";
    }

    // METHOD: POST, URI：http://localhost:8082/dev/o/v1/device, Body：{"id": 11444, "name": "add", "borrowTime": "2019-12-24T16:40:07.000+0000", "userId": 0}
    @RequestMapping(value = "/device", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveUser(@RequestBody Device device) {
        mapper.insertDevice(device);
        return ResponseEntity.ok().build();
    }

    // METHOD: DELETE, URI：http://localhost:8082/dev/o/v1/device/10
    @RequestMapping(value = "/device/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        System.out.println(id);
        return ResponseEntity.ok().build();
    }

    // METHOD: PUT, URI：http://localhost:8082/dev/o/v1/device/10, Body：{"id": 11444, "name": "modify", "borrowTime": "2019-12-24T16:40:07.000+0000", "userId": 0}
    @RequestMapping(value = "/device/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody Device device) {
        System.out.println(id + device.toString());
        return ResponseEntity.ok().build();
    }

    // METHOD: GET, URI：http://localhost:8082/dev/o/v1/device/10
    @RequestMapping(value = "/device/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUser(@PathVariable int id) {
        return ResponseEntity.ok(mapper.selectDevice(id));
    }
}