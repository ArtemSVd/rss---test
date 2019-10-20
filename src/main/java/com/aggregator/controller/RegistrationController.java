package com.aggregator.controller;

import com.aggregator.model.Role;
import com.aggregator.model.User;
import com.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@SessionAttributes("user")
public class RegistrationController {
    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @RequestMapping(name = "/register", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") User user){
        User userFromDb = userService.getByLogin(user.getUsername());

        ModelAndView modelAndView = new ModelAndView();

        if(userFromDb != null){
            modelAndView.addObject("message","Пользователь с таким именем уже существует!");
            modelAndView.setViewName("registration");
            return modelAndView;
        }
        modelAndView.addObject("user",user);
        user.setRoles(Collections.singleton(Role.USER));
        userService.add(user);

        modelAndView.setViewName("redirect:/login");
        return modelAndView;

    }
}
