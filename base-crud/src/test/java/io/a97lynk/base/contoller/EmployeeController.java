package io.a97lynk.base.contoller;

import io.a97lynk.base.controller.impl.BaseControllerImpl;
import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.service.impl.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseControllerImpl<EmployeeDto, EmployeeServiceImpl> {

    public EmployeeController(EmployeeServiceImpl service) {
        super(service);
    }
}
