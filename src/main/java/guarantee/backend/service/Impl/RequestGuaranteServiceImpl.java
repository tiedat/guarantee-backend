package guarantee.backend.service.Impl;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.model.RequestGuarantee;
import guarantee.backend.repositories.RequestGuaranteeRepository;
import guarantee.backend.service.RequestGuaranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class RequestGuaranteServiceImpl implements RequestGuaranteService {

    @Autowired
    RequestGuaranteeRepository requestGuaranteeRepository;

    @Override
    public boolean saveRequestGuarante(RequestGuaranteeDTO requestGuaranteeDTO) {
        RequestGuarantee requestGuarantee = requestGuaranteeRepository.save(convertDtoToModel(requestGuaranteeDTO));
        return requestGuarantee != null ? true : false;
    }

    @Override
    public RequestGuaranteeDTO searchBySerial(String serial) {
        RequestGuarantee requestGuarantee = requestGuaranteeRepository.findBySerial(serial);
        return this.convertModelToDTO(requestGuarantee);
    }

    @Override
    public List<RequestGuaranteeDTO> findAll() {
        Iterable<RequestGuarantee> iterator = requestGuaranteeRepository.findAll();
                List result = new ArrayList();
        if(null == iterator){
            return result;
        }
        for (RequestGuarantee requestGuarantee : iterator) {
            result.add(convertModelToDTO(requestGuarantee));
        }
        return result;
    }

    @Override
    public boolean rejectBySerial(String serial) {
        RequestGuarantee requestGuarantee = requestGuaranteeRepository.findBySerial(serial);
        requestGuarantee.setStatus("reject");
        RequestGuarantee result = requestGuaranteeRepository.save(requestGuarantee);
        if (null == result) {
            return false;
        }
        return true;
    }

    @Override
    public boolean acceptBySerial(String serial) {
        RequestGuarantee requestGuarantee = requestGuaranteeRepository.findBySerial(serial);
        requestGuarantee.setStatus("accept");
        RequestGuarantee result = requestGuaranteeRepository.save(requestGuarantee);
        if (null == result) {
            return false;
        }
        return true;
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
        requestGuarantee.setStatus(requestGuaranteeDTO.getStatus());
        return requestGuarantee;
    }

    private RequestGuaranteeDTO convertModelToDTO(RequestGuarantee model) {
        RequestGuaranteeDTO requestGuarantee = new RequestGuaranteeDTO();
        requestGuarantee.setCustomerName(model.getCustomerName());
        requestGuarantee.setAddress(model.getAddress());
        requestGuarantee.setProvince(model.getProvince());
        requestGuarantee.setDistrict(model.getDistrict());
        requestGuarantee.setWard(model.getWard());
        requestGuarantee.setPhone(model.getPhone());
        requestGuarantee.setPhone2(model.getPhone2());
        requestGuarantee.setEmail(model.getEmail());
        requestGuarantee.setProduct(model.getProduct());
        requestGuarantee.setModelProduct(model.getModelProduct());
        requestGuarantee.setSerial(model.getSerial());
        requestGuarantee.setDescription(model.getDescription());
        requestGuarantee.setStatus(model.getStatus());
        return requestGuarantee;
    }
}
