package com.example.employeecompany.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    // FK -> Join Column
    @JoinColumn(name = "company_id")
    @ManyToOne
    private Company company; // company-id = 1


}
