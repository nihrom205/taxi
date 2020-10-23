package ru.bitmaster.taxi.service;

import ru.bitmaster.taxi.model.Driver;
import java.util.Optional;

public interface DriverService {
    Optional<Driver> findById(Long id);
    void create(Driver driver);
    void deleteById(Long id);
    void delete(Driver driver);
    void update(Driver driver);

}
