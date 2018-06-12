package com.bkip.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
//import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface PersonMapper {

    @Select("SELECT * FROM test WHERE id = #{id}")
    Person selectbyId(@Param("id") int id);

}