package guarantee.backend.repositories;

import guarantee.backend.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<String> getAllUnderAdmin();
}
