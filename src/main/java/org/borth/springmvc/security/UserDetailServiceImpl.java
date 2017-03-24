package org.borth.springmvc.security;

import org.borth.springmvc.account.Account;
import org.borth.springmvc.account.AccountService;
import org.borth.springmvc.account.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * Implementation of UserDetailsService
 *
 * @author Konstantin Valerievich Dichenko
 * @version 1.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService
{

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private final AccountService accountService;

    @Autowired
    public UserDetailServiceImpl(AccountService accountService)
    {
        Assert.notNull(accountService);
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        logger.debug("Loading user by name \"{}\"", username);
        Account account = accountService.findByLogin(username);
        if (account == null)
        {
            logger.debug("User with name \"{}\"", username);
            throw new UsernameNotFoundException("User not found");
        }
        User user = accountToUser(account);
        return user;
    }

    private User accountToUser(Account account)
    {
        SimpleGrantedAuthority authority = roleToGrantedAuthority(account.getRole());
        return new User(account.getLogin(), account.getPassword(), Collections.singleton(authority));
    }

    private SimpleGrantedAuthority roleToGrantedAuthority(Role role)
    {
        return new SimpleGrantedAuthority(role.toString());
    }
}
