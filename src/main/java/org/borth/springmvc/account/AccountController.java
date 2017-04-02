package org.borth.springmvc.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controller to work with user accounts
 */
@Controller
public class AccountController
{

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService)
    {
        Assert.notNull(accountService);
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public String accountsGet(final Account account)
    {
        System.out.println("Get method");
        return "accounts";
    }

    @PostMapping("/accounts")
    public String accounts(final Account account)
    {
        System.out.println("Post method");
        System.out.println(account.getFirstname() + account.getLastname() + account.getLogin());
        accountService.create(account);
        return "redirect:/accounts";
    }

    @RequestMapping(value = "/accounts", params = {"save"})
    public String account(final Account account, final BindingResult bindingResult, final ModelMap model)
    {
        if (bindingResult.hasErrors())
        {
            return "accounts";
        }
        System.out.println(account.getFirstname() + account.getLastname() + account.getLogin());
        model.clear();
        return "accounts";
    }

    @ModelAttribute
    public long accountsNumber(){
        return accountService.count();
    }

    @ModelAttribute
    public List<Account> accountAll()
    {
        return accountService.findAll();
    }

   /* @ModelAttribute("accountList")
    public List<Account> accountAll()
    {

        Account account1 = new Account();
        account1.setFirstname("Konstantin");
        account1.setLastname("Dichenko");
        account1.setActive(true);
        account1.setLogin("kd");

        Account account2 = new Account();
        account2.setFirstname("Maria");
        account2.setLastname("Singer");
        account2.setActive(true);
        account2.setLogin("Zinger.Maria");

        Account account3 = new Account();
        account3.setFirstname("Evgeniy");
        account3.setLastname("Dokuchaev");
        account3.setActive(false);
        account3.setLogin("Dokuchaev.Evgeniy");

        Account[] accounts = {account1, account2, account3};

        return Arrays.asList(accounts);
    }*/
}
