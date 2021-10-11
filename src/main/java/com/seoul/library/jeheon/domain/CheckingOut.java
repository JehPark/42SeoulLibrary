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
    public static final int BOOK_STOCK = 1;
    public static final int RENT_DAY = 14;

    @Id @GeneratedValue
    @Column(name = "checkout_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checking_out_info_id")
    private CheckingOutInfo checkingOutInfo;

    private LocalDateTime checkOutDate;
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private CheckOutStatus checkOutStatus;

    @Builder
    public CheckingOut(Book book, CheckingOutInfo checkingOutInfo, LocalDateTime checkOutDate, LocalDateTime expirationDate, CheckOutStatus checkOutStatus) {
        this.book = book;
        this.checkingOutInfo = checkingOutInfo;
        this.checkOutDate = checkOutDate;
        this.expirationDate = expirationDate;
        this.checkOutStatus = checkOutStatus;
    }

    public void setBook(Book book){
        this.book = book;
        if (!book.getCheckingOuts().contains(this)){
            book.getCheckingOuts().add(this);
        }
    }

    private void setCheckOutStatus(CheckOutStatus checkOutStatus){
        this.checkOutStatus = checkOutStatus;
    }

    public static CheckingOut createCheckingOut(Book book){
        final CheckingOut checkingOut = CheckingOut.builder()
                .checkOutDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusDays(RENT_DAY))
                .checkOutStatus(CheckOutStatus.NORMAL)
                .build();
        book.removeQuantity(BOOK_STOCK);
        checkingOut.setBook(book);
        return checkingOut;
    }

    public void setCheckOutInfo(CheckingOutInfo checkingOutInfo) {
        this.checkingOutInfo = checkingOutInfo;
    }

    public void bookReturn() {
        this.setCheckOutStatus(CheckOutStatus.RETURNED);
        this.getBook().addQuantity(BOOK_STOCK);
    }
}
