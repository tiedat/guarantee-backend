package guarantee.backend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "guarantee_place")
@Data
@Cacheable
public class GuaranteePlace {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence", value = "GUARANTEE_PLACE_SEQ")
    })
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "province")
    private String province;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
