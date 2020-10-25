package io.a97lynk.base;

import io.a97lynk.base.exception.DataException;
import io.a97lynk.base.jpa.EmployeeDto;
import io.a97lynk.base.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BaseServiceTests {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @AfterEach
    public void clearData() {
        Set<Long> ids = employeeService.searchAll().stream().map(EmployeeDto::getId).collect(Collectors.toSet());
        ids.forEach(id -> employeeService.deleteById(id));
    }

    @Test
    public void insert() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Tuan Nguyen");
        EmployeeDto newEmployeeDto = employeeService.insert(employeeDto);

        Predicate<EmployeeDto> comparator = e -> e.getName().equals(newEmployeeDto.getName())
                && e.getId().equals(newEmployeeDto.getId());

        Assertions.assertTrue(employeeService.searchAll().stream().anyMatch(comparator), "insert employee fails");
    }

    @Test
    public void selectAll() {
        Assertions.assertTrue(employeeService.searchAll().isEmpty(), "selectAll fails");
    }

    @Test
    public void selectPage() {
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setName("Tuan Nguyen 1");
        employeeService.insert(employeeDto1);

        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setName("Tuan Nguyen 2");
        employeeService.insert(employeeDto2);

        Pageable pageable = PageRequest.of(0, 1);
        Page<EmployeeDto> dtoPage = employeeService.searchAll(pageable);

        Assertions.assertTrue(dtoPage.isFirst(), "Page isn't the first");
        Assertions.assertTrue(!dtoPage.isLast(), "Page is the last");
        Assertions.assertEquals(2, dtoPage.getTotalPages(), "Page size != 2");
        Assertions.assertEquals(2, dtoPage.getTotalElements(), "Total element != 2");
    }

    @Test
    public void searchById() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Tuan Nguyen");
        EmployeeDto newEmployeeDto = employeeService.insert(employeeDto);

        Assertions.assertNotNull(employeeService.searchById(newEmployeeDto.getId()), "searchById is fails");
    }

    @Test
    public void searchById_fails() {
        Assertions.assertThrows(DataException.class, () -> employeeService.searchById(100l));
    }

    @Test
    public void update() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName("Tuan Nguyen");

        EmployeeDto newEmployeeDto = employeeService.insert(employeeDto);
        newEmployeeDto.setName("Nguyen Tuan");

        EmployeeDto updateEmployeeDto = employeeService.updateById(newEmployeeDto.getId(), newEmployeeDto);

        Assertions.assertTrue(updateEmployeeDto.getName().equals(newEmployeeDto.getName()), "update is fails");
    }

}
