package guarantee.backend.service;

import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.model.WarrantyCard;
import org.springframework.stereotype.Service;


public interface IWarrantyService {
    WarrantyCard findBySerialNumber(String serialNumber);
    void active(WarrantyActiveDTO warrantyActiveDTO);
}
