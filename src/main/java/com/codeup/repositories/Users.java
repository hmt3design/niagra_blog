package com.codeup.repositories;

import com.codeup.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Harry on 2/10/17.
 */
@Repository
public interface Users extends CrudRepository<User, Long> {
    // select * from user where username = ?
    // automagic
    public User findByUsername(String username);

}
