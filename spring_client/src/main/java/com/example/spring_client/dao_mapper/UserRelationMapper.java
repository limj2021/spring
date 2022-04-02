package com.example.spring_client.dao_mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRelationMapper {
    //增加

    public int userRelationAdd(@Param("userId")String userId,
                               @Param("authorityId")String authorityId,@Param("uInfoId") String uInfoId);

    //删除

    public int userRelationDelete(@Param("userId") String userId);

    //修改

    public int userRelationUpdate(@Param("userId")String userId,
                                  @Param("authorityId")String authorityId,@Param("uInfoId") String uInfoId);

}
