package guarantee.backend.service;

import guarantee.backend.DTO.RequestGuaranteeDTO;

public interface RequestGuaranteService {
    boolean saveRequestGuarante(RequestGuaranteeDTO requestGuaranteeDTO);
}
