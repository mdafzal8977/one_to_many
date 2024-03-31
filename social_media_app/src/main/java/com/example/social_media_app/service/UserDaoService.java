package com.example.social_media_app.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;

import com.example.social_media_app.Student;
import com.example.social_media_app.Teacher;
import com.example.social_media_app.exception.ResourceNotFoundException;
import com.example.social_media_app.model.Post;
import com.example.social_media_app.model.User;
import com.example.social_media_app.repository.PostRepository;
import com.example.social_media_app.repository.UserRepository;

@Component
public class UserDaoService {
	private UserRepository userRepository;
	private PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private Student student;
	@Autowired
	private Teacher teacher;

	// Constructor-injection
	// @Autowired not required
	public UserDaoService(UserRepository useruserRepository, PostRepository postRepository) {
		this.userRepository = useruserRepository;
		this.postRepository = postRepository;
	}

	public User findById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	public List<User> findAll() {
		System.out.println(modelMapper);
		System.out.println(student);
		System.out.println(teacher);
		// Above lines are for understanding @Configuration and @ Bean, also in main
		// method also i have tried this
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(User user, int id) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		u.setName(user.getName());
		u.setBirthDate(user.getBirthDate());
		return userRepository.save(u);
	}

	public void deleteById(int id) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.deleteById(id);
	}

	//// In 2no methods ko hum postService me bhi rakh sakte the(wahi better hai)

	// Getting all posts of a user
	public List<Post> getAllPostsOfAUser(int user_id) {
		User user = userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));
		return user.getPosts();

	}

	// creating post for a specific-user
	public Post createPostForAUser(int user_id, Post post) {
		User user = userRepository.findById(user_id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));
		post.setUser(user);
		return postRepository.save(post);

	}
	//Patch mapping

	/*public User patch(int id, Map<Object, Object> fields) {
		User u = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		for (Map.Entry<Object, Object> entry : fields.entrySet()) {
			Field field = ReflectionUtils.findRequiredField(User.class, (String) entry.getKey());
			field.setAccessible(true);
			ReflectionUtils.setField(field,u,entry.getValue());
		}
		return userRepository.save(u);

	}*/
	                 //OR
	
	public User patch(int id,Map<Object,Object> fields) {
		User user=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));
		fields.forEach((key,value)->{
			Field field=ReflectionUtils.findRequiredField(User.class,(String)key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, user, value);
		});
		return userRepository.save(user);
	}

}
