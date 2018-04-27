package id.ac.tazkia.callcenter.outbound.controller;


import id.ac.tazkia.callcenter.outbound.dao.CampaignDao;
import id.ac.tazkia.callcenter.outbound.dao.ProspectDao;
import id.ac.tazkia.callcenter.outbound.entity.Campaign;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class CampaignController {

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private ProspectDao prospectDao;


    @GetMapping("/campaign/list")
    public ModelMap campaign(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("daftarCampaign", campaignDao.findByNameContainingIgnoreCaseOrderByName(value, pageable));
        } else {
            return new ModelMap().addAttribute("daftarCampaign", campaignDao.findAll(pageable));
        }

    }

    @GetMapping(value = "/campaign/form")
    public void tampilkanForms(@RequestParam(value = "id", required = false) Campaign c, Model model) {
        if (c == null) {
            c = new Campaign();
        }
        model.addAttribute("campaign", c);
    }

    @RequestMapping(value = "/campaign/form", method = RequestMethod.POST)
    public String simpan(@Valid Campaign campaign, BindingResult errors, ModelMap mm) {
        if (errors.hasErrors()) {
            mm.addAttribute("campaign", campaign);
            return "campaign/form";
        }

        campaignDao.save(campaign);
        return "redirect:/campaign/list";
    }

    @ModelAttribute("daftarProspek")
    public Iterable<Prospect> daftarProspek(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name="value",required = false) String value, Model model) {
        if (value != null) {
            model.addAttribute("key", value);
            return prospectDao.findByName(value, pageable);
        }else {
            return prospectDao.findAll();
        }
    }

    @GetMapping("/campaign/prospect/form")
    public ModelMap displayAddProspect(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "id", required = false) String id) {
        return new ModelMap().addAttribute("campaign", campaignDao.findById(id).get());
    }

    @PostMapping("/campaign/prospect/form")
    public String prosesAddProspect(@RequestParam("campaign") Campaign campaign, @RequestParam("prospects")Set<Prospect> prospects) {
        for (Prospect p : prospects) {
            System.out.println("ID : "+p.getId());
        }
        campaign.setProspects(prospects);
        campaignDao.save(campaign);

        return "redirect:/campaign/list";
    }

}
