package com.marcy.intro_to_sb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class SpringBootApplicationCounter {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationCounter.class, args);
    }
    static int count = 0;  //don't put in the main

    // Create a method to get current count. i.e localhost:8080/current-count
    @GetMapping("current-count")
    int count () {
        return count;
    }

    // Create method to increment the count. i.e localhost:8080/increment-count
    @GetMapping("increment-count")
    int incrementCount(){
        return count++;    //return the value and then count
    }
    // Create method to decrement the count. i.e localhost:8080/decrement-count
    @GetMapping("decrement-count")
    int decrementCount(){
        return count--;
    }









}





