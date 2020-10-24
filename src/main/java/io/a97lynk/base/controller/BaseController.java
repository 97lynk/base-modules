package io.a97lynk.base.controller;

import io.a97lynk.base.jpa.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseController<D extends BaseDto> {

    D create(D d);

    D searchById(Long id);

    List<D> searchAll();

    Page<D> searchAll(Pageable pageable);

    D update(Long id, D d);

    void delete(Long id);

}
