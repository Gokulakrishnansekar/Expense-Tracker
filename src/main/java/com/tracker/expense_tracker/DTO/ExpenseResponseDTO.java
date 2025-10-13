package com.tracker.expense_tracker.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseResponseDTO {

    private Long id;


    private String title;


    private Double amount;


    private LocalDate date;


    private String category;


    private String userName;




}
