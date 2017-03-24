package org.borth.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by kd on 13.03.2017.
 */
@Controller
@SessionScope
public class LoginController
{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginController(UserDetailsService userDetailsService)
    {
        Assert.notNull(userDetailsService);
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login")
    public String showLoginForm()
    {
        logger.debug("Get login");
        return "login";
    }

    @RequestMapping(value = "/login", params = "error")
    public String showLoginForm(Model model)
    {
        logger.debug("Get login with error");
        model.addAttribute("loginError", "Login Error!!!!!!");
        return "login";
    }

    @RequestMapping("/access")
    public String verifyLogin(String username, String password, Model model)
    {
        logger.debug("Post login: login = {} password = {}", username, password);//TODO remove
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null)
        {
            logger.debug("Login failed");
            return "login?error";
        }
        else
        {
            logger.debug("Login successful. Account is {}", userDetails);
            model.addAttribute("loggedUser", userDetails);
            return "redirect:/";
        }
    }
}
