package com.example.bike_registration.Service;

import com.example.bike_registration.Model.UserAddress;

public interface userAddressServiceInterface {
    UserAddress createAddress(UserAddress userAddress);

    UserAddress getUserAddressById(String id);

    UserAddress updateUserAddress(String id, UserAddress userAddress);

    UserAddress getUserAddressByUserId(String id);
}