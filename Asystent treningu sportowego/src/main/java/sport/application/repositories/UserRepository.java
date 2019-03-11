package sport.application.repositories;

import java.util.List;

import sport.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {

	List<User> findByNameLike(String name);
//	void updateUserAttributes(User user, HttpServletRequest req);


}
