package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data @Entity @Table(name="s_role")
public class Role {

    @Id
    private String id;

    private String description;

    private String name;

}
