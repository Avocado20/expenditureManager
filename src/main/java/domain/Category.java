package domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import config.TableNameContants;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Data
@Accessors(chain=true)
public class Category {

    @Id
    @Column(name = TableNameContants.CATEGORY_ID)
    private Long id;

    private String name;

    @OneToMany(mappedBy=TableNameContants.CATEGORY, cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Budget> budgets;

}
