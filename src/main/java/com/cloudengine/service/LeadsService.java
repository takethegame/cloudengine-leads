package com.cloudengine.service;

import com.cloudengine.dao.LeadsDAO;
import com.cloudengine.dao.dataobject.LeadsDO;
import com.cloudengine.dao.dataobject.PageQueryDO;
import com.cloudengine.vo.LeadsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        leadsDO.setName(leadsVO.getName());
        leadsDO.setDescription(leadsVO.getDescription());
        leadsDO.setEmail(leadsVO.getEmail());
        leadsDO.setPhoneNumber(leadsVO.getPhoneNumber());
        leadsDO.setUpdateDate(new Date());
        leadsDO.setCreateDate(new Date());

        return leadsDAO.createLeads(leadsDO);
    }

    public List<LeadsDO> queryForCsv(Date startDate) {
        PageQueryDO pageQueryDO = new PageQueryDO();
        pageQueryDO.setStartDate(startDate);

        int count = leadsDAO.count(pageQueryDO);
        int pages = (count + 100)/100;
        List<LeadsDO> r = new ArrayList<>();
        for(int i = 0; i < pages; ++i) {
            pageQueryDO.setPageSize(100);
            pageQueryDO.setPageStart(i * 100);
            List<LeadsDO> pd = leadsDAO.queryByPage(pageQueryDO);
            r.addAll(pd);
        }

        return r;
    }

}
