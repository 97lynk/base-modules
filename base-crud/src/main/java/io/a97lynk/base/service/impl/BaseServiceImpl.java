package io.a97lynk.base.service.impl;

import io.a97lynk.base.exception.DataException;
import io.a97lynk.base.jpa.BaseDto;
import io.a97lynk.base.jpa.BaseEntity;
import io.a97lynk.base.jpa.BaseRepository;
import io.a97lynk.base.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<E extends BaseEntity, D extends BaseDto, R extends BaseRepository<E>> implements BaseService<E, D> {

    private R repository;

    protected BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    public R getRepository() {
        return repository;
    }

    @Override
    public Page<D> searchAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDto);
    }

    @Override
    public List<D> searchAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public D searchById(Number id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new DataException("Not found"));
    }

    @Override
    public void validateToInsert(D d) throws RuntimeException {

    }

    @Override
    public D insert(D d) {
        validateToInsert(d);
        E e = toEntity(d);
        e = repository.save(e);
        return toDto(e);
    }

    @Override
    public void validateToUpdate(D d) throws RuntimeException {
        if (d.getId() == null || !repository.existsById(d.getId()))
            throw new DataException(d.getClass().getSimpleName() + " not found");
    }

    @Override
    public D updateById(Number id, D d) {
        d.setId(id);
        validateToUpdate(d);
        E e = toEntity(d);
        e = repository.save(e);
        return toDto(e);
    }

    @Override
    public void validateToDelete(Number id) throws RuntimeException {
        if (id == null || !repository.existsById(id))
            throw new DataException(" not found");
    }

    @Override
    public void deleteById(Number id) {
        validateToDelete(id);
        repository.deleteById(id);
    }
}
