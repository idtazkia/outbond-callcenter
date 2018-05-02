package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.Campaign;
import id.ac.tazkia.callcenter.outbound.entity.Prospect;
import id.ac.tazkia.callcenter.outbound.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends PagingAndSortingRepository<User, String> {

    Page<User> findByFullnameContainingIgnoreCaseOrderByFullname(String fullname, Pageable page);
    User findByUsername(String u);
    Page<User> findByUsername(String username, Pageable page);

    @Query("select u from User u where :campaign member of u.campaigns")
    Page<User> findByCampaign(@Param("campaign") Campaign c, Pageable page);

}
