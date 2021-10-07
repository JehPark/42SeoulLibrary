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
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String intraId;

    @OneToMany(mappedBy = "user")
    private List<CheckingOutInfo> checkingOutInfos = new ArrayList<>();

    @Builder
    public User(String name, String intraId) {
        this.name = name;
        this.intraId = intraId;
    }

    public void saveCheckOutInfo(CheckingOutInfo checkingOutInfo){
        if (checkingOutInfo.getUser() != this){
            checkingOutInfos.add(checkingOutInfo);
        }
        checkingOutInfo.setUser(this);
    }

    public void update(String name, String intraId) {
        this.name = name;
        this.intraId = intraId;
    }
}
