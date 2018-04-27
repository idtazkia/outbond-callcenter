package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.Campaign;
import id.ac.tazkia.callcenter.outbound.entity.Institution;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProspectDao extends PagingAndSortingRepository<Prospect, String> {

    Page<Prospect> findByNameContainingIgnoreCaseOrderByName(String name, Pageable page);

    Page<Prospect> findByName(String name, Pageable page);


    @Query("select p from Prospect p where :campaign member of p.campaigns")
    Page<Prospect> findByCampaign(@Param("campaign") Campaign c, Pageable page);





}
