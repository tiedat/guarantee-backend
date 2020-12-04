package guarantee.backend.service.Impl;

import guarantee.backend.model.Role;
import guarantee.backend.repositories.RoleRepository;
import guarantee.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("RoleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public List<String> getRoleUnderAdmin() {
        List<String> list = repository.getAllUnderAdmin();
        return list;
    }
}
