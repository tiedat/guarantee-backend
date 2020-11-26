package guarantee.backend.service;

import guarantee.backend.DTO.PolicyDTO;
import guarantee.backend.model.Policy;
import org.springframework.stereotype.Service;

@Service
public interface IPolicyService {
    PolicyDTO getById(Long id);

    PolicyDTO convertToDTO(Policy policy);
}
