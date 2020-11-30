package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICT")
@Data
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String prefix;

    @ManyToOne
    private Province province;
}
