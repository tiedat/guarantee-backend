package guarantee.backend.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class WarrantyActiveDTO {

    private String fullName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String address;

    private String serialNumber;
    private LocalDate soldDate;
    private String storeAddr;
    private String storePhone;
}
