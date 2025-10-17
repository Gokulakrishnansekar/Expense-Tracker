package com.tracker.expense_tracker.repository;

import com.tracker.expense_tracker.Entity.Expense;
import com.tracker.expense_tracker.model.AmountByCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense,Long> , JpaSpecificationExecutor<Expense> {


////    @Query("select e from Expense e order by e.id")
//    @Override
//    public Page<Expense> findAll(Pageable pageable);

    @Query("select sum(e.amount) as totalAmount,e.category as category from Expense" +
            " e where (:userName is null or e.userName like (:userName)) " +
            "and (:startDate is null or e.date>= :startDate) " +
            "and (:endDate is null or e.date<= :endDate) " +
            "group by e.category")
    public List<AmountByCategory> getAmountByCategory(@Param("userName") String username,
                                                      @Param("startDate")  LocalDate startDate,
                                                      @Param("endDate")  LocalDate endDate);
}

