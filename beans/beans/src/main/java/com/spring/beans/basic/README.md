# Spring bean basics

This package contains small, runnable examples for learning Spring Core beans.

Run any class with its `main` method from the IDE:

- `BeanMethodExample` - creates a bean with `@Bean` inside a `@Configuration` class.
- `ComponentBeanExample` - registers a class annotated with `@Component`.
- `ConstructorInjectionExample` - injects `MessageRepository` into `MessageService` through its constructor.
- `BeanScopeExample` - compares Spring's default singleton scope with prototype scope.

The examples use `AnnotationConfigApplicationContext`, so no web server is required. Each context is closed automatically with try-with-resources.
