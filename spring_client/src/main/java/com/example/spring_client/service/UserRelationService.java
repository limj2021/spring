package com.example.spring_client.service;

public interface UserRelationService {
    public String userRelationAdd(String userId,String authorityId,String uInfoId);

    public String userRelationDelete(String userId);

    public String userRelationUpdate(String userId,String authorityId,String uInfoId);

}
