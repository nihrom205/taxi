package ru.bitmaster.taxi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bitmaster.taxi.model.Operation;
import ru.bitmaster.taxi.service.OperationService;
import java.time.LocalDate;
import java.util.List;

@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/{account}/report/{startDate}/{endDate}")
    public List<Operation> reportOfDates(@PathVariable("startDate") String startDateString,
                                         @PathVariable("endDate") String endDateString,
                                         @PathVariable("account") Long numberAccount) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        return operationService.reportToDates(numberAccount, startDate, endDate);
    }
}
