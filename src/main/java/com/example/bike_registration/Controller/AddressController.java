package com.example.bike_registration.Controller;

import com.example.bike_registration.Model.UserAddress;
import com.example.bike_registration.ResponseHandler.ResponseHandler;
import com.example.bike_registration.Service.UserAddressService;
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
public class AddressController {

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public ResponseEntity<Object> createAddress(@RequestBody UserAddress userAddress){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        userAddress.setId(id);
        UserAddress user_result = userAddressService.createUserAddress(userAddress);
        return ResponseHandler.generateResponse("Successfully added UserAddress!", HttpStatus.OK, user_result);
    }

    @RequestMapping(value = "/getAddress/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserAddressById(@PathVariable("id") String id){
        Optional<UserAddress> userAddress = userAddressService.getUserAddressById(id);
        return ResponseHandler.generateResponse(null, HttpStatus.valueOf(200), userAddress);
    }

    @RequestMapping(value = "/updateAddress/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateAddress(@RequestBody UserAddress userAddress , @PathVariable("id") String id){
        UserAddress getUserAddress = userAddressService.updateUserAddress(id, userAddress);
        return ResponseHandler.generateResponse("Update Successfully", HttpStatus.valueOf(200), getUserAddress);
    }

    @RequestMapping(value = "/getAddressByUserId/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAddressByUserId(@PathVariable("id") String id){
        List<UserAddress> userAddress = userAddressService.getUserAddressByUserId(id);
        return ResponseHandler.generateResponse(null, HttpStatus.valueOf(200), userAddress);
    }

    @RequestMapping(value = "/deleteAddress/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUserAddress(@PathVariable("id") String id){
        userAddressService.deleteUserAddress(id);
        return ResponseHandler.generateResponse("Successfully deleted..!", HttpStatus.valueOf(200), null);
    }

}
