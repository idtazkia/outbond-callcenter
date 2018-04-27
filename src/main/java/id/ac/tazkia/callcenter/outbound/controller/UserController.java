package id.ac.tazkia.callcenter.outbound.controller;


import id.ac.tazkia.callcenter.outbound.dao.RoleDao;
import id.ac.tazkia.callcenter.outbound.dao.UserDao;
import id.ac.tazkia.callcenter.outbound.dao.UserPasswordDao;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import id.ac.tazkia.callcenter.outbound.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserPasswordDao userPasswordDao;

    @GetMapping("/user/list")
    public ModelMap user(@PageableDefault(size = 10) Pageable pageable, @RequestParam(name = "value", required = false) String value, Model model){
        if (value != null) {
            model.addAttribute("key", value);
            return new ModelMap().addAttribute("daftarUser", userDao.findByFullnameContainingIgnoreCaseOrderByFullname(value, pageable));
        } else {
            return new ModelMap().addAttribute("daftarUser", userDao.findAll(pageable));
        }

    }

    @GetMapping(value = "/user/form")
    public String tampilkanForms(@RequestParam(value = "id", required = false) String id, Model model) {
        User user = new User();
        if (id != null && !id.isEmpty()) {
            user = userDao.findById(id).get();
        } else {

        }

        model.addAttribute("user", user);
        model.addAttribute("listRole", roleDao.findAll());
        return "user/form";
    }

}
