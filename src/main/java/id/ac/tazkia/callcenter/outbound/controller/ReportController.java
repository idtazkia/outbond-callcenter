package id.ac.tazkia.callcenter.outbound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @GetMapping("/report/list")
    public void listReport(){
    }

}
