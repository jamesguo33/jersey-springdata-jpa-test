package sample.op.eastblue.dawnisland.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.op.eastblue.dawnisland.jpa.TaskRepository;
import sample.op.eastblue.dawnisland.model.Task;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/JpaRepoTest-context.xml")
public class TaskRepoTest {
	 @Autowired TaskRepository repo;

	 @Before public void setUp() {
		 for ( long i = 0; i < 6; i++ ) {
			 repo.save( new Task( "Task " + i ) );
		 }
	 }
	 
	 @After public void tearDown() {
		 repo.deleteAll();
	 }
	 
	 @Test public void shouldRunCustomMethod() {
		 Task task = new Task( "Special One");
		 
		 // when
		 repo.createSpecialTask(task);
	 
		 // then
		 List<Task> tasks = repo.findByTitle("Special One");
		 assertNotNull(tasks);
		 assertEquals(1, tasks.size());
		 assertTrue(2 == tasks.get(0).getPriority());
	 }
}
