package poc.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poc.spring.boot.domain.model.Product;
import poc.spring.boot.service.ProductService;


@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired 
    private ProductService productService;


	
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
    
}
