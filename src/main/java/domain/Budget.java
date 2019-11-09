package domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import config.TableNameContants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
public class Budget {

    @Id
    @Column(name = TableNameContants.BUDGET_ID)
    private Long id;

    @ManyToOne
    @JoinColumn(name = TableNameContants.CATEGORY_ID)
    @JsonBackReference
    private Category category;

    private LocalDate begin;
    private BudgetType type;


}
