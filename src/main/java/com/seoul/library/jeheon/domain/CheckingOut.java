package com.seoul.library.jeheon.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Table(name = "checking_out")
public class CheckingOut extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "checkout_id")
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime checkOutDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checking_out_info_id")
    private CheckingOutInfo checkingOutInfo;


    @Enumerated(EnumType.STRING)
    private CheckOutStatus checkOutStatus;
}
