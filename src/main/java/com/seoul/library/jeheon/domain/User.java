package com.seoul.library.jeheon.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String nickName;
    private String intraId;

    @OneToMany(mappedBy = "user")
    private List<CheckingOutInfo> checkingOutInfos = new ArrayList<>();

    @Builder
    public User(String nickName, String intraId) {
        this.nickName = nickName;
        this.intraId = intraId;
    }

    public void saveCheckOutInfo(CheckingOutInfo checkingOutInfo){
        this.checkingOutInfos.add(checkingOutInfo);
        if (checkingOutInfo.getUser() != this){
            checkingOutInfo.setUser(this);
        }
    }

    public void update(String name) {
        this.nickName = name;
    }
}
