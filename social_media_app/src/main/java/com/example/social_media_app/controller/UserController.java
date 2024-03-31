package com.example.social_media_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.social_media_app.model.Post;
import com.example.social_media_app.model.User;
import com.example.social_media_app.payload.DeleteApiResponse;
import com.example.social_media_app.service.UserDaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path="/api/v1")
public class UserController {
	private UserDaoService userService;
	//Setter-injection---->@Autowired required here
	@Autowired
	public void setUserService(UserDaoService userService) {
		this.userService=userService;
	}
	@RequestMapping(method=RequestMethod.GET,path="/users/{id}")
	//@GetMapping(path="/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id){
		//return new ResponseEntity<User>(userService.findById(id),HttpStatus.OK);
		return ResponseEntity.ok(userService.findById(id));
		
	}
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		//return new ResponseEntity<List<User>>(userService.findAll(),HttpStatus.OK);
		return ResponseEntity.ok(userService.findAll());
	}
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
		//created me ReponseEntity.created(URI) wala jugad nagi chalega because parameter me URI(of newly created object)
		//let hai which means we will return the uri(endpoint) of getting newly created resource
	}
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@PathVariable int id){
		//return new ResponseEntity<User>(userService.updateUser(user, id),HttpStatus.OK);
		return ResponseEntity.ok(userService.updateUser(user, id));
	}
	@DeleteMapping(path="/users/{id}")
	public ResponseEntity<DeleteApiResponse> deleteUserById(@PathVariable int id){
		userService.deleteById(id);
		DeleteApiResponse response=new DeleteApiResponse("User with id:"+id+" deleted successfully");
		//return new ResponseEntity<DeleteApiResponse>(response,HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
	/*public ResponseEntity<HashMap<String, String>> deleteUserById(@PathVariable int id){
		userService.deleteById(id);
		HashMap<String,String> hm=new HashMap<String,String>();
		hm.put("message","User with id:"+id+" deleted successfully");
		//return new ResponseEntity<HashMap<String,String>>(hm,HttpStatus.OK);
		return ResponseEntity.ok(hm);
	}
	*/
	/*
	public ResponseEntity<Map<String,String>> deleteUserById(@PathVariable int id){
		userService.deleteById(id);
		return new ResponseEntity<Map<String,String>>(Map.of("message","User with id:"+id+" deleted successfully"),HttpStatus.OK);
	}*/
	
	//Since, DeleteById() method of spring data repository is similar to remove() method of EntityManager and remove() 
	//and delete() methods of hibernate has a void return type, therefore we have to make provision for response.
	//Done above by using 3 different ways
	
	//In 2no methods ko hum postController me bhi rakh sakte the(wahi better hai)
	
	//Getting all posts of a user
	@GetMapping("users/{user_id}/posts")
	public ResponseEntity<List<Post>> retrivePostsOfAUser(@PathVariable int user_id){
		return new ResponseEntity<List<Post>>(userService.getAllPostsOfAUser(user_id),HttpStatus.OK);
	}
	
	//creating post for a specific-user
	@PostMapping("users/{user_id}/posts")
	public ResponseEntity<Post> createPostForAUser(@PathVariable int user_id,@RequestBody Post post){
		return new ResponseEntity<Post>(userService.createPostForAUser(user_id, post),HttpStatus.CREATED);
	}
	
	@PatchMapping("/users/{id}")
	public ResponseEntity<User> patch(@PathVariable int id, @RequestBody Map<Object,Object> fields){
		return new ResponseEntity<User>(userService.patch(id, fields),HttpStatus.OK);
		
	}
	
	
	
}
