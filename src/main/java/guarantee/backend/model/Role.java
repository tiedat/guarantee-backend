package guarantee.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
@Data
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Role.getAllUnderAdmin"
                , query = "SELECT r.role from Role r where r.role not in ('ROLE_ADMIN')")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    public Role() {
    }
}
