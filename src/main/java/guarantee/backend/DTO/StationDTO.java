package guarantee.backend.DTO;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

@Data
public class StationDTO {

    private Long id;

    private String object;
    private String name;
    private String phone;
    private String password;
    private String province;
    private String email;
    private String district;
    private String ward;
    private String address;
    private String legal;
    private String represent;
    //CMTND/CCCD
    private String serial;
    private String taxcode;
    private String license;
    private String bank;
    private String banknumber;
    private String beneficiary;
    private String status;
    private String area;
}
