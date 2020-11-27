package guarantee.backend.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Cacheable
@Data
public class Product {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(generator = "sequence")
    @GenericGenerator(name = "sequence", strategy = "sequence", parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence", value = "PRODUCT_SEQ")
    })
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "unit_weight")
    private String unitWeight;

    @Column(name = "description")
    private String description;

    @Column(name = "base_price")
    private String basePrice;

    @Column(name = "product_available")
    private Long productAvailable;

    @Column(name = "discount_available")
    private Long discountAvailable;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "product_imei")
    private String productImei;

    @Column(name = "status")
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
