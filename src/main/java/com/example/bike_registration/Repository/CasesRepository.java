package com.example.bike_registration.Repository;

import com.example.bike_registration.Model.Cases;
import com.example.bike_registration.Model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasesRepository extends JpaRepository<Cases, String> {

    @Query(value = "SELECT * FROM cases", nativeQuery = true)
    List<Cases> getAllCases();
}
