package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.Campaign;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/sql/reset-data.sql", "/sql/sample-institution.sql", "/sql/sample-campaign.sql", "/sql/sample-prospect.sql"})
public class CampaignDaoTests {
    @Autowired private CampaignDao campaignDao;
    @Autowired private ProspectDao prospectDao;

    @Test @Transactional @Rollback(false)
    public void testAddProspectToCampaign(){
        Campaign c = campaignDao.findById("c001").get();

        Prospect p1 = prospectDao.findById("p001").get();
        Prospect p2 = prospectDao.findById("p002").get();
        Prospect p3 = prospectDao.findById("p003").get();
        Prospect p4 = prospectDao.findById("p004").get();
        Prospect p5 = prospectDao.findById("p005").get();

        c.getProspects().add(p1);
        c.getProspects().add(p2);
        c.getProspects().add(p3);
        c.getProspects().add(p4);
        c.getProspects().add(p5);

        campaignDao.save(c);

        Page<Prospect> pc = prospectDao.findByCampaign(c, PageRequest.of(0, 10));
        Assert.assertNotNull(pc);
        System.out.println("Jumlah record : "+pc.getTotalElements());
    }
}
