package org.borth.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Controller to log in user
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

}
