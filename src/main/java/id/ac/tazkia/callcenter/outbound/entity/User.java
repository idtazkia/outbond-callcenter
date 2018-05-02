package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data @Entity @Table(name = "s_user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull @NotEmpty
    @Column(unique = true)
    private String username;

    private String email;

    @NotNull @NotEmpty
    private String fullname;

    private Boolean active;

    @NotNull
    @ManyToOne @JoinColumn(name = "id_role")
    private Role role;

    @ManyToMany(mappedBy = "users")
    private Set<Campaign> campaigns = new HashSet<>();
}
