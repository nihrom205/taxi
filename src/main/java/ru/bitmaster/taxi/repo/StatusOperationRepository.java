package ru.bitmaster.taxi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitmaster.taxi.model.StatusOperation;

@Repository
public interface StatusOperationRepository extends JpaRepository<StatusOperation, Long> {
    StatusOperation findByName(String status);
}
