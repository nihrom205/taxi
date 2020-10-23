package ru.bitmaster.taxi.service;

import ru.bitmaster.taxi.model.StatusOperation;

import java.util.Optional;

public interface StatusOperationService {
    void create(StatusOperation statusOperation);
    Optional<StatusOperation> findById(Long id);
    void deleteById(Long id);
    void delete(StatusOperation statusOperation);
    void update(StatusOperation statusOperation);
    StatusOperation findByName(String name);
}
