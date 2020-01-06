package domain;

import config.TableNameContants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@Accessors(chain =true)
@Entity
public class Expenditure {

    @Id
    @Column(name = TableNameContants.EXPENDITURE_ID)
    private Long id;

    private Category category;
    private Double expenditure;

    private LocalDateTime time;


}
