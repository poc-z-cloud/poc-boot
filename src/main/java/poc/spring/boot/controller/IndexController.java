package poc.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class IndexController {
    @RequestMapping("/")
    String index(){
        return "index";
    }
    @RequestMapping("/console")
    String console(){
        return "console/dashboard";
    }
    @RequestMapping("/test/")
    String test(){
        return "file";
    }


}