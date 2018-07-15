package poc.spring.boot.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poc.spring.boot.domain.model.AbstractNamedDomainClass;
import poc.spring.boot.domain.model.DomainObject;
import poc.spring.boot.service.CRUDService;
import poc.spring.boot.service.NamedCRUDService;

public abstract class NamedCRUDController<D extends AbstractNamedDomainClass, S extends NamedCRUDService<D>> extends CRUDController<D, S>{

    @RequestMapping(value ="/searchByNameAndDecription", method = RequestMethod.POST)
    public String doSearchByName(TheListForm theListForm,Model model){
    	String name = theListForm.getName();
    	String description = theListForm.getDescription();
    	model.addAttribute("moduleId", this.getModuleId());
        model.addAttribute("theList", this.getService().findByNameAndDescriptionContaining(name, description));
        model.addAttribute("theListForm", theListForm);
        return toList();
    }


}
