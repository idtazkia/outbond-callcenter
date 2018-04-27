package id.ac.tazkia.callcenter.outbound.controller;

import id.ac.tazkia.callcenter.outbound.dao.InstitutionDao;
import id.ac.tazkia.callcenter.outbound.dao.ProspectDao;

import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ProspectController {

    @Autowired
    private ProspectDao prospectDao;
    @Autowired
    private InstitutionDao institutionDao;

    @GetMapping("/prospect/list")
    public ModelMap prospect(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("daftarProspect", prospectDao.findByNameContainingIgnoreCaseOrderByName(value, pageable));
        } else {
            return new ModelMap().addAttribute("daftarProspect", prospectDao.findAll(pageable));
        }

    }


    @GetMapping(value = "/prospect/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) String id, Model model) {
        Prospect prospect = new Prospect();
        if (id != null && !id.isEmpty()) {
            prospect = prospectDao.findById(id).get();
        } else {

        }

        model.addAttribute("prospect", prospect);
        model.addAttribute("listInstitution", institutionDao.findAll());
        return "prospect/form";
    }


    @RequestMapping(value = "/prospect/form", method = RequestMethod.POST)
    public String simpan(@Valid Prospect prospect, BindingResult errors, ModelMap mm) {
        if (errors.hasErrors()) {
            mm.addAttribute("prospect", prospect);
            mm.addAttribute("listInstitution", institutionDao.findAll());
            return "prospect/form";
        }

        prospectDao.save(prospect);
        return "redirect:/prospect/list";
    }



}
