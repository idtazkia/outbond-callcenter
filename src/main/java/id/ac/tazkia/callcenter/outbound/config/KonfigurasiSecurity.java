package id.ac.tazkia.callcenter.outbound.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN
            = "SELECT a.username AS username, b.password AS PASSWORD, a.active AS enabled FROM s_user AS a INNER JOIN "
            + "s_user_password AS b ON a.id=b.id_user WHERE a.username = ?";

    private static final String SQL_PERMISSION
            = "SELECT a.username AS username, c.permission_value AS authority FROM s_user AS a INNER JOIN "
            + "s_role_permission AS b ON a.id_role=b.id_role INNER JOIN s_permission AS c ON b.id_permission=c.id where a.username= ?";

    @Autowired
    private DataSource ds;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/list").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/user/form").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/report/list").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/prospect/list").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/prospect/form").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/prospect/upload").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/institution/list").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/institution/form").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/campaign/prospect/form").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/campaign/user/form").hasAuthority("CONFIGURE_SYSTEM")
                .antMatchers("/sesi_telepon/form").hasAuthority("SESI_TELEPON")
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and().formLogin().defaultSuccessUrl("/home",true)
                .loginPage("/login")
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/navbar-top-fixed.css")
                .antMatchers("/offcanvas.css")
                .antMatchers("/offcanvas.js")
                .antMatchers("/stei-tazkia.png")
                .antMatchers("/favicon.ico")
                .antMatchers("/signin.css")
                .antMatchers("/dist/**")
                .antMatchers("/assets/**")
                .antMatchers("/assets/js/vendor/*")
                .antMatchers("/dist/css/bootstrap.min.css")
                .antMatchers("/");

    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(17);
    }

}
