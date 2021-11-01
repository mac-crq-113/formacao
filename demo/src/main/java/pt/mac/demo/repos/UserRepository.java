package pt.mac.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.mac.demo.entities.User;

/**
 *
 * @author mario
 * @since 23/10/2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
//    Optional<User> findByUsername(String username);
}