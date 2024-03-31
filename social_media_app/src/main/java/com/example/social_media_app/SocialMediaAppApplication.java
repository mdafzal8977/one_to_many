package com.example.social_media_app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SocialMediaAppApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SocialMediaAppApplication.class, args);
		/*ModelMapper modelMapper=new ModelMapper();*/
		var modelMapper=context.getBean(ModelMapper.class);
		System.out.println(modelMapper);
		Student student1=context.getBean(Student.class);
		Student student2=context.getBean(Student.class);
		System.out.println(student1==student2); //See result with and without @Scope("prototype)
	}

  /*  @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
		
	}*/
	
}
