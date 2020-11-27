package guarantee.backend.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "request_guarantee")
@Cacheable
@Data
public class RequestGuarantee {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence", value = "REQUEST_GUARAN_SEQ")
    })
    private Long id;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="address")
    private String address;

    @Column(name="province")
    private String province;

    @Column(name="district")
    private String district;

    @Column(name="ward")
    private String ward;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_imei")
    private String productImei;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
