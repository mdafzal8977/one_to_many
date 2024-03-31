package com.example.social_media_app.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name="user_details")
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name", length = 20)
	@NotEmpty(message="name must not be empty or null")// Not empty also covers @NotNull.       ""--->This is empty
	@NotBlank(message="name must not be blank")  // " "--->This is blank
	@Size(min = 2, max = 20,message="size of the name must be minimum of 2 and max of 20 characters")
	//@Size(min=2)

	private String name;
	@Past(message="BirthDate must be in the past")
	@NotNull(message="Birth-date must not be null")  // null---->This is null
	private LocalDate birthDate;
	@OneToMany(mappedBy ="user",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	//@JsonIgnore
	private List<Post> posts;

}
//For all the constraints put under @Column(nullable=false,length=20).These validations/constraints are at database
//level and are checked after-firing query

//To validate before firing a query--->we should use the above mentioned annotations like--> @NotNull,@Size(max=20)
//i.e in this case the Hibenate will not trigger the SQL query

//As a rule of thumb,we should prefer @NotNull annotation over @Column(nullable=false).This way,we make sure that
//validation takes place before Hibernate sends any insert or update queries to the database

/*
 * public class User {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy=GenerationType.IDENTITY) private int id;
 * 
 * We can't have 2 @Column annotations on the same field else compile error
 * 
 * @Column(name="name",nullable=false,length=20)
 * 
 * @Column(nullable=false)
 * 
 * For not null constraint(nullable=false)----> mainly used in DDL schema
 * metadata generation.This means that if we let hibernate generate the database
 * schema automatically,it applies not null constraint to the particular
 * database column private String name;
 * 
 * @Column(unique=true) private LocalDate birthDate; }
 */
//Two @Column annotation on a single attribute is not allowed that's why we have used that way
//See difference between @NotNull and @Column(nullable=false) by baeldung */
