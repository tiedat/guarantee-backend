package guarantee.backend.service;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.model.RequestGuarantee;

import java.util.List;

public interface RequestGuaranteService {
    boolean saveRequestGuarantee(RequestGuaranteeDTO requestGuaranteeDTO);
    List<RequestGuaranteeDTO> searchBySerial(String serial);
    List<RequestGuaranteeDTO> findAll();
    boolean rejectBySerial(String serial);
    boolean acceptBySerial(String serial);
}
