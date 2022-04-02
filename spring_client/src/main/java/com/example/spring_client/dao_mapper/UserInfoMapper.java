package com.example.spring_client.dao_mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserInfoMapper {
    //增加
    public int userInfoAdd(@Param("uInfoId")  String uInfoId,@Param("email") String email,
                           @Param("phoneNumber")  String phoneNumber, @Param("nackName") String nackName);

    //删除

    public int userInfoDelete(@Param("uInfoId")  String uInfoId);

    //修改

    public int userUpdate(@Param("email") String email,@Param("phoneNumber")  String phoneNumber,
                             @Param("nackName") String nackName,@Param("uInfoId")  String uInfoId);
}
