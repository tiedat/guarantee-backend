package guarantee.backend.service;

import guarantee.backend.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoleService {
    List<String> getRoleUnderAdmin();
}
