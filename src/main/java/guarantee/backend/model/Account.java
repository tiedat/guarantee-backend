package guarantee.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @ManyToOne
    private Role role;
}
