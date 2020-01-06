package domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum BudgetType {
    MONTHLY("1"), QUARTERLY("3"), YEARLY("12");

    private BudgetType(String value)
    {
        this.label = value;
    }
    public final String label;

}
