package com.github.commitscrawler.domain.entity;

import com.github.commitscrawler.domain.enums.UserRole;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String name;
    private String password;

    private boolean isManaged; // 내 상태를 노출 할 것인지 여부

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
