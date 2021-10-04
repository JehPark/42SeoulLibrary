package com.seoul.library.jeheon.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private long id;

    private String name;
    private String intraId;

    @OneToMany(mappedBy = "user")
    private List<CheckingOutInfo> checkingOutInfos = new ArrayList<>();
}
