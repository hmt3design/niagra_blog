
package com.codeup.repositories;

import com.codeup.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Table operations
// insert -> table name, columns, values
// select -> table name, columns, values
// update -> table name, columns, values
// delete -> table name, values

/**
 * Created by Harry on 2/9/17.
 */
@Repository
public interface PostsRepository extends CrudRepository<Post, Long> {

}