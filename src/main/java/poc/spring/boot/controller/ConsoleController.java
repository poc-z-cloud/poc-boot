package poc.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import poc.spring.boot.domain.model.ModuleGroup;
import poc.spring.boot.domain.model.Product;
import poc.spring.boot.service.CRUDService;
import poc.spring.boot.service.ModuleGroupService;
import poc.spring.boot.service.ModuleService;
import poc.spring.boot.service.ProductService;


@Controller
@RequestMapping("/console")
@SessionAttributes("moduleGroupList")
public class ConsoleController {

    @Autowired 
    private ProductService productService;

    @Autowired 
    private ModuleGroupService moduleGroupService;
    @Autowired 
    private ModuleService moduleService;
    
	
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
    
//    @RequestMapping("/menu-group-list")
//    public String menuGroupList(Model model) {
//    	model.addAttribute("module", "menu-group");
//        model.addAttribute("menuGroupList", menuGroupService.listAll());
//        return "console/menu-group-list";
//    }

    @RequestMapping("/menu-group-list")
    public String menuGroupList(Model model) {
    	return goList(model,"menu-group", moduleGroupService);
    }

    @RequestMapping("/menu-list")
    public String menuList(Model model) {
    	return goList(model,"menu", moduleService);
    }

//    @RequestMapping("/menu/edit/{id}")
    public String menuEdit(@PathVariable Integer id, Model model){
    	return goEdit(model,"menu", moduleService,id);
    }
    
    private String goList(Model model, String module, CRUDService crudService) {
    	model.addAttribute("module", module);
        model.addAttribute("theList", crudService.listAll());
        return "console/" + module + "-list";
    }

    private String goEdit(Model model, String module, CRUDService crudService, Integer id) {
    	model.addAttribute("module", module);
        model.addAttribute(module, crudService.getById(id));
        return "console/" + module + "-edit";
    }
    
    @RequestMapping("/product-list")
    public String productList(Model model) {
    	model.addAttribute("module", "product-list");
        model.addAttribute("products", productService.listAll());
        return "console/product-list";
    }
    
    @RequestMapping("/product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
    	model.addAttribute("module", "product-list");
        model.addAttribute("product", productService.getById(id));
        return "console/product-show";
    } 
    
    @RequestMapping("/product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
    	model.addAttribute("module", "product-list");
        model.addAttribute("product", productService.getById(id));
        return "console/product-edit";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model){
    	model.addAttribute("module", "product-list");
        model.addAttribute("product", new Product());
        return "console/product-edit";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveOrUpdate(product);
//        return "redirect:/console/product/" + product.getId();
        return "redirect:/console/product-list";
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/console/product-list";
    }
    
    @ModelAttribute("moduleGroupList")
    public List<ModuleGroup> retrieveModuleGroupList(){
    	return  (List<ModuleGroup>) moduleGroupService.listAll();    //return all for now	
    }
}
