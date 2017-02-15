package com.codeup.repositories;

import com.codeup.models.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**  * Created by Harry on 2/13/17.  */
@Repository
public interface RolesRepository extends CrudRepository <UserRole, Long> {
    @Query("select userRole.role from UserRole userRole, User user where user.username=?1 and userRole.userId = user.id")
    public List<String> ofUserWith(String username);
}
