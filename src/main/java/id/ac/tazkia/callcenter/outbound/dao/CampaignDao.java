package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CampaignDao extends PagingAndSortingRepository<Campaign, String> {

    Page<Campaign> findByNameContainingIgnoreCaseOrderByName(String name, Pageable page);

}
