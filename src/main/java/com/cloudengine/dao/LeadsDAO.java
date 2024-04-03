package com.cloudengine.dao;


import com.cloudengine.dao.dataobject.LeadsDO;
import com.cloudengine.dao.mapper.LeadsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeadsDAO {

    @Autowired
    private LeadsMapper leadsMapper;

    public boolean addLeads(LeadsDO leadsDO) {
        long l = leadsMapper.insert(leadsDO);
        return l > 0;
    }
}
