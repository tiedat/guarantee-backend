package guarantee.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WarrantyCardDTO {
    private Long id;
    private String serialNumber;
    private Long customerId;
    private String productCode;
    private LocalDate startTime;
    private LocalDate endTime;
    private LocalDate soldDate;
    private String storeAddr;
    private String storePhone;

    public WarrantyCardDTO(Long id, String serialNumber, Long customerId, String productCode, LocalDate startTime, LocalDate endTime, LocalDate soldDate, String storeAddr, String storePhone) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.customerId = customerId;
        this.productCode = productCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.soldDate = soldDate;
        this.storeAddr = storeAddr;
        this.storePhone = storePhone;
    }
}
