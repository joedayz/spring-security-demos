package pe.joedayz.springsecuritywithdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.joedayz.springsecuritywithdb.service.MyUserDetailService;

/**
 * @author josediaz
 **/
@Configuration
public class SecurityConfiguration {

  MyUserDetailService myUserDetailService;

  public SecurityConfiguration(MyUserDetailService myUserDetailService) {
    this.myUserDetailService = myUserDetailService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.authorizeHttpRequests(
            authorize -> {
              authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
              authorize.requestMatchers("/login", "/error/**", "/logout", "/", "/home").permitAll();
              authorize.requestMatchers("/admin/**").hasRole("ADMIN");
              authorize.requestMatchers("/user/**").hasRole("USER");
              authorize.anyRequest().authenticated();
            }
        ).formLogin(formLogin -> formLogin
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll())
        .logout(logout -> logout.logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        )
        .csrf(AbstractHttpConfigurer::disable).build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return myUserDetailService;
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(myUserDetailService);
    authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
    return authenticationProvider;
  }


  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
