package dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ExpenditureDto {

    Long id;

    @NotNull
    Double expenditure;

    @NotNull
    LocalDateTime time;

    @NotNull
    Long categoryId;
}
