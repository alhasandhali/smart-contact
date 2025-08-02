package com.spring.boot.scm.repositories;

import com.spring.boot.scm.models.Contact;
import com.spring.boot.scm.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query("FROM Contact AS c WHERE c.user.id=:userId")
    Page<Contact> findContactByUser(@Param("userId") int userId, Pageable pageable);
//  Search
    List<Contact> findByNameContainingAndUser(String name, User user);

}
