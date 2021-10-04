package com.seoul.library.jeheon.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class CheckingOutInfo {
    @Id @GeneratedValue
    @Column(name = "checking_out_info_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "checkingOutInfo")
    private List<CheckingOut> checkingOuts = new ArrayList<>();
}
