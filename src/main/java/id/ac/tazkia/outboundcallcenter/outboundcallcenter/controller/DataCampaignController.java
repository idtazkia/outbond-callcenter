package id.ac.tazkia.outboundcallcenter.outboundcallcenter.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataCampaignController {
    @GetMapping("/datacampaign/list")
    public void daftarCampaign(){
    }

    @GetMapping("/datacampaign/form")
    public void  formDataCampaign(){
    }
}
