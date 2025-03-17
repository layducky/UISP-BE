package UISP.repository;

import UISP.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User save(User user);
    User findByEmail(String email);
    User findByEmailAndRefreshToken(String email, String refreshToken);
    boolean existsByEmail(String email);
    boolean deleteByEmail(String email);
}
