package id.ac.tazkia.callcenter.outbound.dto;

import id.ac.tazkia.callcenter.outbound.entity.User;
import id.ac.tazkia.callcenter.outbound.entity.UserPassword;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserInputDto {
    private User user;

    private UserPassword userPassword;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private Boolean active;

    @NotNull
    private String fullname;

    @NotNull
    private String idRole;

    @NotNull
    private String idUser;

    @NotNull
    private String password;
}
