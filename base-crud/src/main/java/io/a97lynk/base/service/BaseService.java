package io.a97lynk.base.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BaseService<E, D> {

    D toDto(E e);

    E toEntity(D d);

    Page<D> searchAll(Pageable pageable);

    List<D> searchAll();

    D searchById(Number id);

    void validateToInsert(D d) throws RuntimeException;

    D insert(D d);

    void validateToUpdate(D d) throws RuntimeException;

    D updateById(Number id, D d);

    void validateToDelete(Number id) throws RuntimeException;

    void deleteById(Number id);

}
