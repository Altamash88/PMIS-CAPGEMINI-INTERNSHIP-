package com.ankit.demoproject;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String introduce() {
        String adultMessage = age >= 18 ? "an adult" : "a minor";
        return "My name is " + name + " and I am " + age + " years old. I am " + adultMessage + ".";
    }
}