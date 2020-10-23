package ru.bitmaster.taxi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitmaster.taxi.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
