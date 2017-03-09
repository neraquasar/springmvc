package org.borth.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kd on 02.03.2017.
 */
@Controller
public class HomeController
{

    @ModelAttribute("justWord")
    public String justWord(){
        return "Just word";
    }


    @RequestMapping(value = {"/", "/home"})
    public String home(Model model)
    {
        model.addAttribute("username", "KOSTYA");
        return "home";
    }

    public String getText(String text)
    {
        return text.toUpperCase();
    }
}
