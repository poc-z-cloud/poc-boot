package poc.spring.boot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poc.spring.boot.domain.model.DomainObject;
import poc.spring.boot.service.CRUDService;

/**
 * 
 * @author Jason
 * base controller to process list-edit-update-delete actions
 * conventions: 
 * 		1) module name is the folder name of template
 * 		2) template name follows list.html/edit.html pattern
 * 		3) template base folder starts from /console/
 * @param <D>
 * @param <S>
 */
public abstract class  CRUDController<D extends DomainObject, S extends CRUDService<D>> {

	public static final String TEMPLATE_BASE="/console/";
 
    @RequestMapping("/list")
    private String doList(Model model) {
    	model.addAttribute("module", this.getModuleName());
        model.addAttribute("theList", this.getService().listAll());
        return toList();
    }

    @RequestMapping("/new")
    public String doNew(Model model){
    	model.addAttribute("module", this.getModuleName());
        model.addAttribute(this.getModuleName(), getDomainObject());
        return toEdit();
    }

    @RequestMapping("/edit/{id}")
    private String doEdit(@PathVariable Integer id, Model model) {
    	model.addAttribute("module", this.getModuleName());
        model.addAttribute(this.getModuleName(), this.getService().getById(id));
        return toEdit();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String doSave(D domainObject){
    	this.getService().saveOrUpdate(domainObject);
//        return "redirect:/console/product/" + product.getId();
        return "redirect:" + toList();
    }
    @RequestMapping("/delete/{id}")
    public String doDelete(@PathVariable Integer id){
    	this.getService().delete(id);
        return "redirect:" + toList();
    }

    protected String toList(){
    	return TEMPLATE_BASE + this.getModuleName() + "/list";
    }
    
    protected String toEdit(){
    	return TEMPLATE_BASE + this.getModuleName() + "/edit";
    }
    
    abstract protected String getModuleName();
    abstract protected S getService();
    abstract protected DomainObject getDomainObject();
}
