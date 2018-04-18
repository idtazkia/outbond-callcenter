package id.ac.tazkia.outboundcallcenter.outboundcallcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataProspekController {

    @GetMapping("/dataprospek/list")
    public void daftarProspek(){
    }

    @GetMapping("/dataprospek/form")
    public void  formDataProspek(){
    }

    @GetMapping("/dataprospek/upload")
    public void  uploadDataProspek(){
    }

}
