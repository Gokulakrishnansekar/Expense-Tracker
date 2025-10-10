package com.tracker.expense_tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private Long id;
    private String title;
    private double amount;
    private LocalDate date;
    private String category;
    private String user;
}
