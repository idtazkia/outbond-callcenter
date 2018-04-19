package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @Entity
public class Institution {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty @Column(unique = true)
    private String name;
    private String description;
}
