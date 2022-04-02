package com.example.spring_client.service;


public interface UserInfoService {
    public String userInfoAdd(String uInfoId,String email,String nackName,String phoneNumber);

    public String userInfoDelete(String uInfoId);

    public String userUpdate(String email,String phoneNumber,String nackName,String uInfoId);

}
