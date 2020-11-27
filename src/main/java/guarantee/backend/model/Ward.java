package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "WARD")
@Data
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String prefix;

    @ManyToOne
    private District district;

    @ManyToOne
    private Province province;
}
