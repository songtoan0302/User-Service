package org.ptit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "age")
    private int age;
    @NonNull
    @Column(name = "address")
    private String address;
}
