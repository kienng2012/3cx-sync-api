package com.java.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    //    @Id
//    @Column(name = "ID", updatable = false, nullable = false, columnDefinition = "INT")
//    private Integer id;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "Sharekey")
    private String sharekey;


}
