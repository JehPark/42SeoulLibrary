package com.seoul.library.jeheon.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class CheckingOut {
    @Id @GeneratedValue
    @Column(name = "checkout_id")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "checking_out_info_id")
    private CheckingOutInfo checkingOutInfo;


    @Enumerated(EnumType.STRING)
    private CheckOutStatus checkOutStatus;
}
