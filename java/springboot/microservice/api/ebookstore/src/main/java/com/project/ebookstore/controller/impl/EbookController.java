package com.project.ebookstore.controller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ebookstore.controller.EbookOperation;
import com.project.ebookstore.entity.Ebook;
import com.project.ebookstore.service.EbookServiceInterface;

@RestController
@RequestMapping(value = "/api/v1")
public class EbookController implements EbookOperation {
    
    @Autowired
    EbookServiceInterface ebookServiceInterface;

    @Override
    public List<Ebook> findAllEbooks() {
        return ebookServiceInterface.findAllEbooks();
    }

    @Override
    public ResponseEntity<Ebook> findEbookById(Long id) {
        Optional<Ebook> oBook = ebookServiceInterface.findEbookById(id);
        if (oBook.isPresent()) {
            return ResponseEntity.ok().body(oBook.get());
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Ebook> saveEbook(Ebook ebook) {
        return ResponseEntity.ok().body(ebookServiceInterface.saveEbook(ebook));
    }

    @Override
    public ResponseEntity<Ebook> deleteEbookById(Long id) {
        return ResponseEntity.ok().body(ebookServiceInterface.deleteEbookById(id));
    }

}
