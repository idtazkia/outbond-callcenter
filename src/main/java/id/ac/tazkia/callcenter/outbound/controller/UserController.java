package id.ac.tazkia.callcenter.outbound.controller;


import id.ac.tazkia.callcenter.outbound.dao.RoleDao;
import id.ac.tazkia.callcenter.outbound.dao.UserDao;
import id.ac.tazkia.callcenter.outbound.dao.UserPasswordDao;
import id.ac.tazkia.callcenter.outbound.dto.UserInputDto;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import id.ac.tazkia.callcenter.outbound.entity.Role;
import id.ac.tazkia.callcenter.outbound.entity.User;
import id.ac.tazkia.callcenter.outbound.entity.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Id;
import javax.validation.Valid;

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

       model.addAttribute("user",new UserInputDto());
        model.addAttribute("listRole", roleDao.findAll());

        if (id != null && !id.isEmpty()){
            User user= userDao.findById(id).get();
            if (user != null){
                UserPassword userPassword = userPasswordDao.findByUser(user);
                UserInputDto userInputDto = new UserInputDto();
                userInputDto.setUserPassword(userPassword);
                userInputDto.setUser(user);
                userInputDto.setPassword(userPassword.getPassword());
                userInputDto.setIdUser(user.getId());
                userInputDto.setUsername(user.getUsername());
                userInputDto.setEmail(user.getEmail());
                userInputDto.setIdRole(user.getRole().getId());
                userInputDto.setFullname(user.getFullname());
                userInputDto.setActive(Boolean.TRUE);

                model.addAttribute("user", userInputDto);
            }
        }

        return "user/form";
    }

    @PostMapping("/user/form")
    public String prosesInput(@Valid UserInputDto uid, BindingResult errors){
        User user = new User();
        UserPassword userPassword = new UserPassword();

        Role role = roleDao.findById(uid.getIdRole()).get();

        user.setActive(Boolean.TRUE);
        user.setEmail(uid.getEmail());
        user.setFullname(uid.getFullname());
        user.setUsername(uid.getUsername());
        user.setRole(role);
        if (uid.getUser() != null) {
            user.setId(uid.getUser().getId());
        }


        userPassword.setPassword(uid.getPassword());
        userPassword.setUser(user);
        if (uid.getUserPassword() != null) {
            userPassword.setId(uid.getUserPassword().getId());
        }
        userDao.save(user);
        userPasswordDao.save(userPassword);



        return "redirect:/user/list";
    }

}
