package ru.bitmaster.taxi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bitmaster.taxi.model.StatusOperation;
import ru.bitmaster.taxi.repo.StatusOperationRepository;
import ru.bitmaster.taxi.service.StatusOperationService;
import java.util.Optional;

@Service
public class StatusOperationServiceImpl implements StatusOperationService {

    @Autowired
    private StatusOperationRepository statusOperationRepository;

    @Override
    public void create(StatusOperation statusOperation) {
        statusOperationRepository.save(statusOperation);
    }

    @Override
    public Optional<StatusOperation> findById(Long id) {
        return statusOperationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        statusOperationRepository.deleteById(id);
    }

    @Override
    public void delete(StatusOperation statusOperation) {
        statusOperationRepository.delete(statusOperation);
    }

    @Override
    public void update(StatusOperation statusOperation) {
        statusOperationRepository.save(statusOperation);
    }

    @Override
    public StatusOperation findByName(String name) {
        return statusOperationRepository.findByName(name);
    }
}
