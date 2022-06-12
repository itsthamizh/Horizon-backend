package com.example.bike_registration.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Cases {
    @Id
    private String id;
    private String case_name;
    private String applicant_name;
    private String address;
    private String visited_person;
    private String bake_name;
    private String bank_branch;
    private String status;
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)

    private String date;
    private String user_id;

    public Cases() {
    }

    public Cases(String id, String case_name, String applicant_name, String address, String visited_person, String bake_name, String bank_branch, String status, String date, String user_id) {
        this.id = id;
        this.case_name = case_name;
        this.applicant_name = applicant_name;
        this.address = address;
        this.visited_person = visited_person;
        this.bake_name = bake_name;
        this.bank_branch = bank_branch;
        this.status = status;
        this.date = date;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCase_name() {
        return case_name;
    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVisited_person() {
        return visited_person;
    }

    public void setVisited_person(String visited_person) {
        this.visited_person = visited_person;
    }

    public String getBake_name() {
        return bake_name;
    }

    public void setBake_name(String bake_name) {
        this.bake_name = bake_name;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
