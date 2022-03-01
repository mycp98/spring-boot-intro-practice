package com.marcy.intro_to_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@SpringBootApplication   //this annotation starts up the web server - very important
@RestController
public class HelloSpringBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(HelloSpringBootApplication.class, args);  //this is how you run it
	}

	//localhost:8080     is the route path
	@GetMapping
	//allows us to have a method and send requests
	String greet() {
		return "Hello Marcy";
	}

	@GetMapping(params = "name")
		// localhost:8080/?name=Marcy
		//optional parameter, can include it if i want to, that's why there's a ?
	String greetName(@RequestParam("name") String name) {    //give me the value of name adn store it into a string variable name
		return "Hello " + name + " how are you today?";
	}

	//localhost:8080/ping     a different path
	@GetMapping(path = "ping")
	//cannot have duplicates of the same path
	String ping() {
		return "pong";
	}

	@GetMapping(path = "foo")
	String foo() {
		return "bar";
	}

	//2nd Method:
	@GetMapping(path = "people")  // localhost:8080/people
	List<Person> getPerson() {
		return List.of(
				new Person("Ben", 12, false, List.of("chips","sausage roll"), List.of(new Car("Lamborghini"))),
		new Person("Israel", 30, true, List.of("burgers", "pizza"), List.of(new Car("Tesla"), new Car("Toyota")))
		);
	}



//	Person getPerson(){
//		return new Person("Maya", 23);
//	}

	//Post: if we want to add something, we'll use a post request, we want to send our person to the server:
	@PostMapping(path = "people")
	//as long as they have different http requests, they can have the same paths
	void addPerson(@Valid @RequestBody Person person) {    //we receive person
		System.out.println(person);
	}


	//going to have class
	static class Person {
		private String name;
		@Max(value = 120, message = "sorry age cannot be greater than 120")
		@Min(value = 0)
		private Integer age;
		@NotNull
		private Boolean isAdult;
		private List<String> favouriteFood;  //not made NotNull in case someone doesn't have favourite food
		private List<Car> cars;

		public Person(String name, Integer age, Boolean isAdult, List<String> favouriteFood, List<Car> cars) {
			this.name = name;
			this.age = age;
			this.isAdult = isAdult;
			this.favouriteFood = favouriteFood;
			this.cars = cars;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Boolean getAdult() {
			return isAdult;
		}

		public void setAdult(Boolean adult) {
			isAdult = adult;
		}

		public List<String> getFavouriteFood() {
			return favouriteFood;
		}

		public void setFavouriteFood(List<String> favouriteFood) {
			this.favouriteFood = favouriteFood;
		}

		public List<Car> getCars() {
			return cars;
		}

		public void setCars(List<Car> cars) {
			this.cars = cars;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					", age=" + age +
					", isAdult=" + isAdult +
					", favouriteFood=" + favouriteFood +
					", cars=" + cars +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Person person = (Person) o;
			return Objects.equals(name, person.name) && Objects.equals(age, person.age) && Objects.equals(isAdult, person.isAdult) && Objects.equals(favouriteFood, person.favouriteFood) && Objects.equals(cars, person.cars);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, age, isAdult, favouriteFood, cars);
		}
	}

	record CarRecord(String brand) {}

	//The property Car Class is itself going to be an object:
	static class Car {
		@NotBlank
		private String brand;


		public Car(String brand) {
			this.brand = brand;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		@Override
		public String toString() {
			return "Car{" +
					"brand='" + brand + '\'' +
					'}';
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Car car = (Car) o;
			return Objects.equals(brand, car.brand);
		}

		@Override
		public int hashCode() {
			return Objects.hash(brand);
		}
	}




}









