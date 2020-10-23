package ru.bitmaster.taxi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bitmaster.taxi.model.Driver;
import ru.bitmaster.taxi.repo.DriverRepository;
import ru.bitmaster.taxi.service.DriverService;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public void create(Driver driver) {
        driverRepository.save(driver);
    }

    @Override
    public void deleteById(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public void delete(Driver driver) {
        driverRepository.delete(driver);
    }

    @Override
    public void update(Driver driver) {
        driverRepository.save(driver);
    }
}
