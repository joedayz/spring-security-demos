package pe.joedayz.springsecuritywithdb.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.joedayz.springsecuritywithdb.model.MyUser;

/**
 * @author josediaz
 **/
@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
  Optional<MyUser> findByUsername(String username);
}
