package jm.security.example.controller;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

    @GetMapping("/user-add")
    public String addUserForm(User user) {
        return "user-add";
    }

    @PostMapping("/user-add")
    public String addUser(User user,
                          @RequestParam(required = false) String roleAdmin,
                          @RequestParam(required = false) String roleUser) {

        if (roleAdmin != null) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(2L, "ROLE_USER"));
            roles.add(new Role(1L, "ROLE_ADMIN"));
            user.setRoles(roles);
        } else if (roleUser != null) {
            user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        }


        userService.add(user);
        return "redirect:/admin";
    }
}
