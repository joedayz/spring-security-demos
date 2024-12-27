package pe.joedayz.springsecuritywithdb.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.joedayz.springsecuritywithdb.model.MyUser;
import pe.joedayz.springsecuritywithdb.repository.MyUserRepository;

/**
 * @author josediaz
 **/
@Service
public class MyUserDetailService implements UserDetailsService {

  private final MyUserRepository myUserRepository;

  public MyUserDetailService(MyUserRepository myUserRepository) {
    this.myUserRepository = myUserRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<MyUser> myUserOptional = myUserRepository.findByUsername(username);
    if(myUserOptional.isPresent()) {
      MyUser myUser = myUserOptional.get();
      return User.builder()
          .username(myUser.getUsername())
          .password(myUser.getPassword())
          .roles(myUser.getRoles().split(","))
          .build();
    }else{
      throw new UsernameNotFoundException(username);
    }
  }
}
