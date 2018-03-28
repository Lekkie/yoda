package com.avantir.yoda.repository;

import com.avantir.yoda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lekanomotayo on 01/01/2018.
 */

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    //@Cacheable(value = "endpointById")
    User findByUserId(@Param("id") Long userId);
    //@Cacheable(value = "endpointByName")
    User findOneByUsername(String username);
    //User findByUsernameAllIgnoringCase(@Param("username") String username);
    //@Query("FROM Acquirer n WHERE n.status = :status")
    //List<User> findByStatus(@Param("status") int status);


}
