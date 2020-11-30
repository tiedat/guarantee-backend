package guarantee.backend.DTO;

import lombok.Data;


@Data
public class RequestGuaranteeDTO {
    private String customerName;

    private String address;

    private String province;

    private String district;

    private String ward;

    private String phone;

    private String phone2;

    private String email;

    private String product;

    private String modelProduct;

    private String serial;

    private String description;
}
