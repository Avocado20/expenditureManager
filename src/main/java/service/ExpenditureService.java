package service;

import domain.Expenditure;
import dto.ExpenditureDto;

import java.util.List;

public interface ExpenditureService {

    List<Expenditure> getForBudget(Long budgetId);
    List<Expenditure> getForCategory(Long categoryId);
    List<Expenditure> getLast(int howMany);
    List<Expenditure> getLast();
    Expenditure create(ExpenditureDto expenditureDto);
    Expenditure edit(ExpenditureDto expenditureDto);
    void delete(Long expenditureId);
}
