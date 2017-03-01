package org.borth.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kd on 01.03.2017.
 */
@Controller
public class GreetingController
{

    @RequestMapping("/")
    public String home()
    {
        return "test";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name, Model model)
    {
        model.addAttribute("name", name);
        return "greeting";
    }
}
