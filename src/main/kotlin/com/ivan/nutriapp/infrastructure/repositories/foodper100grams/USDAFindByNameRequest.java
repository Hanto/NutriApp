package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class USDAFindByNameRequest {

    private String query;
    private List<String>dataType;
    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private String sortOrder;
    private String brandOwner;
    private List<String> tradeChannel;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
