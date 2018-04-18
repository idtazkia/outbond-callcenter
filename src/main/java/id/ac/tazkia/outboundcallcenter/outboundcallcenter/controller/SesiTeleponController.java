package id.ac.tazkia.outboundcallcenter.outboundcallcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SesiTeleponController {

    @GetMapping("/sesitelepon/list")
    public void daftarSesiTelepon(){
    }

    @GetMapping("/sesitelepon/form")
    public void  formSesiTelepon(){
    }


}
