package dto;

import domain.BudgetType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class BudgetDto {

    private Long budgetId;

    @NotNull
    private Long categoryId;

    @NotNull
    private LocalDate begin;

    @NotNull
    private BudgetType budgetType;

    @NotNull
    private Double size;
}
