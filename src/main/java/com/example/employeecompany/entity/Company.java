package com.example.employeecompany.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String companyName;

//    @Column(name = "passport_id")
//    private String passportId;

//    @OneToMany(mappedBy = "company")
//    private List<Employee> employees;

    // select * from employees where company_id = 1
    // ORM (JPA -> Hibernate)
    //  Object
    //  Relation
    //  Mapping

    // company      employee
    //   1              1  -> 3, 4
    //   1              2

    //  3               1
    //  4               1
}
