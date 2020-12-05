package guarantee.backend.service.Impl;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.model.RequestGuarantee;
import guarantee.backend.model.WarrantyCard;
import guarantee.backend.repositories.RequestGuaranteeRepository;
import guarantee.backend.service.IWarrantyService;
import guarantee.backend.service.RequestGuaranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RequestGuaranteServiceImpl implements RequestGuaranteService {

    @Autowired
    RequestGuaranteeRepository requestGuaranteeRepository;
    @Autowired
    IWarrantyService warrantyService;

    @Override
    public boolean saveRequestGuarante(RequestGuaranteeDTO requestGuaranteeDTO) {
        requestGuaranteeDTO.setCreateTime((new Date()).toString());
        RequestGuarantee requestGuarantee = requestGuaranteeRepository.save(convertDtoToModel(requestGuaranteeDTO));
        return requestGuarantee != null ? true : false;
    }

    @Override
    public List<RequestGuaranteeDTO> searchBySerial(String serial) {
        List<RequestGuarantee> requestGuarantee = requestGuaranteeRepository.findBySerial(serial);
        List<RequestGuaranteeDTO> results = new ArrayList<>();
        int size = requestGuarantee.size();
        if (null == requestGuarantee) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            results.add(this.convertModelToDTO(requestGuarantee.get(i)));
        }
        return results;
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
    public boolean rejectBySerial(String id) {
        Optional<RequestGuarantee> optional =  requestGuaranteeRepository.findById(Long.valueOf(id));
        RequestGuarantee requestGuarantee = optional.get();
        if (optional == null || requestGuarantee == null) {
            return false;
        }
        requestGuarantee.setStatus("reject");
        requestGuarantee.setDoneTime((new Date()).toString());
        RequestGuarantee result = requestGuaranteeRepository.save(requestGuarantee);
        if (null == result) {
            return false;
        }
        return true;
    }

    @Override
    public boolean acceptBySerial(String id) {
        Optional<RequestGuarantee> optional =  requestGuaranteeRepository.findById(Long.valueOf(id));
        RequestGuarantee requestGuarantee = optional.get();
        if (optional == null || requestGuarantee == null) {
            return false;
        }
        requestGuarantee.setStatus("accept");
        requestGuarantee.setDoneTime((new Date()).toString());
        RequestGuarantee result = requestGuaranteeRepository.save(requestGuarantee);
        if (null == result) {
            return false;
        }
        return true;
    }

    private RequestGuarantee convertDtoToModel(RequestGuaranteeDTO requestGuaranteeDTO) {
        if(null == requestGuaranteeDTO){
            return null;
        }
        RequestGuarantee requestGuarantee = new RequestGuarantee();
        requestGuarantee.setCustomerName(requestGuaranteeDTO.getCustomerName());
        requestGuarantee.setAddress(requestGuaranteeDTO.getAddress());
        requestGuarantee.setProvince(requestGuaranteeDTO.getProvince());
        requestGuarantee.setDistrict(requestGuaranteeDTO.getDistrict());
        requestGuarantee.setWard(requestGuaranteeDTO.getWard());
        requestGuarantee.setPhone(requestGuaranteeDTO.getPhone());
        requestGuarantee.setPhone2(requestGuaranteeDTO.getPhone2());
        requestGuarantee.setEmail(requestGuaranteeDTO.getEmail());
        requestGuarantee.setCreateTime(requestGuaranteeDTO.getCreateTime());
        requestGuarantee.setDoneTime(requestGuaranteeDTO.getDoneTime());
        requestGuarantee.setSerial(requestGuaranteeDTO.getSerial());
        requestGuarantee.setDescription(requestGuaranteeDTO.getDescription());
        requestGuarantee.setStatus(requestGuaranteeDTO.getStatus());
        return requestGuarantee;
    }

    private RequestGuaranteeDTO convertModelToDTO(RequestGuarantee model) {
        if(null == model){
            return null;
        }
        RequestGuaranteeDTO requestGuarantee = new RequestGuaranteeDTO();
        WarrantyCard warrantyCard = this.warrantyService.findBySerialNumber(model.getSerial());
        requestGuarantee.setProduct(warrantyCard.getProduct());
        requestGuarantee.setCustomerName(model.getCustomerName());
        requestGuarantee.setAddress(model.getAddress());
        requestGuarantee.setProvince(model.getProvince());
        requestGuarantee.setDistrict(model.getDistrict());
        requestGuarantee.setWard(model.getWard());
        requestGuarantee.setPhone(model.getPhone());
        requestGuarantee.setPhone2(model.getPhone2());
        requestGuarantee.setEmail(model.getEmail());
        requestGuarantee.setCreateTime(model.getCreateTime());
        requestGuarantee.setDoneTime(model.getDoneTime());
        requestGuarantee.setSerial(model.getSerial());
        requestGuarantee.setDescription(model.getDescription());
        requestGuarantee.setStatus(model.getStatus());
        return requestGuarantee;
    }
}
