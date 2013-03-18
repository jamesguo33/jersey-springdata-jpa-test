package sample.op.eastblue.dawnisland.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sample.op.eastblue.dawnisland.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long>, TaskCustomRepository {

	List<Task> findByTitle(String title);

	@Transactional(timeout = 2, propagation = Propagation.REQUIRED)
	@Query("SELECT entity FROM Task entity WHERE entity.title like 'test%'")
	List<Task> findByGivenQuery();
	
}
