package com.k21d.spring.springbootbeanvalidation.domain;

import com.k21d.spring.springbootbeanvalidation.validation.constraints.ValidCardNumber;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class User {
    @Max(value = 10000)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @ValidCardNumber(message = "卡号必须以kk开头，以数字结尾")
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
