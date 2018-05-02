package id.ac.tazkia.callcenter.outbound.controller;

import id.ac.tazkia.callcenter.outbound.dao.InstitutionDao;
import id.ac.tazkia.callcenter.outbound.entity.Institution;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class InstitutionController {

    @Autowired
    private InstitutionDao institutionDao;

    @GetMapping("/institution/list")
    public ModelMap institution(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("daftarInstitution", institutionDao.findByNameContainingAndDescriptionContainingIgnoreCaseOrderByName(value, pageable));
        } else {
            return new ModelMap().addAttribute("daftarInstitution", institutionDao.findAll(pageable));
        }

    }


    @GetMapping(value = "/institution/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) String id, Model model) {
        Institution institution = new Institution();
        if (id != null && !id.isEmpty()) {
            institution = institutionDao.findById(id).get();
        } else {

        }

        model.addAttribute("institution", institution);
        model.addAttribute("listInstitution", institutionDao.findAll());
        return "institution/form";
    }


    @RequestMapping(value = "/institution/form", method = RequestMethod.POST)
    public String simpan(@Valid Institution institution, BindingResult errors, ModelMap mm) {
        if (errors.hasErrors()) {
            mm.addAttribute("institution", institution);
            mm.addAttribute("listInstitution", institutionDao.findAll());
            return "institution/form";
        }

        institutionDao.save(institution);
        return "redirect:/institution/list";
    }

}
