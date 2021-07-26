package com.clairvoyant.controller;

import com.clairvoyant.entity.Employee;
import com.clairvoyant.service.EmployeeService;
import com.clairvoyant.util.Result;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

    private final com.clairvoyant.service.EmployeeService EmployeeService;

    public EmployeeController(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }

    @PostMapping("/save")//C
    public Mono<Result> saveEmployee(@RequestBody Employee employee) {
        return EmployeeService.createNewEmployee(employee);
    }

    @GetMapping("/get/{EmployeeId}")//R
    public Mono<Employee> getEmployee(@PathVariable("EmployeeId") Long employeeId) {
        return EmployeeService.getEmployeeByEmployeeId(employeeId);
    }
    @GetMapping("/health")//R
    public Mono<Result> getHealth() {
        return Mono.just(Result.SUCCESS);
    }

    @PutMapping("/updateEmployeeOrCreate")//U
    public Mono<Result> updateOrCreateEmployee(@RequestBody Employee Employee) {
        return EmployeeService.updateExistingOrCreateEmployee(Employee);
    }

    @DeleteMapping("/delete/{EmployeeId}")//D
    public Mono<Result> deleteEmployee(@PathVariable("EmployeeId") Long EmployeeId) {
        return EmployeeService.deleteEmployeeByEmployeeId(EmployeeId);
    }

    @PutMapping("/updateEmployee")
    public Mono<Result> updateEmployee(@RequestBody Employee Employee) {
        return EmployeeService.updateExistingEmployee(Employee);
    }

//    @GetMapping("/query/{EmployeeId}")
//    public Mono<Address> queryEmployeeAddress(@PathVariable("EmployeeId") String EmployeeId) {
//        return EmployeeService.queryAddressByEmployeeId(EmployeeId);
//    }

    @GetMapping("/allEmployeeList")
    public Flux<Employee> getAllEmployee() {
        return EmployeeService.getEmployeeList();
    }

}