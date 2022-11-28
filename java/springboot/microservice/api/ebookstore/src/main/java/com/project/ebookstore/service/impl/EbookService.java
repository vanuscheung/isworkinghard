package com.project.ebookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ebookstore.entity.Ebook;
import com.project.ebookstore.repository.EbookRepository;
import com.project.ebookstore.service.EbookServiceInterface;

@Service
public class EbookService implements EbookServiceInterface{
    
    @Autowired
    EbookRepository ebookRepository;

    @Override
    public List<Ebook> findAllEbooks() {
        return ebookRepository.findAll();
    }

    @Override
    public Optional<Ebook> findEbookById(Long id) {
        return ebookRepository.findById(id);
    }

    @Override
    public Ebook saveEbook(Ebook ebook) {
        return ebookRepository.save(ebook);
    }

    @Override
    public Ebook deleteEbookById(Long id) throws IllegalArgumentException {
    Ebook ebook = ebookRepository.findById(id).orElse(null);
    if (ebook != null) {
        ebookRepository.deleteById(id);}
        return ebook;
    }
    
}


