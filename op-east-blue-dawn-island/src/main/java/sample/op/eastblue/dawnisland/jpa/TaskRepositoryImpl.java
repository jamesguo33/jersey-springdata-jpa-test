package sample.op.eastblue.dawnisland.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import sample.op.eastblue.dawnisland.model.Task;


public class TaskRepositoryImpl implements TaskCustomRepository {

	@PersistenceContext
	EntityManager em;
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	@Override
	public Task createSpecialTask(Task task) {
		
		task.setPriority(2);
		Task merged = em.merge(task);
		return merged;
	}

}
