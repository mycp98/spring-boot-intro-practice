package com.marcy.intro_to_sb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RestController
public class SpringBootApplicationPeople {

        static List<Person> people = new ArrayList<>();

        public static void main(String[] args) {


            Person marcy = new Person("Marcy", 1, 23);
            Person hajr = new Person("Hajr", 2, 24);
            Person suad = new Person("Suad", 3, 24);
            Person snoop = new Person("Snoop", 4, 16);
            people.add(marcy);
            people.add(hajr);
            people.add(suad);
            people.add(snoop);

            SpringApplication.run(SpringBootApplicationPeople.class, args);


        }
        // implement getPersonById

        @GetMapping("people/{id}")  //localhost:8080/people/1
        public Person getPersonById(@PathVariable("id") Integer id) {
            // filter list and return person that matches id otherwise return null
            //loop through the list of people to match id
            for (Person person : people) {
                if (person.getId() == id) {
                    return person;
                }
            }
            return null;
        }


    @GetMapping("all-people")    //localhost:8080/all-people
    public List<Person> getAllPeople(){
        return people;
    }

        static class Person{
            private String name;
            private Integer id;
            private Integer age;

            public Person(String name, Integer id, Integer age) {
                this.name = name;
                this.id = id;
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getAge() {
                return age;
            }

            public void setAge(Integer age) {
                this.age = age;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return Objects.equals(name, person.name) && Objects.equals(id, person.id) && Objects.equals(age, person.age);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, id, age);
            }

            @Override
            public String toString() {
                return "Person{" +
                        "name='" + name + '\'' +
                        ", id=" + id +
                        ", age=" + age +
                        '}';
            }

        }
        



    }



