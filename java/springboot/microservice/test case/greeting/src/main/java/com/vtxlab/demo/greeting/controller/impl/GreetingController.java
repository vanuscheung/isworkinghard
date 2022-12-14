package com.vtxlab.demo.greeting.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vtxlab.demo.greeting.controller.GreetingOperation;
import com.vtxlab.demo.greeting.entity.Book;
import com.vtxlab.demo.greeting.service.GreetingServiceInterface;

import lombok.AllArgsConstructor;

@RestController // after @, create bean to spring context during startup
@RequestMapping(value = "/api/v1")
@AllArgsConstructor
public class GreetingController implements GreetingOperation {

    @Autowired
    GreetingServiceInterface greetingServiceInterface;

    @Override
    public String greeting() {
    //    return "hello world";
        return greetingServiceInterface.greeting() + "d"; //mock
    }

    @Override
    public List<Book> findAllBooks() {
        return greetingServiceInterface.findAllBook();
    }
    
}
