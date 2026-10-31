package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicProgramController {

    @GetMapping("/basics/even-or-odd") // localhost:8080/basics/even-or-odd?number=89
    public String evenOrOdd(@RequestParam int number) {
        if (number % 2 == 0) {
            return number + " is even.";
        }
        return number + " is odd.";
    }

    @GetMapping("/basics/grade")
    public String grade(@RequestParam int marks) {
        if (marks < 0 || marks > 100) {
            return "Marks must be between 0 and 100.";
        } else if (marks >= 90) {
            return "Grade A";
        } else if (marks >= 75) {
            return "Grade B";
        } else if (marks >= 50) {
            return "Grade C";
        }
        return "Grade F";
    }

    @GetMapping("/basics/person")
    public String person(@RequestParam String name, @RequestParam int age) {
        Person person = new Person(name, age);
        return person.introduce();
    }
}