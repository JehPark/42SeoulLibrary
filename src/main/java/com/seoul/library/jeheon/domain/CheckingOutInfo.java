package com.seoul.library.jeheon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "checking_out_info")
@NoArgsConstructor
public class CheckingOutInfo extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "checking_out_info_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime checkOutDate;
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private CheckOutStatus checkOutStatus;

    @OneToMany(mappedBy = "checkingOutInfo", cascade = CascadeType.ALL)
    private List<CheckingOut> checkingOuts = new ArrayList<>();

    @Builder
    public CheckingOutInfo(LocalDateTime checkOutDate, LocalDateTime expirationDate, CheckOutStatus checkOutStatus) {
        this.checkOutDate = checkOutDate;
        this.expirationDate = expirationDate;
        this.checkOutStatus = checkOutStatus;
    }

    public void setUser(User newUser){
        if (!newUser.getCheckingOutInfos().contains(this)){
            newUser.saveCheckOutInfo(this);
        }
        this.user = newUser;
    }

    public void addCheckOut(CheckingOut checkingOut){
        this.checkingOuts.add(checkingOut);
        checkingOut.setCheckOutInfo(this);
    }

    public static CheckingOutInfo createCheckingOutInfo(List<CheckingOut> checkingOuts, User user){
        final CheckingOutInfo checkingOutInfo = CheckingOutInfo.builder()
                .checkOutDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusDays(14))
                .checkOutStatus(CheckOutStatus.NORMAL)
                .build();
        for (CheckingOut checkingOut : checkingOuts) {
            checkingOutInfo.addCheckOut(checkingOut);
        }
        checkingOutInfo.setUser(user);
        return checkingOutInfo;
    }
}
