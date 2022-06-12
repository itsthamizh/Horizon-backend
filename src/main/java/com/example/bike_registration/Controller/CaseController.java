package com.example.bike_registration.Controller;

import com.example.bike_registration.Model.Cases;
import com.example.bike_registration.ResponseHandler.ResponseHandler;
import com.example.bike_registration.Service.CasesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class CaseController {

    @Autowired
    private CasesService casesService;


    @RequestMapping(value = "/addCases", method = RequestMethod.POST)
    public ResponseEntity<Object> createCases(@RequestBody Cases cases){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        cases.setId(id);
        Cases result_cases = casesService.createCases(cases);
        return ResponseHandler.generateResponse("Successfully created", HttpStatus.valueOf(200), cases);
    }

    @RequestMapping(value = "/case/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCasesById(@PathVariable String id){
        Optional<Cases> cases = casesService.getCasesById(id);
        return ResponseHandler.generateResponse(null, HttpStatus.valueOf(200), cases);
    }

    @RequestMapping(value = "/cases", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCases(){
        List<Cases> cases = casesService.getAllCases();
        return ResponseHandler.generateResponse(null, HttpStatus.valueOf(200), cases);
    }
}
