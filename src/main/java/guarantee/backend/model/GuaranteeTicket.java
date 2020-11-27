package guarantee.backend.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "guarantee_ticket")
@Cacheable
@Data
public class GuaranteeTicket {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence", value = "GUARANTEE_TICKET_SEQ")
    })
    private Long id;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="expire_date")
    private Date expireDate;

    @Column(name="product_imei")
    private String product_imei;

    @Column(name="description")
    private String description;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
