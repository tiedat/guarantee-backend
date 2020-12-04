package guarantee.backend.service;

import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.model.WarrantyCard;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface IWarrantyService {
    WarrantyCard findBySerialNumber(String serialNumber);
    void active(WarrantyActiveDTO warrantyActiveDTO);
    void uploadDataWarranty(MultipartFile file) throws Throwable;
}
