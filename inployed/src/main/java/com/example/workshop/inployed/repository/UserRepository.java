package com.example.workshop.inployed.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.workshop.inployed.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Module: User login
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = username AND UserPassword = password
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username AND UserPassword = :password"
			+ " AND UserType = 'Job Seeker'"
			+ " AND UserStatus = 'Active'", 
			nativeQuery = true)
	public User jobSeekerLogin(@Param("username")String username, 
			@Param("password")String password);
	
	
	/**
	 * Module: User login
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = username AND UserPassword = password
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username AND UserPassword = :password"
			+ " AND UserType = 'Company'"
			+ " AND UserStatus = 'Active'", 
			nativeQuery = true)
	public User CompanyLogin(@Param("username")String username, 
			@Param("password")String password);
	
	/**
	 * Module: User login
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = username AND UserPassword = password
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username AND UserPassword = :password"
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'", 
			nativeQuery = true)
	public User AdminLogin(@Param("username")String username, 
			@Param("password")String password);

	
	
	/**
	 * Module: Retrieve a certain user information by User Id
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = userId
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserID = :userId AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findByUserId(@Param("userId")int userID);
	
	/**
	 * Module: Retrieve a certain user information by User Id
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = userId
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserID = :userId "
			+ " AND UserType = 'Job Seeker'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findJobSeekerByUserId(@Param("userId")int userID);
	
	
	/**
	 * Module: Retrieve a certain user information by User Name
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = userId
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username "
			+ " AND UserType = 'Job Seeker'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findJobSeekerByUsername(@Param("username")String username);
	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findUserByUserEmail(@Param("useremail")String useremail);
	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = 'Job Seeker'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findJobSeekerByUserEmail(@Param("useremail")String useremail);
	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = 'Job Seeker'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findCompanyUserByUserEmail(@Param("useremail")String useremail);
	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findAdminByUserEmail(@Param("useremail")String useremail);
	
	/**
	 * Module: Retrieve a certain user information by User Id
	 * 
	 * SQL statement for login module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = userId
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username "
			+ " AND UserType = 'Company'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findCompanyUserByUserName(@Param("username")String username);
	
	/**
	 * To retrieve active company user based on user id
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserID = :userId "
			+ " AND UserType = 'Company'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findCompanyUserByUserId(@Param("userId")int userID);
	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username "
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findAdminUserByUserName(@Param("username")String username);
	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserID = :userId "
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findAdminUserByUserId(@Param("userId")int userID);
	
	@Query(value="SELECT * FROM user"
			+"WHERE AdminID = :userId"
			+"WHERE AdminID != UserID",
			nativeQuery = true)
	public List<User> ctrlUserAdmin(@Param("userId")int userID);
	
	@Query(value="SELECT * FROM user "
			+ "WHERE AdminID = :userId "
			+ "AND AdminID != UserID",
			nativeQuery = true)
	public List<User> controlUserList(@Param("userId") int userID);
}
