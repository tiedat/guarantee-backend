package guarantee.backend.service;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.model.RequestGuarantee;

import java.util.List;

public interface RequestGuaranteService {
    boolean saveRequestGuarante(RequestGuaranteeDTO requestGuaranteeDTO);
    RequestGuaranteeDTO searchBySerial(String serial);
    List<RequestGuaranteeDTO> findAll();
}
