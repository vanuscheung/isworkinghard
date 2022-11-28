package com.project.ebookstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ebookstore.entity.Ebook;

@RequestMapping(value = "/default")
public interface EbookOperation {
    
    @GetMapping(value = "/ebooks")
    List<Ebook> findAllEbooks();

    @GetMapping(value = "/ebook/id/{id}")
    Ebook findEbookById(@PathVariable Long id);
    
    @PostMapping(value = "/ebook/id")
    ResponseEntity<Ebook> saveEbook(@RequestBody Ebook ebook);

    @DeleteMapping(value = "/ebook/id/{id}")
    ResponseEntity<Ebook> deleteEbookById(@PathVariable Long id);
}
