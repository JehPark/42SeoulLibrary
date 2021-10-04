package com.seoul.library.jeheon.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "books")
public class Book extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "book_id")
    private long id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;
    private String urlToYes24;
}
