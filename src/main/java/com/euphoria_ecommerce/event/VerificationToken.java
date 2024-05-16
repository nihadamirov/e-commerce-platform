//package com.euphoria_ecommerce.security;
//
//import com.euphoria_ecommerce.model.User;
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Calendar;
//import java.util.Date;
//@Data
//@Entity
//@NoArgsConstructor
//public class VerificationToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String token;
//    private Date exprationTime;
//    private static final  int EXPIRATION_TIME=15;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    public VerificationToken(String token, User user) {
//        this.token = token;
//        this.user = user;
//        this.exprationTime=this.getTokenExpirationTime();
//    }
//
//    public VerificationToken(String token) {
//        this.token = token;
//        this.exprationTime=this.getTokenExpirationTime();
//    }
//    public Date getTokenExpirationTime() {
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTimeInMillis(new Date().getTime());
//        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
//        return new Date(calendar.getTime().getTime());
//    }
//}
