package guarantee.backend.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Cacheable
@Data
public class Category {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence", value = "CATEGORY_SEQ")
    })
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "active")
    private Long active;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
