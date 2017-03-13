package org.borth.springmvc.controller;

import org.borth.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kd on 09.03.2017.
 */
@Controller
public class UsersController
{
    @RequestMapping("/users")
    public String users(final User user)
    {
        System.out.println("First method works");
        System.out.println(user.getFirstname() + user.getLastname() + user.getLogin());
        return "users";
    }

    @RequestMapping(value = "/users", params = {"save"})
    public String user(final User user, final BindingResult bindingResult, final ModelMap model)
    {
        if (bindingResult.hasErrors()) {
            return "users";
        }
        System.out.println(user.getFirstname() + user.getLastname() + user.getLogin());
        model.clear();
        return "users";
    }

    @ModelAttribute("userList")
    public List<User> userAll()
    {

        User user1 = new User();
        user1.setFirstname("Konstantin");
        user1.setLastname("Dichenko");
        user1.setActive(true);
        user1.setLogin("kd");

        User user2 = new User();
        user2.setFirstname("Maria");
        user2.setLastname("Singer");
        user2.setActive(true);
        user2.setLogin("Zinger.Maria");

        User user3 = new User();
        user3.setFirstname("Evgeniy");
        user3.setLastname("Dokuchaev");
        user3.setActive(false);
        user3.setLogin("Dokuchaev.Evgeniy");

        User[] users = {user1, user2, user3};

        return Arrays.asList(users);
    }
}
