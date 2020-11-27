package guarantee.backend.service.Impl;

import guarantee.backend.DTO.PolicyDTO;
import guarantee.backend.model.Policy;
import guarantee.backend.repositories.PolicyRepository;
import guarantee.backend.service.IPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("PolicyService")
public class PolicyServiceImpl implements IPolicyService {
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public PolicyDTO getById(Long id) {
        Policy policy = policyRepository.findById(id).get();
        PolicyDTO policyDTO = convertToDTO(policy);
        return policyDTO;
    }

    @Override
    public PolicyDTO convertToDTO(Policy policy) {
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setId(policy.getId());
        policyDTO.setContent(policy.getContent());
        policyDTO.setType(policy.getType());
        return policyDTO;
    }
}
