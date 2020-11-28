package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TYPESTATION")
@Data
public class TypeStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
}
