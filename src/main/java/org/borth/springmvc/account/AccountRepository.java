package org.borth.springmvc.account;

import org.borth.springmvc.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kd on 13.03.2017.
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long>
{
    Account findByLogin(String login);
}
