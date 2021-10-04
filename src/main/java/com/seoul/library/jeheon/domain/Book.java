package com.seoul.library.jeheon.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private long id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;
    private String info;
    private String urlToYes24;

    private String checkingOut;
}
