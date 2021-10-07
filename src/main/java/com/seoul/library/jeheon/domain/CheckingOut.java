package com.seoul.library.jeheon.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name = "checking_out")
@NoArgsConstructor
public class CheckingOut extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "checkout_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checking_out_info_id")
    private CheckingOutInfo checkingOutInfo;

    public void setBook(Book book){
        if (!book.getCheckingOuts().contains(this)){
            book.getCheckingOuts().add(this);
        }
        this.book = book;
    }

    public static CheckingOut createCheckingOut(Book book, int quantity){
        final CheckingOut checkingOut = new CheckingOut();
        book.removeQuantity(quantity);
        checkingOut.setBook(book);
        return checkingOut;
    }

    public void setCheckOutInfo(CheckingOutInfo checkingOutInfo) {
        this.checkingOutInfo = checkingOutInfo;
    }
}
