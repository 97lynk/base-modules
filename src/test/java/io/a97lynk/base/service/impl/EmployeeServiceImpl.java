package io.a97lynk.base.service.impl;

import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.jpa.EmployeeEntity;
import io.a97lynk.base.jpa.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<EmployeeEntity, EmployeeDto, EmployeeRepository> {

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    public EmployeeDto toDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeEntity.getId());
        employeeDto.setName(employeeEntity.getName());
        return employeeDto;
    }

    @Override
    public EmployeeEntity toEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setName(employeeDto.getName());
        return employeeEntity;
    }


}
