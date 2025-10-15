package com.tracker.expense_tracker.Entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;

@Entity
@Table(name="expenses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank(message = "title should not be empty")
    @Size(min = 4,max = 30,message = "title should between 4 - 30 chars")
    @Column(name="title")
    private String title;

    @NotNull(message = "should not be zero")
    @Column(name="amount")
    private Double amount;

    @Column(name="date")
    @NotNull(message = "date is important field")
    @PastOrPresent(message = "date should not be future")
    private LocalDate date;

    @NotBlank(message = "Category should not be blank")
    @Column(name="category")
    private String category;

    @NotBlank(message = "user name  should not be empty")
    @Column(name="userName")
    private String userName;


    @Column(name="created_date")
    @CurrentTimestamp()
    private String createDate;



}
