package id.ac.tazkia.callcenter.outbound.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data @Entity @Table(name="s_user_password")
public class UserPassword {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne @JoinColumn(name="id_user")
    private User user;

    @NotNull
    private String password;

}
