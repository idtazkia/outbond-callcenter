package id.ac.tazkia.callcenter.outbound.dao;

import id.ac.tazkia.callcenter.outbound.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleDao extends PagingAndSortingRepository<Role, String> {

    List<Role> findByName(String name);

}
