package sample.op.eastblue.dawnisland.jpa;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.op.eastblue.dawnisland.jpa.UserRepository;
import sample.op.eastblue.dawnisland.model.User;


/**
 * Tests for Spring Data JPA.
 * 
 * @author tobias.trelle
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/JpaRepoTest-context.xml")
public class UserRepoTest {
	
	 @Autowired UserRepository repo;

	 @Before public void setUp() {
		 for ( long i = 0; i < 6; i++ ) {
			 repo.save( new User( "User " + i ) );
		 }
	 }
	 @After  public void teardown() {
		 for ( long i = 0; i < 6; i++ ) {
			 repo.deleteAll();
		 }
	 }
	 
	 @Test public void shouldPageUsers() {
		 List<User> users;
		 
		 // when
		 Page<User> page = repo.findAll( new PageRequest(2, 2 ) );
		 users = page.getContent();
	 
		 // then
		 assertUserCount(users, 2);
	 }

	 @Test public void shouldPageWithSort() {
		 List<User> users;
		 
		 // when
		 Page<User> page = repo.findAll( new PageRequest(1, 2, Sort.Direction.DESC, "fullName" ) );
		 users = page.getContent();
	 
		 // then
		 assertUserCount(users, 2);
		 assertEquals(users.get(0).getFullName(), "User 3");// p0[5,4],p1[3,2],p2[1,0]
	 }
	 
	 @Test public void shouldFindByFullnameQuery() {
		 List<User> users;
		 
		 // when
		 users = repo.findByFullName("User 5");
		 
		 // then
		 assertUserByFullName(users, "User 5");
	 }

	 @Test public void shouldFindByFullnameIgnoreCaseQuery() {
		 List<User> users;
		 
		 // when
		 users = repo.findByFullNameIgnoreCase("user 5");
		 
		 // then
		 assertUserByFullName(users, "User 5");
	 }
 
	 @Test public void shouldFindByFullnameQueryWithSort() {
		 List<User> users;
		 
		 // when
		 users = repo.findByFullName("User 5", new Sort( new Sort.Order(Sort.Direction.DESC,"fullName")));
		 
		 // then
		 assertUserByFullName(users, "User 5");
	 }
	 
	 
	 @Test public void shouldUseSpringDataQuery() {
		 List<User> users;
		 
		 // when
		 users = repo.findByGivenQuery();
		 
		 // then
		 assertUserByFullName(users, "User 3");
	 }	 
	 
	 @Test public void shouldIgnoreNullQueryParameters() {
		 List<User> usersById, usersByFullName;

		 // when
		 usersById = repo.findByIdAndFullName(1L, null);
		 usersByFullName = repo.findByIdAndFullName(null, "User 01");
		 
		 // then
		 assertUserCount(usersById, 0);
		 assertUserCount(usersByFullName, 0);
	 }
	 
	 @Test public void shouldSortByTwoCriteria() {
		 List<User> users;
		 
		 // when
		 users = repo.findAll( new Sort(
				 new Sort.Order(Sort.Direction.ASC, "id"),
				 new Sort.Order(Sort.Direction.DESC, "fullName")
				 )
		 );
		 
		 // then
		 assertUserCount(users, 6);
	 }
	 
	 private static void assertUserByFullName(List<User> users, String fullName)  {
		 assertUserCount(users, 1);
		 assertEquals( "Mismatch full name" , fullName, users.get(0).getFullName());
	 }

	 private static void assertUserCount(List<User> users, int expected) {
		 assertNotNull( users );
		 assertEquals( "Mismatch user count" , expected, users.size());
		 
	 }
	 
}
