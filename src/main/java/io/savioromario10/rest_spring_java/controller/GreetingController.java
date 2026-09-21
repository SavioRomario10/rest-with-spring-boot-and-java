package io.savioromario10.rest_spring_java.controller;

import java.util.concurrent.atomic.AtomicLong;

import io.savioromario10.rest_spring_java.model.Greeting;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
    return 
      new Greeting(counter.incrementAndGet(), String.format(template, name));
  }
}