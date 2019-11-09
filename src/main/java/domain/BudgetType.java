package domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)

public enum BudgetType {
    MONTHLY, QUARTERLY, YEARLY
}
