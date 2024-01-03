package com.example.workshop.inployed.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop.inployed.model.Company;
import com.example.workshop.inployed.model.User;
import com.example.workshop.inployed.repository.UserRepository;


/**
 * This is the User Controller class
 * to manage all requests for web services related to User class
 * 
 * basic URL for API: http://localhost:8080/inployed/user
 * @author Edgar
 */

@RestController
@RequestMapping("/user")
public class UserController {

	
	/**
	 * web request REST API
	 * API => store data in JSON format => send to the localhost 
	 * => 8080 mysql => fetch data
	 */
	
	/**
	 * Autowired -> apply variable / method based on the repository refer to
	 */
	
	@Autowired
	private UserRepository userRepos;
	
	/**
	 * Implementation of Listing all users module
	 * @return
	 */
	@GetMapping
	public List<User> getUsers(){
		return userRepos.findAll();
	}
	
	
	/**
	 * *************************
	 *  JOB SEEKER LEVEL
	 * *************************
	 */
	
	
	/**
	 * Implementation for user login module
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> JobSeekerlogin (@RequestBody User user){
		User loginUser = userRepos.jobSeekerLogin(user.getUsername(),
				user.getUserpassword());
		
		/**
		 * If the user is not exist or no record for the user, 
		 * it will return HTTP response status code of 401
		 * 
		 * code 200 means OK
		 * code 401 means Unauthorised
		 * 
		 */
		if(loginUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("This user is not exist");
		}
		/**
		 * else it will return the user data
		 */
		else {
			return ResponseEntity.ok().body(loginUser);
		}
	}
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> UpdateUser(@PathVariable Integer id, 
			@RequestBody User updatedUser) {
		try {
            User existingUser = userRepos.findByUserId(id);

            if (existingUser == null) {
                return new ResponseEntity<>("User not found", 
                		HttpStatus.NOT_FOUND);
            }

            // Update fields if provided
            if (updatedUser.getUsername() != null) {
                existingUser.setUsername(updatedUser.getUsername());
            }

            if (updatedUser.getUserEmail() != null) {
                existingUser.setUserEmail(updatedUser.getUserEmail());
            }

            if (updatedUser.getUserPosition()!= null) {
                existingUser.setUserPosition(updatedUser.getUserPosition());
            }

            if (updatedUser.getUserpassword() != null) {
                existingUser.setUserpassword(updatedUser.getUserpassword());
            }
            userRepos.save(existingUser);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("User updated successfully", 
            		HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error updating user", 
            		HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	/**
	 * Retrieve a certain user personal information
	 * based on userId
	 * 
	 */
	@GetMapping("/jobseeker/{userId}")
	public User getJobSeekerbyId(@PathVariable Integer userId) {
		return userRepos.findJobSeekerByUserId(userId);
	}
	
	/**
	 * Retrieve a certain user personal information
	 * based on username
	 * 
	 */
	@GetMapping("/account/{username}")
	public User getUserbyName(@PathVariable String username) {
		return userRepos.findJobSeekerByUsername(username);
	}
	
	/**
	 * Implementation for soft delete certain user module
	 * 
	 * Note: 
	 * --------------------------------------------------------------------
	 * Here we do soft delete as in real world, it is invalid to delete
	 * user information from the database, hence in this case, we just only
	 * hide the information from user view by updating the account status, 
	 * let user thinks that the account is already 'deleted', but in fact 
	 * the information is still resides in the database.
	 * --------------------------------------------------------------------
	 * @param id
	 * @param userDetail
	 * @return
	 */
	@PutMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		// Optional here
		// used to represent a value that may or may not be present
		// an Optional object can either contain a non-null value (considered present) 
		// or it can contain no value at all (considered empty)
		Optional<User> userResult = userRepos.findById(id);
		
		//Default retrieved user is null / empty record
		User user = null;
		
		// if the result is found
		if (userResult.isPresent()) {
			
			// obtain the user information
			user = userResult.get();
			
			// update the user status
			user.setUserStatus("Inactive");
			userRepos.save(user);
			
			// return HTTP status response code of 200 means OK
			return ResponseEntity.ok().body(user);
		} 
		// else the result is not found
		else {
			// it will return HTTP status response code of 401
			// where 401 is unauthorized (can test at Postmann)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("fail to update");
		}
		
	}
	
	
	
	/**
	 * *************************
	 *   COMPANY USER FUNCTION
	 * *************************
	 */

	
	/**
	 * Implementation for user login module
	 * @param user
	 * @return
	 */
	@PostMapping("/companyLogin")
	public ResponseEntity<?> CompanyLogin (@RequestBody User user){
		User loginUser = userRepos.CompanyLogin(user.getUsername(),
				user.getUserpassword());
		
		/**
		 * If the user is not exist or no record for the user, 
		 * it will return HTTP response status code of 401
		 * 
		 * code 200 means OK
		 * code 401 means Unauthorised
		 * 
		 */
		if(loginUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("This user is not exist");
		}
		/**
		 * else it will return the user data
		 */
		else {
			return ResponseEntity.ok().body(loginUser);
		}
	}
	
	
	/**
	 * Retrieve a certain company user personal information
	 * based on username
	 * 
	 */
	@GetMapping("/account/company/{username}")
	public User getCompanyUserbyName(@PathVariable String username) {
		return userRepos.findCompanyUserByUserName(username);
	}
	
	/**
	 * Retrieve a certain company user personal information
	 * based in username
	 * 
	 */
	@GetMapping("/account/allcompanyUser/{username}")
	public User getCompanyUserbyUserName(@PathVariable String username) {
		return userRepos.findCompanyStaffByUserName(username);
	} 

	/**
	 * Retrieve a certain company user personal information
	 * based on userId
	 * 
	 */
	@GetMapping("/company/{userId}")
	public User getCompanyUserbyId(@PathVariable Integer userId) {
		return userRepos.findCompanyUserByUserId(userId);
	}
	
	/**
	 * Implementation for soft delete certain user module
	 * 
	 * Note: 
	 * --------------------------------------------------------------------
	 * Here we do soft delete as in real world, it is invalid to delete
	 * user information from the database, hence in this case, we just only
	 * hide the information from user view by updating the account status, 
	 * let user thinks that the account is already 'deleted', but in fact 
	 * the information is still resides in the database.
	 * --------------------------------------------------------------------
	 * @param id
	 * @param userDetail
	 * @return
	 */
	@PutMapping("/updateUserCompany/{id}/{company}")
	public ResponseEntity<?> updateUserCompany(@PathVariable("id") Integer id, 
			@PathVariable("company") int company) {
		// Optional here
		// used to represent a value that may or may not be present
		// an Optional object can either contain a non-null value (considered present) 
		// or it can contain no value at all (considered empty)
		Optional<User> userResult = userRepos.findById(id);
		
		//Default retrieved user is null / empty record
		User user = null;
		Company company1 = new Company();
		
		// if the result is found
		if (userResult.isPresent()) {
			
			// obtain the user information
			user = userResult.get();
			company1.setCompanyID(company);
			// update the user status
			user.setCompany(company1);
			userRepos.save(user);
			
			// return HTTP status response code of 200 means OK
			return ResponseEntity.ok().body(user);
		} 
		// else the result is not found
		else {
			// it will return HTTP status response code of 401
			// where 401 is unauthorized (can test at Postmann)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("fail to update");
		}
		
	}
	
	
	
	/**
	 * ********************
	 * ADMIN USER FUNCTION
	 * ********************
	 */
	
	
	/**
	 * Implementation for user login module
	 * @param user
	 * @return
	 */
	@PostMapping("/adminLogin")
	public ResponseEntity<?> AdminLogin (@RequestBody User user){
		User loginUser = userRepos.AdminLogin(user.getUsername(),
				user.getUserpassword());
		
		/**
		 * If the user is not exist or no record for the user, 
		 * it will return HTTP response status code of 401
		 * 
		 * code 200 means OK
		 * code 401 means Unauthorised
		 * 
		 */
		if(loginUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("This user is not exist");
		}
		/**
		 * else it will return the user data
		 */
		else {
			return ResponseEntity.ok().body(loginUser);
		}
	}
	
	
	/**
	 * Implementation for admin update user status module
	 * @param user
	 * @return
	 */
	@PutMapping("/updatestatus/{id}/{status}")
	public ResponseEntity<?> UpdateStatus(@PathVariable("id") Integer id, 
			@PathVariable("status") String status) {
			// Optional here
			// used to represent a value that may or may not be present
			// an Optional object can either contain a non-null value (considered present) 
			// or it can contain no value at all (considered empty)
			Optional<User> userResult = userRepos.findById(id);
			
			//Default retrieved user is null / empty record
			User user = null;
			
			// if the result is found
			if (userResult.isPresent()) {
				
				// obtain the user information
				user = userResult.get();
				
				// update the user status
				user.setUserStatus(status);
				userRepos.save(user);
				
				// return HTTP status response code of 200 means OK
				return ResponseEntity.ok().body(user);
			} 
			// else the result is not found
			else {
				// it will return HTTP status response code of 401
				// where 401 is unauthorized (can test at Postmann)
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
						body("fail to update");
			}
			
		}

	
	/**
	 * Retrieve a certain admin user personal information
	 * based in username
	 * 
	 */
	@GetMapping("/account/admin/{username}")
	public User getAdminUserbyName(@PathVariable String username) {
		return userRepos.findAdminUserByUserName(username);
	}
	/**
	 * Retrieve a certain company user personal information
	 * based in userId
	 * 
	 */
	@GetMapping("/account/adminId/{userId}")
	public User getAdminUserbyId(@PathVariable int userId) {
		return userRepos.findAdminUserByUserId(userId);
	}

	/**
	 * Retrieve a list of certain user 
	 * based in adminId
	 * 
	 */
	@GetMapping("/admin/ctrlUser/{id}")
	public List<User>getUserundercertainAdmin(@PathVariable int id){
		return userRepos.ctrlUserAdmin(id);
	}
	/**
	 * Retrieve a list of certain user 
	 * based in userType = Company
	 * 
	 */
	@GetMapping("/admin/ctrlUserCompany/{userType}")
	public List<User>getCompanyundercertainAdmin(@PathVariable String userType){
		return userRepos.ctrlUserCompany(userType);
	}
	/**
	 * Retrieve a list of certain user 
	 * based in userType = Job Seeker
	 * 
	 */
	@GetMapping("/admin/ctrlUserJobSeeker/{userType}")
	public List<User>getJobSeekerundercertainAdmin(@PathVariable String userType){
		return userRepos.ctrlUserJobSeeker(userType);
	}

	/**
	 * REGISTER FOR JOB SEEKERS AND COMPANY
	 */
	/**
	 * Implementation for user register module
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public User Register(@RequestBody User user) {
        return userRepos.save(user);
    }
	
	@GetMapping("/allusers/email/{userType}/{userEmail}")
	public User getUserbyEmail(@PathVariable("userType") String usertype, @PathVariable("userEmail") String userEmail) {
		return userRepos.findUserByUserEmail(usertype, userEmail);
	}
		
	/**
	 * Retrieve a certain user personal information
	 * based in userId
	 * 
	 */
	@GetMapping("/allUserType/{userId}")
	public User getUserbyId(@PathVariable Integer userId) {
		return userRepos.findByUserId(userId);
	}
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/resetUser/{id}/{usertype}")
	public ResponseEntity<?> ResetUserPassword(
		@PathVariable("id") Integer id, @PathVariable("usertype")String usertype, @RequestBody User updatedUser
	) {
		try {
            User existingUser = userRepos.finduserBasedOnTypeByUserId(id, usertype);

            if (updatedUser.getUserpassword() != null) {
                existingUser.setUserpassword(updatedUser.getUserpassword());
            }
            userRepos.save(existingUser);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("Reset password successfully", HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error reset password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	
	
	@GetMapping("/allusers/email/{userEmail}")
	public User getUserbyEmail(@PathVariable String userEmail) {
		return userRepos.findUserByUserEmail(userEmail);
	}
	
	/**
	 * ==============================
	 *  JOB SEEKER LEVEL
	 * ==============================
	 */
	
	@GetMapping("/jobseeker/email/{userEmail}")
	public User getJobSeeker(@PathVariable String userEmail) {
		return userRepos.findJobSeekerByUserEmail(userEmail);
	}
	
	/**
	 * ==============================
	 *  COMPANY LEVEL
	 * ==============================
	 */
	
	@GetMapping("/company/email/{userEmail}")
	public User getCompanyUserByEmail(@PathVariable String userEmail) {
		return userRepos.findCompanyUserByUserEmail(userEmail);
	}
	
	
	@GetMapping("/admin/email/{userEmail}")
	public User getAdminByEmail(@PathVariable String userEmail) {
		return userRepos.findAdminByUserEmail(userEmail);
	}
	
	/**
	 * Implementation for user update module
	 * @param user
	 * @return
	 */
	@PutMapping("/resetJobseeker/{id}")
	public ResponseEntity<?> ResetJobseekerPassword(
		@PathVariable Integer id, @RequestBody User updatedUser
	) {
		try {
            User existingUser = userRepos.findJobSeekerByUserId(id);

            if (updatedUser.getUserpassword() != null) {
                existingUser.setUserpassword(updatedUser.getUserpassword());
            }
            userRepos.save(existingUser);
            
            // return HTTP status response code of 200 means OK
            return new ResponseEntity<>("Reset password successfully", HttpStatus.OK);
         
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>("Error reset password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/**
	 * Implementation for soft delete certain user module
	 * 
	 * Note: 
	 * --------------------------------------------------------------------
	 * Here we do soft delete as in real world, it is invalid to delete
	 * user information from the database, hence in this case, we just only
	 * hide the information from user view by updating the account status, 
	 * let user thinks that the account is already 'deleted', but in fact 
	 * the information is still resides in the database.
	 * --------------------------------------------------------------------
	 * @param id
	 * @param userDetail
	 * @return
	 */
	@PutMapping("/updateCompany/{id}")
	public ResponseEntity<?> updateUserCompany(@PathVariable Integer id, @RequestBody Company company) {
		// Optional here
		// used to represent a value that may or may not be present
		// an Optional object can either contain a non-null value (considered present) 
		// or it can contain no value at all (considered empty)
		Optional<User> userResult = userRepos.findById(id);
		//Default retrieved user is null / empty record
		User updatedUser = null;
		
		// if the result is found
		if (userResult.isPresent()) {
			
			// obtain the user information
			updatedUser = userResult.get();
			
			// update the user status
			updatedUser.setCompany(company);
			userRepos.save(updatedUser);
			
			// return HTTP status response code of 200 means OK
			return ResponseEntity.ok().body(updatedUser);
		} 
		// else the result is not found
		else {
			// it will return HTTP status response code of 401
			// where 401 is unauthorized (can test at Postmann)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
					body("fail to update");
		}
		
	}
	
	
	
	/**
	 * Retrieve the list of users under certain administration user
	 * the list will not include the administration user itself
	 * @param id
	 * @return
	 */
	@GetMapping("/getUserByCompany/{id}")
	public List<User> getUserUnderSameCompany(@PathVariable int id){
		return userRepos.getUserbySameCompany(id);	
	}
}
