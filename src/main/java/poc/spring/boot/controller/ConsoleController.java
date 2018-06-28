package poc.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/console")
public class ConsoleController {
    @RequestMapping("/")
    public String root(Model model) {
    	model.addAttribute("module", "dashboard");
        return "console/dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
    	model.addAttribute("module", "dashboard");
        return "console/dashboard";
    }

    @RequestMapping("/main")
    public String main() {
        return "console/main";
    }

    @RequestMapping("/guru")
    public String guru(Model model) {
    	model.addAttribute("module", "guru");
        return "console/guru";
    }

    @RequestMapping("/user-list")
    public String userList(Model model) {
    	model.addAttribute("module", "user-list");
        return "console/user-list";
    }

    @RequestMapping("/role-list")
    public String roleList(Model model) {
    	model.addAttribute("module", "role-list");
        return "console/role-list";
    }
    
}
