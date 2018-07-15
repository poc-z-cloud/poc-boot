package poc.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String doList(Model model) {
    	model.addAttribute("moduleId", this.getModuleId());
        model.addAttribute("theList", this.getService().listAll());
        model.addAttribute("theListForm", new TheListForm());
        return toList();
    }

    @RequestMapping("/new")
    public String doNew(Model model){
    	model.addAttribute("moduleId", this.getModuleId());
        model.addAttribute(this.getModuleId(), getDomainObject());
        return toEdit();
    }

    @RequestMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, Model model) {
    	model.addAttribute("moduleId", this.getModuleId());
        model.addAttribute(this.getModuleId(), this.getService().getById(id));
        return toEdit();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String doSave(@Valid D domainObject,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return toEdit();
        }    	
    	this.getService().saveOrUpdate(domainObject);
//        return "redirect:/console/product/" + product.getId();
        return "redirect:" + toList();
    }
    @RequestMapping("/delete/{id}")
    public String doDelete(@PathVariable Integer id){
    	this.getService().delete(id);
        return "redirect:" + toList();
    }
    @RequestMapping(value ="/delete", method = RequestMethod.POST)
    public String doDeleteMore(TheListForm theListForm){
    	this.getService().deleteMore(theListForm.getIds());
        return "redirect:" + toList();
    }

    protected String toList(){
    	return TEMPLATE_BASE + this.getModuleId() + "/list";
    }
    
    protected String toEdit(){
    	return TEMPLATE_BASE + this.getModuleId() + "/edit";
    }
    
    abstract protected String getModuleId();
    abstract protected S getService();
    abstract protected DomainObject getDomainObject();
}
