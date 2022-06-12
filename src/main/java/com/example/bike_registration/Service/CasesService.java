package com.example.bike_registration.Service;

import com.example.bike_registration.Model.Cases;
import com.example.bike_registration.Repository.CasesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CasesService {

    @Autowired
   private CasesRepository casesRepository;

    public Cases createCases(Cases cases) {
        casesRepository.save(cases);
        return cases;
    }

    public Optional<Cases> getCasesById(String id){
        Optional<Cases> cases = casesRepository.findById(id);
        return cases;
    }

    public List<Cases> getAllCases(){
        List<Cases> cases = casesRepository.getAllCases();
        return cases;
    }
}
