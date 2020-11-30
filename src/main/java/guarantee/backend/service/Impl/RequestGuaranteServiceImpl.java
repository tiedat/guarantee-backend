package guarantee.backend.service.Impl;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.model.RequestGuarantee;
import guarantee.backend.repositories.RequestGuaranteeRepository;
import guarantee.backend.service.RequestGuaranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestGuaranteServiceImpl implements RequestGuaranteService {

    @Autowired
    RequestGuaranteeRepository requestGuaranteeRepository;

    @Override
    public boolean saveRequestGuarante(RequestGuaranteeDTO requestGuaranteeDTO) {
        RequestGuarantee requestGuarantee = requestGuaranteeRepository.save(convertDtoToModel(requestGuaranteeDTO));
        return requestGuarantee != null ? true : false;
    }

    private RequestGuarantee convertDtoToModel(RequestGuaranteeDTO requestGuaranteeDTO) {
        RequestGuarantee requestGuarantee = new RequestGuarantee();
        requestGuarantee.setCustomerName(requestGuaranteeDTO.getCustomerName());
        requestGuarantee.setAddress(requestGuaranteeDTO.getAddress());
        requestGuarantee.setProvince(requestGuaranteeDTO.getProvince());
        requestGuarantee.setDistrict(requestGuaranteeDTO.getDistrict());
        requestGuarantee.setWard(requestGuaranteeDTO.getWard());
        requestGuarantee.setPhone(requestGuaranteeDTO.getPhone());
        requestGuarantee.setPhone2(requestGuaranteeDTO.getPhone2());
        requestGuarantee.setEmail(requestGuaranteeDTO.getEmail());
        requestGuarantee.setProduct(requestGuaranteeDTO.getProduct());
        requestGuarantee.setModelProduct(requestGuaranteeDTO.getModelProduct());
        requestGuarantee.setSerial(requestGuaranteeDTO.getSerial());
        requestGuarantee.setDescription(requestGuaranteeDTO.getDescription());
        return requestGuarantee;
    }
}
