package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {

    Page<User> findByFullnameContainingIgnoreCaseOrderByFullname(String fullname, Pageable page);

}
