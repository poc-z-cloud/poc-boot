package poc.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poc.spring.boot.domain.model.Product;
import poc.spring.boot.service.ProductService;

@Controller
@RequestMapping("mvc")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAll());
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable String id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "productshow";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("product", productService.getById(id));
        return "productform";
    }

    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){
        productService.saveOrUpdate(product);
        return "redirect:/mvc/product/" + product.getId();
    }

    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable String id){
        productService.delete(id);
        return "redirect:/mvc/products";
    }

}