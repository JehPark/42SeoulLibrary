package com.seoul.library.jeheon.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "checking_out_info")
public class CheckingOutInfo extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "checking_out_info_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "checkingOutInfo")
    private List<CheckingOut> checkingOuts = new ArrayList<>();
}
