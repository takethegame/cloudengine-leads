package com.cloudengine.service;

import com.cloudengine.dao.LeadsDAO;
import com.cloudengine.dao.dataobject.LeadsDO;
import com.cloudengine.vo.LeadsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LeadsService {
    @Autowired
    private LeadsDAO leadsDAO;

    public boolean addLeads(LeadsVO leadsVO) {

        LeadsDO queryResult = leadsDAO.queryByPhoneNumber(leadsVO.getPhoneNumber());
        if (null != queryResult) {
            queryResult.setDescription(leadsVO.getDescription());
            queryResult.setPhoneNumber(leadsVO.getPhoneNumber());
            queryResult.setEmail(leadsVO.getEmail());
            queryResult.setName(leadsVO.getName());
            return leadsDAO.update(queryResult) ;
        }

        LeadsDO leadsDO = new LeadsDO();
        leadsDO.setName(leadsDO.getName());
        leadsDO.setDescription(leadsVO.getDescription());
        leadsDO.setEmail(leadsVO.getEmail());
        leadsDO.setPhoneNumber(leadsVO.getPhoneNumber());
        leadsDO.setUpdateDate(new Date());
        leadsDO.setCreateDate(new Date());

        return leadsDAO.createLeads(leadsDO);


    }


}
