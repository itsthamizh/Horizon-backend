package com.example.bike_registration.Repository;

import com.example.bike_registration.Model.UserAddress;
import org.hibernate.SQLQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserAddressRepository extends JpaRepository<UserAddress, String> {

    @Query(value = "SELECT * FROM user_address WHERE user_id = ?1", nativeQuery = true)
    List<UserAddress> findByUserId(String user_id);

}
