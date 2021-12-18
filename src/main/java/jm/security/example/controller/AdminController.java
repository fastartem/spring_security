package jm.security.example.controller;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import jm.security.example.service.RoleService;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

    @GetMapping("/user-add")
    public String addUserForm(User user, ModelMap model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        return "user-add";
    }

    @PostMapping("/user-add")
    public String addUser(User user,
                          @RequestParam(value = "rolesChecked", required = false) String[] roles) {

        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, ModelMap model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user,
                             @RequestParam(value = "rolesChecked", required = false) String[] roles) {
        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.update(user);
        return "redirect:/admin";
    }
}
