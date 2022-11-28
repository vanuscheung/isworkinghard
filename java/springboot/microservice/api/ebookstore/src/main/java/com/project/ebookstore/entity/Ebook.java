package com.project.ebookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eBooks")
@Data
public class Ebook {
    
    @Id
    private Long id;

    @Column(name = "ebookName")
    private String bookName;

    @Column(name = "authorName")
    private String authorName;

}
