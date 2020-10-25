package io.a97lynk.base.jpa;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class EmployeeEntity extends BaseEntity<Long> {

    private String name;

}
