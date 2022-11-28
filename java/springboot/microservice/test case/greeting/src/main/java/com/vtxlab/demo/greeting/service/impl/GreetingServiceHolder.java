package com.vtxlab.demo.greeting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vtxlab.demo.greeting.entity.Book;
import com.vtxlab.demo.greeting.repository.BookRepository;
import com.vtxlab.demo.greeting.service.GreetingServiceInterface;

@Service // after @, create bean to spring context during startup
public class GreetingServiceHolder implements GreetingServiceInterface {
    
    @Autowired
    BookRepository bookRepository;
    
    @Override
    public String greeting() {
        return "abc";
    }

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAll();
    }

}
