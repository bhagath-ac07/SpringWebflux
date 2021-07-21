package com.clairvoyant.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;


@NoArgsConstructor
@DynamoDbBean
public class Employee {
	private Long dsId;

	private String name;

	private Integer age;

	private String place;

	private String dateOfBirth;

	private String dateOfJoining;

	private String manager;

	private String maritalStatus;

	private Integer reviewCycle;

	private String hrManager;

	@DynamoDbPartitionKey
	public Long getDsId() {
		return dsId;
	}

	public void setDsId(Long dsId) {
		this.dsId = dsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getReviewCycle() {
		return reviewCycle;
	}

	public void setReviewCycle(Integer reviewCycle) {
		this.reviewCycle = reviewCycle;
	}

	public String getHrManager() {
		return hrManager;
	}

	public void setHrManager(String hrManager) {
		this.hrManager = hrManager;
	}
}


