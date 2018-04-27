package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.Institution;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface InstitutionDao extends PagingAndSortingRepository<Institution, String> {

    List<Institution> findByName(String name);

}
