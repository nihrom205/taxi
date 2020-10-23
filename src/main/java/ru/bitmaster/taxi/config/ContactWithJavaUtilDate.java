package ru.bitmaster.taxi.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class ContactWithJavaUtilDate {
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate localDate;
}
