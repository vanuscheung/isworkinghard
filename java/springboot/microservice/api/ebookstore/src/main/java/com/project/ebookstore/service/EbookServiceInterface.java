package com.project.ebookstore.service;

import java.util.List;
import java.util.Optional;

import com.project.ebookstore.entity.Ebook;

public interface EbookServiceInterface {
    
    List<Ebook> findAllEbooks();

    Optional<Ebook> findEbookById(Long id);

    Ebook saveEbook(Ebook ebook);

    Ebook deleteEbookById(Long id);

}
