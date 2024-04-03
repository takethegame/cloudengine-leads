package com.cloudengine.dao.mapper;


import com.cloudengine.dao.dataobject.LeadsDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeadsMapper {
    long insert(LeadsDO leadsDO);
}
