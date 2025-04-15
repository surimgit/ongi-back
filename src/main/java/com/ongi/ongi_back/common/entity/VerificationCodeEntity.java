// package com.ongi.ongi_back.common.entity;

// import java.time.LocalDateTime;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity(name="verificationCodes")
// @Table(name="verification_codes")
// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// public class VerificationCodeEntity {

//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Integer sequence;
//     private String telNumber;
//     private String code;
//     private LocalDateTime expiryTime;
//     private LocalDateTime createdAt = LocalDateTime.now();
    
// }
