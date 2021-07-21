package com.clairvoyant.service;

import com.clairvoyant.entity.Employee;
import com.clairvoyant.repository.EmployeeRepository;
import com.clairvoyant.util.Result;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.concurrent.CompletableFuture;

import static com.clairvoyant.util.Result.SUCCESS;
import static com.clairvoyant.util.Result.FAIL;

@Service
public class EmployeeService {

    private final com.clairvoyant.repository.EmployeeRepository EmployeeRepository;

    public EmployeeService(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    public Mono<Result> createNewEmployee(Employee Employee) {
        CompletableFuture<Result> result = EmployeeRepository.save(Employee)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL);
        return Mono.fromFuture(result);

    }

    public Mono<Employee> getEmployeeByEmployeeId(Long EmployeeId) {
        CompletableFuture<Employee> Employee = EmployeeRepository.getEmployeeByID(EmployeeId)
                .whenComplete((cus, ex) -> {
                    if (null == cus) {
                        throw new IllegalArgumentException("Invalid EmployeeId");
                    }
                })
                .exceptionally(ex -> new Employee());
        return Mono.fromFuture(Employee);
    }



    public Mono<Result> updateExistingEmployee(Employee employee) {
        CompletableFuture<Result> result = EmployeeRepository.getEmployeeByID(employee.getDsId())
                .thenApply(retrievedEmployee -> {
                    if (null == retrievedEmployee) {
                        throw new IllegalArgumentException("Invalid EmployeeID");
                    }
                    return retrievedEmployee;
                }).thenCompose(__ -> EmployeeRepository.updateEmployee(employee))
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL);

        return Mono.fromFuture(result);
    }

    public Mono<Result> updateExistingOrCreateEmployee(Employee Employee) {
        CompletableFuture<Result> result = EmployeeRepository.updateEmployee(Employee)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL);

        return Mono.fromFuture(result);
    }

    public Mono<Result> deleteEmployeeByEmployeeId(String EmployeeId) {
        CompletableFuture<Result> result = EmployeeRepository.deleteEmployeeById(EmployeeId)
                .handle((__, ex) -> ex == null ? SUCCESS : FAIL);
        return Mono.fromFuture(result);
    }

    public Flux<Employee> getEmployeeList() {
        return Flux.from(EmployeeRepository.getAllEmployee().items())
                .onErrorReturn(new Employee());
    }

}