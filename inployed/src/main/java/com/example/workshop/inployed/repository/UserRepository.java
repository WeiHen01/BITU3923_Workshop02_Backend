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
			+ " AND UserType = :userType "
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User finduserBasedOnTypeByUserId(@Param("userId")int userID, @Param("userType")String usertype);

	
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
	 * Module: Retrieve a certain user information by Username
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = username
	 * AND UserType = 'Company'
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username "
			+ " AND UserType = 'Company'",
			nativeQuery = true)
	public User findCompanyStaffByUserName(@Param("username")String username);

	/**
	 * Module: Retrieve a certain user information by userId and userType
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserID = userId
	 * AND UserType = 'Company'
	 * And UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserID = :userId "
			+ " AND UserType = 'Company'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findCompanyUserByUserId(@Param("userId")int userID);

	
	/**
	 * Module: Retrieve a certain user information by Username and UserType
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserName = username
	 * AND UserType = 'Admin'
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param UserName
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserName = :username "
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findAdminUserByUserName(@Param("username")String username);

	/**
	 * Module: Retrieve a certain user information by UserID and UserType
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserID = userId
	 * AND UserType = 'Admin'
	 * AND UserStatus = 'Active
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserID = :userId "
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findAdminUserByUserId(@Param("userId")int userID);

	/**
	 * Module: Retrieve a list of admin
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE AdminID = userId
	 * AND AdminID != UserID
	 * -------------------------------------------------------
	 * 
	 * @param userID
	 * @return
	 */
	@Query(value = "SELECT * FROM user "
			+ "WHERE AdminID = :userId "
			+ "AND AdminID != UserID", 
			nativeQuery = true)
	public List<User> ctrlUserAdmin(@Param("userId") int userID);

	/**
	 * Module: Retrieve a list of company user
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserType = 'Company'
	 * -------------------------------------------------------
	 * 
	 * @param userType
	 * @return
	 */
	@Query(value = "SELECT * FROM user "
	        + "WHERE UserType = 'Company' ",
	        nativeQuery = true)
	public List<User> ctrlUserCompany(@Param("userType") String userType);

	/**
	 * Module: Retrieve a list of job seeker 
	 * 
	 * SQL statement for admin module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserType = 'Job Seeker'
	 * -------------------------------------------------------
	 * 
	 * @param userType
	 * @return
	 */
	@Query(value = "SELECT * FROM user "
	        + "WHERE UserType = 'Job Seeker' ",
	        nativeQuery = true)
	public List<User> ctrlUserJobSeeker(@Param("userType") String userType);


	/**
	 * Module: Retrieve a user by useremail
	 * 
	 * SQL statement for user module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE UserEmail = useremail
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param userEmail
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = :usertype "
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findUserByUserEmail(@Param("usertype") String usertype, @Param("useremail")String useremail);
	
	/**
	 * Module: Retrieve a user by userType and userEmail
	 * 
	 * SQL statement for user module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE USerEmail = useremail
	 * WHERE UserType = 'Job Seeker'
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param useremail
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = 'Job Seeker'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	
	public User findJobSeekerByUserEmail(@Param("useremail")String useremail);
	
	/**
	 * Module: Retrieve a user by userType and userEmail
	 * 
	 * SQL statement for user module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE USerEmail = useremail
	 * WHERE UserType = 'Company'
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param useremail
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = 'Company'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findCompanyUserByUserEmail(@Param("useremail")String useremail);

	/**
	 * Module: Retrieve a user by userType and userEmail
	 * 
	 * SQL statement for user module:
	 * ------------------------------------------------------- 
	 * SELECT * FROM user 
	 * WHERE USerEmail = useremail
	 * WHERE UserType = 'Admin'
	 * AND UserStatus = 'Active'
	 * -------------------------------------------------------
	 * 
	 * @param useremail
	 * @return
	 */
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserType = 'Admin'"
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findAdminByUserEmail(@Param("useremail")String useremail);

	
	@Query(value="SELECT * FROM user "
			+ " WHERE UserEmail = :useremail "
			+ " AND UserStatus = 'Active'",
			nativeQuery = true)
	public User findUserByUserEmail(@Param("useremail")String useremail);

	
	@Query(value="SELECT * FROM user "
			+ "WHERE AdminID = :userId "
			+ "AND AdminID != UserID",
			nativeQuery = true)
	public List<User> controlUserList(@Param("userId") int userID);
	
	@Query(value="SELECT * FROM user "
			+ "WHERE CompanyID = :company",
			nativeQuery = true)
	public List<User> getUserbySameCompany(@Param("company") int company);
}
