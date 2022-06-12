package com.example.bike_registration.Service;

import com.example.bike_registration.Model.Cases;

public interface CasesServiceInterface {

    Cases createCases(Cases cases);

    Cases getCasesById(String id);

    Cases getAllCases();
}
