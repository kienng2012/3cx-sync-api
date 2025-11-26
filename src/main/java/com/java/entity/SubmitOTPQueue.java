package com.java.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "SUBMIT_OTP_QUEUE")
public class SubmitOTPQueue {
    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long Id;

    @Column(name = "RECEIVE_ID", nullable = false)
    private Long receiveId;

    @Column(name = "SOURCE_ADDR")
    private String sourceAddr;

    @Column(name = "USER_ID")
    private String username;

    @Column(name = "DEST_ADDR")
    private String destAddr;
    @Column(name = "METHOD")
    private String method;

    @Column(name = "SEND_TIMESTAMP")
    private Timestamp sendTimestamp;


    @Column(name = "SHORT_MESSAGE")
    private String shortMessage;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "TYPE_SERVICE")
    private Integer typeService;

    @Column(name = "API_LOOKUP")
    private String apiLookup;
    @Column(name = "RESPONSE_TIME")
    private Timestamp responseTime;
    @Column(name = "STATUS")
    private Integer status;


}
