package com.example.tiki_java.entity;

import com.example.tiki_java.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Column(unique = true)
    String email;
    String password;
    String firstName;
    String lastName;
    Date emailVerifiedAt;
    String provider;
    String providerId;
}
