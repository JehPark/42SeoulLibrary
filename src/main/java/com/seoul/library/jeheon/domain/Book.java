package com.seoul.library.jeheon.domain;

import com.seoul.library.jeheon.exception.NotEnoughBookException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "books")
@NoArgsConstructor
public class Book extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private int quantity;

    @OneToMany(mappedBy = "book")
    private List<CheckingOut> checkingOuts = new ArrayList<>();

    @Builder
    public Book(String title, String author, String publisher, int quantity) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    public boolean isAvailable(){
        return quantity > 0;
    }

    public void addQuantity(int stock){
        this.quantity += stock;
    }

    public void removeQuantity(int stock){
        if (this.quantity - stock < 0){
            throw new NotEnoughBookException("책이 모자랍니다.");
        }
        this.quantity -= stock;
    }

    public void update(String title, String author, String publisher, int quantity){
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }
}
