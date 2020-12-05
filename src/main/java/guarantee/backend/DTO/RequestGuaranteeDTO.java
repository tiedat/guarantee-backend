package guarantee.backend.DTO;

import guarantee.backend.model.Product;
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

    private String createTime;

    private String doneTime;

    private String serial;

    private String description;

    private String status = "active";

    private Product product;

    private String id;
}
