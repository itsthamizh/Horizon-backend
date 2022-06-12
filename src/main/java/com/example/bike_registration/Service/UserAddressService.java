package com.example.bike_registration.Service;

import com.example.bike_registration.Model.UserAddress;
import com.example.bike_registration.Repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public UserAddress createUserAddress(UserAddress userAddress) {
        userAddressRepository.save(userAddress);
        return userAddress;
    }

    public Optional<UserAddress> getUserAddressById(String id){
        return userAddressRepository.findById(id);
    }

    public List<UserAddress> getUserAddressByUserId(String id){
        return userAddressRepository.findByUserId(id);
    }

    public UserAddress updateUserAddress(String id, UserAddress userAddress){
        if (userAddressRepository.findById(id).isPresent()) {
            UserAddress get_userAddress = userAddressRepository.findById(id).get();

            get_userAddress.setDoor_no(userAddress.getDoor_no());
            get_userAddress.setStreet_name(userAddress.getStreet_name());
            get_userAddress.setArea(userAddress.getArea());
            get_userAddress.setCity(userAddress.getCity());
            get_userAddress.setState(userAddress.getState());
            get_userAddress.setCountry(userAddress.getCountry());
            get_userAddress.setPost_code(userAddress.getPost_code());

            UserAddress updateUserAddress = userAddressRepository.save(get_userAddress);
            return updateUserAddress;
        }
        else {
            return userAddress;
        }
    }

    public void deleteUserAddress(String id){
        userAddressRepository.deleteById(id);
    }

}
