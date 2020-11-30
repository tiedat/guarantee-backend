package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PROVINCE")
@Data
@NamedQueries({
        @NamedQuery(name = "Province.showAreaByName",
                query = "SELECT p.area FROM Province p where p.name=:name")
})
public class Province implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String code;

    private String area;
}
