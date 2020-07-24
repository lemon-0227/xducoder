package com.xdu.xducoder.Dao;

import com.xdu.xducoder.Entity.Steps;
import com.xdu.xducoder.Entity.StepsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StepsMapper {
    int deleteByExample(StepsExample example);

    int insert(Steps record);

    int insertSelective(Steps record);

    List<Steps> selectByExample(StepsExample example);

    int updateByExampleSelective(@Param("record") Steps record, @Param("example") StepsExample example);

    int updateByExample(@Param("record") Steps record, @Param("example") StepsExample example);
}