package guarantee.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import guarantee.backend.DTO.WarrantyCardDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "WARRANTY")
@Data
@SqlResultSetMapping(
        name = "mappingWarrantyCardDTO",
        classes = {
                @ConstructorResult(
                        targetClass = WarrantyCardDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "serial_Number"),
                                @ColumnResult(name = "customer_Id", type = Long.class),
                                @ColumnResult(name = "product_code"),
                                @ColumnResult(name = "start_Time", type = LocalDate.class),
                                @ColumnResult(name = "end_Time", type = LocalDate.class),
                                @ColumnResult(name = "sold_Date", type = LocalDate.class),
                                @ColumnResult(name = "store_Addr"),
                                @ColumnResult(name = "store_Phone"),
                        }
                )
        }
)
public class WarrantyCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;

    @OneToOne
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "productCode", referencedColumnName = "productCode")
    private Product product;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate endTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private LocalDate soldDate;

    private String storeAddr;
    private String storePhone;

}
