package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data @Entity
@EqualsAndHashCode(of = {"name", "mobilePhone"})
@ToString(of = {"name", "mobilePhone"})
public class Prospect {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String mobilePhone;
    private String parentName;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_institution")
    private Institution institution;

    @ManyToMany(mappedBy = "prospects")
    private Set<Campaign> campaigns = new HashSet<>();
}
