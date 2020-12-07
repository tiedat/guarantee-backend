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

    @Column(name="phone2")
    private String phone2;

    @Column(name="email")
    private String email;

    @Column(name="create_time")
    private String createTime;

    @Column(name="done_time")
    private String doneTime;

    @Column(name="serial")
    private String serial;

    @Column(name="description")
    private String description;

    @Column(name = "status")
    private String status = "active";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
