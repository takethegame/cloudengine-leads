package com.enginecloud.dao.mapper;


import com.enginecloud.dao.dataobject.LeadsDO;
import com.enginecloud.dao.dataobject.PageQueryDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LeadsMapper {
    long insert(LeadsDO leadsDO);

    LeadsDO queryByPhoneNumber(String phoneNumber);

    int update(LeadsDO leadsDO);

    int count(PageQueryDO pageQueryDO);

    List<LeadsDO> queryList(PageQueryDO pageQueryDO);
}
