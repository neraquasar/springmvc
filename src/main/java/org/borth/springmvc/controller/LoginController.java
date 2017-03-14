package org.borth.springmvc.controller;

import org.borth.springmvc.persistence.model.Account;
import org.borth.springmvc.persistence.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by kd on 13.03.2017.
 */
@Controller
@SessionScope
public class LoginController
{

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String showLoginForm()
    {
        return "login";
    }

    @PostMapping("/login")
    public String verifyLogin(String login, Model model)
    {
        logger.debug("Post method with login = {}", login);
        Account account = accountService.findByLogin(login);
        if (account == null)
        {
            logger.debug("Login failed");
            model.addAttribute("loginError", "Login Error!!!!!!");
            return "login";
        }
        else
        {
            logger.debug("Login successful. Account is {}", account);
            model.addAttribute("loggedUser", account);
            return "redirect:/";
        }
    }
}
