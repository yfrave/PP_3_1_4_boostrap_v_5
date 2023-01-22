package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.servises.RoleService;
import ru.kata.spring.boot_security.demo.servises.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService,
                           RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
    }
    @GetMapping("/admin")
    public String showUsers(ModelMap model,Principal principal) {

        model.addAttribute("user", userService.listUsers());
        User findByUserName = userService.findByUsername(principal.getName());
        model.addAttribute("findByUserName", findByUserName);
        return "main";
    }
    @GetMapping("/admin/{id}")
    public String showUser(@PathVariable Long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "main";
    }

    @GetMapping("/admin/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "main";
    }

    @PostMapping("/admin/new")
    public String createUser(User user) {

        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/update/{id}")
    public String update (@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUser(id));
        return "main";
    }
    @PatchMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, @PathVariable("id") Long id) {
        userService.update(id,user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/remove/{id}")
    public String remove (@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "main";
    }
    @DeleteMapping("/admin/remove/{id}")
    public String removeUser (@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

}
