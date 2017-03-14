package org.borth.springmvc.persistence.service;

import org.borth.springmvc.persistence.model.Account;
import org.borth.springmvc.persistence.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kd on 13.03.2017.
 */
@Service
public class AccountService
{

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository repository;

    public Account create(Account account)
    {
        logger.debug("Creating account {}", account.toString());
        Account created = repository.save(account);
        logger.debug("Created account {}", account.toString());
        return created;
    }

    public List<Account> findAll()
    {
        List<Account> accounts = new ArrayList<>();
        repository.findAll().forEach(account -> accounts.add(account));
        logger.debug("{} accounts found", accounts.size());
        return accounts;
    }

    public long cout()
    {
        return repository.count();
    }

    public Account findByLogin(String login)
    {
        logger.debug("Looking fot user with login {}", login);
        Account found = repository.findByLogin(login);
        if (found == null)
            logger.debug("Account not found");
        else logger.debug("Found user {}", found);
        return found;
    }
}
