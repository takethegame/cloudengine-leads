package com.cloudengine.dao;


import com.cloudengine.dao.dataobject.LeadsDO;
import com.cloudengine.dao.dataobject.PageQueryDO;
import com.cloudengine.dao.mapper.LeadsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LeadsDAO {

    @Autowired
    private LeadsMapper leadsMapper;

    public boolean createLeads(LeadsDO leadsDO) {
        leadsDO.setUpdateDate(new Date());
        leadsDO.setCreateDate(new Date());
        leadsDO.setIsDelete(0);
        leadsDO.setVersion(1);
        long l = leadsMapper.insert(leadsDO);
        return l > 0;
    }

    public LeadsDO queryByPhoneNumber(String phoneNumber) {
        return leadsMapper.queryByPhoneNumber(phoneNumber);
    }

    public boolean update(LeadsDO leadsDO) {
        return leadsMapper.update(leadsDO) > 0;
    }


    public int count(PageQueryDO pageQueryDO) {
        return leadsMapper.count(pageQueryDO);
    }

    public List<LeadsDO> queryByPage(PageQueryDO pageQueryDO) {
        return leadsMapper.queryList(pageQueryDO);
    }
}
