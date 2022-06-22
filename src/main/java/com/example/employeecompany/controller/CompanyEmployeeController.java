package com.example.employeecompany.controller;

import com.example.employeecompany.entity.Company;
import com.example.employeecompany.entity.Employee;
import com.example.employeecompany.repository.CompanyRepository;
import com.example.employeecompany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CompanyEmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/companies/{companyName}")
    public void createCompany(@PathVariable("companyName") String companyName) {

        Company company = new Company();
        company.setCompanyName(companyName);

        companyRepository.save(company);

    }

    @PostMapping("/employees/{employeeName}")
    public void createEmployee(@PathVariable("employeeName") String employeeName) {
        Employee employee = new Employee();
        employee.setName(employeeName);

        employeeRepository.save(employee);
    }

    // if unemploy - why do we need the fetch the company??
    @PutMapping("/employees/{employeeId}/companies/{companyId}")
    public void employToggle(@PathVariable("employeeId") Long employeeId,
                             @PathVariable("companyId") Long companyId) {

        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Company with id [%s] does not exist", companyId)
                        )
                );

        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Employee with id [%s] does not exist", employeeId)
                        )
                );

        employee.setCompany(
                employee.getCompany() == null ? company : null
        );

        employeeRepository.save(employee);
    }

    @GetMapping("/companies/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable("companyId") Long companyId) {

        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                String.format("Company with id [%s] does not exist", companyId)
                        )
                );

        return employeeRepository.findAllByCompany(company);
//         2 requests

//        return employeeRepository.findAllByCompany_Id(companyId);
//         1 request

    }

    @GetMapping("/employees") // ?unemployed=true
    public List<Employee> getEmployees(@RequestParam(name = "unemployed", required = false) boolean isUnemployed) {
        if (isUnemployed) {
            return employeeRepository.findAllByCompanyIsNull();
        } else {
            return employeeRepository.findAll();
        }
    }
}
