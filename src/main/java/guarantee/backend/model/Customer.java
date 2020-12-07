package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private String phone;

    @NotEmpty
    private String province;

    @NotEmpty
    private String district;

    @NotEmpty
    private String ward;

    @NotEmpty
    private String address;

}
