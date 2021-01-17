package guarantee.backend.service;

import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.DTO.WarrantyCardDTO;
import guarantee.backend.model.WarrantyCard;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IWarrantyService {
    WarrantyCard findBySerialNumber(String serialNumber);
    List<WarrantyCard> findByStatus(int status);
    void active(WarrantyActiveDTO warrantyActiveDTO);
    void uploadDataWarranty(MultipartFile file) throws Throwable;
    List<WarrantyCardDTO> getAllWarrantyCardDTO();
}
