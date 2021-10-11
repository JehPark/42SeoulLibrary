package com.seoul.library.jeheon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "checking_out_info")
@NoArgsConstructor
@ToString
public class CheckingOutInfo extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "checking_out_info_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "checkingOutInfo", cascade = CascadeType.ALL)
    private List<CheckingOut> checkingOuts = new ArrayList<>();

    public void setUser(User newUser){
        this.user = newUser;
        if (!newUser.getCheckingOutInfos().contains(this)) {
            newUser.saveCheckOutInfo(this);
        }
    }

    public void addCheckOut(CheckingOut checkingOut){
        this.checkingOuts.add(checkingOut);
        checkingOut.setCheckOutInfo(this);
    }

    public static CheckingOutInfo createCheckingOutInfo(User user){
        final CheckingOutInfo checkingOutInfo = new CheckingOutInfo();
        checkingOutInfo.setUser(user);
        return checkingOutInfo;
    }
}
