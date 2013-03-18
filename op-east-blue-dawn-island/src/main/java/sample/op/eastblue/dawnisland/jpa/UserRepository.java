package sample.op.eastblue.dawnisland.jpa;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sample.op.eastblue.dawnisland.model.User;


public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByFullName(String fullName);

	List<User> findByFullName(String fullName, Sort sort);

	List<User> findByFullName(String fullName, Pageable paging);
	
	@Transactional(timeout = 2, propagation = Propagation.REQUIRED)
	@Query("SELECT u FROM User u WHERE u.fullName = 'User 3'")
	List<User> findByGivenQuery();
	
	List<User> findByIdAndFullName(@Param("id") Long id, @Param("fullName") String fullname);

	List<User> findByFullNameIgnoreCase(String string);
	
}
