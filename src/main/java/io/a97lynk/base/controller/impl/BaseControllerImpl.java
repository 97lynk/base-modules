package io.a97lynk.base.controller.impl;

import io.a97lynk.base.controller.BaseController;
import io.a97lynk.base.jpa.BaseDto;
import io.a97lynk.base.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseControllerImpl<D extends BaseDto, S extends BaseService> implements BaseController<D> {

    private S service;

    public BaseControllerImpl(S service) {
        this.service = service;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public D create(@RequestBody D d) {
        return (D) service.insert(d);
    }

    @Override
    @GetMapping("/{id}")
    public D searchById(@PathVariable("id") Long id) throws RuntimeException {
        return (D) service.searchById(id);
    }

    @Override
    @GetMapping
    public List<D> searchAll() {
        return service.searchAll();
    }

    @Override
    @GetMapping("/page")
    public Page<D> searchAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return service.searchAll(pageable);
    }

    @Override
    @PutMapping("/{id}")
    public D update(@PathVariable("id") Long id, @RequestBody D d) {
        return (D) service.updateById(id, d);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteById(id);
    }
}
