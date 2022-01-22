package com.youthcon.tdd;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String phoneNumber;

    private Boolean sent;

    public Review() {
    }

    public Review(long id, String content, String phoneNumber) {
        this.id = id;
        this.content=content;
        this.phoneNumber=phoneNumber;
    }

    public Review(long id, String content, String phoneNumber, boolean sent) {
        this.id = id;
        this.content=content;
        this.phoneNumber=phoneNumber;
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Boolean getSent() {
        return sent;
    }
}
