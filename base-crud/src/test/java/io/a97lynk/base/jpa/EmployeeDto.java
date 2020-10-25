package io.a97lynk.base.jpa;

import lombok.Data;

@Data
public class EmployeeDto extends BaseDto<Long> {

    private String name;

}
