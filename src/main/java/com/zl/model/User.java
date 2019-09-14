package com.zl.model;

import lombok.Data;
import lombok.ToString;

@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token ;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

    public User() {}

    public User(Integer id, String accountId, String name, String token, Long gmtCreate, Long gmtModified) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.token = token;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }
}
