package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "STATION")
@Data
@NamedQueries({
        @NamedQuery(name = "Station.accept",
                query = "UPDATE Station s SET s.status='ACCEPTED' where s.id=:id"
        ),
        @NamedQuery(name = "Station.remove",
                query = "delete from Station  s where  s.id=:id")
})
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
