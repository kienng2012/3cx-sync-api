package com.java.repository;

import com.java.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * https://www.baeldung.com/spring-data-jpa-stored-procedures
 * Using Interface if only some column (without select * ) : https://stackoverflow.com/questions/64125932/error-the-column-name-id-is-not-valid-from-spring-data-jpa
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // For Oracle @Query(value = "CALL GET_ALL_GATEWAY_STATUS_ERROR(:serverId);", nativeQuery = true)
    //For SQL SERVER
    @Query(value = "EXEC [dbo].getAllUsers", nativeQuery = true)
    Optional<List<Users>> getAllUsers();
}
