package com.clairvoyant.repository;

import com.clairvoyant.entity.Employee;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;

import java.util.concurrent.CompletableFuture;

import static software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo;

@Repository
public class EmployeeRepository {

    private final DynamoDbEnhancedAsyncClient enhancedAsyncClient;
    private final DynamoDbAsyncTable<Employee> EmployeeDynamoDbAsyncTable;

    public EmployeeRepository(DynamoDbEnhancedAsyncClient asyncClient) {
        this.enhancedAsyncClient = asyncClient;
        this.EmployeeDynamoDbAsyncTable = enhancedAsyncClient.table(Employee.class.getSimpleName(), TableSchema.fromBean(Employee.class));
    }

    //CREATE
    public CompletableFuture<Void> save(Employee Employee) {
        return EmployeeDynamoDbAsyncTable.putItem(Employee);
    }

    //READ
    public CompletableFuture<Employee> getEmployeeByID(Long EmployeeId) {
        return EmployeeDynamoDbAsyncTable.getItem(getKeyBuild(EmployeeId));
    }

    //UPDATE
    public CompletableFuture<Employee> updateEmployee(Employee Employee) {
        return EmployeeDynamoDbAsyncTable.updateItem(Employee);
    }

    //DELETE
    public CompletableFuture<Employee> deleteEmployeeById(Long EmployeeId) {
        return EmployeeDynamoDbAsyncTable.deleteItem(getKeyBuild(EmployeeId));
    }

    //READ_Employee_ADDRESS_ONLY
    public PagePublisher<Employee> getEmployeeAddress(String EmployeeId) {
        return EmployeeDynamoDbAsyncTable
                .query(r -> r.queryConditional(keyEqualTo(k -> k.partitionValue(EmployeeId)))
                        .addAttributeToProject("EmployeeAddress"));
    }

    //GET_ALL_ITEM
    public PagePublisher<Employee> getAllEmployee() {
        return EmployeeDynamoDbAsyncTable.scan();
    }

    private Key getKeyBuild(String EmployeeId) {
        return Key.builder().partitionValue(EmployeeId).build();
    }
    private Key getKeyBuild(Long EmployeeId) {
        return Key.builder().partitionValue(EmployeeId).build();
    }

}