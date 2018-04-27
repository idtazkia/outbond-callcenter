package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.UserPassword;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPasswordDao extends PagingAndSortingRepository<UserPassword, String> {

}
