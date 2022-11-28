package com.project.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ebookstore.entity.Ebook;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long>{
    
}
