package pt.mac.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.mac.demo.entities.User;

/**
 * 
 * @author mario
 * @since 23/10/2021
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
}