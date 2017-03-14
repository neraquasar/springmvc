package org.borth.springmvc.persistence.repository;

import org.borth.springmvc.persistence.model.Account;
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
