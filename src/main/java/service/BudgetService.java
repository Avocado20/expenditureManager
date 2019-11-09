package service;

import domain.Budget;
import domain.BudgetType;
import dto.BudgetDto;

import java.time.LocalDate;
import java.util.List;

public interface BudgetService  {

    Budget create(BudgetDto budgetDto);
    List<Budget> getByType(LocalDate currentDate, BudgetType type);
    List<Budget> getByCategoryAndType(Long categoryId, BudgetType type);
    Budget edit(BudgetDto budgetDto);
    Budget findById(Long budgetId);
    void deleteByKey(Long budgetId);

}
