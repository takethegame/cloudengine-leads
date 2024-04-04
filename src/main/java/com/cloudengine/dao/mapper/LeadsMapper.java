package com.cloudengine.dao.mapper;


import com.cloudengine.dao.dataobject.LeadsDO;
import com.cloudengine.dao.dataobject.PageQueryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface LeadsMapper {
    long insert(LeadsDO leadsDO);

    LeadsDO queryByPhoneNumber(String phoneNumber);

    int update(LeadsDO leadsDO);

    int count(PageQueryDO pageQueryDO);

    List<LeadsDO> queryList(PageQueryDO pageQueryDO);
}
