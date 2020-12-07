package guarantee.backend.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.model.Customer;
import guarantee.backend.model.Product;
import guarantee.backend.model.WarrantyCard;
import guarantee.backend.service.IWarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.TimeZone;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/warranty")
public class WarrantyController {
    @Autowired
    private IWarrantyService warrantyService;

    @GetMapping("/{serialNumber}")
    public ResponseEntity<WarrantyCard> findById(@PathVariable(value = "serialNumber") String serialNumber) {
        WarrantyCard warrantyCard;
        try {
            warrantyCard = warrantyService.findBySerialNumber(serialNumber);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = warrantyCard.getCustomer();
        if (Objects.nonNull(customer)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(warrantyCard, HttpStatus.ACCEPTED);
    }

    @PostMapping("/active")
    public ResponseEntity<?> activeWarranty(@RequestBody WarrantyActiveDTO warrantyActiveDTO) {

        StringBuilder msg = new StringBuilder();
        String serialNumber = warrantyActiveDTO.getSerialNumber();
        WarrantyCard warrantyCard;
        try {
            warrantyCard = warrantyService.findBySerialNumber(serialNumber);
        } catch (Exception ex) {
            msg.append("Mã serial không hợp lệ");
            return new ResponseEntity<>(msg.toString(), HttpStatus.NOT_FOUND);
        }
        Product product = warrantyCard.getProduct();
        Customer customer = warrantyCard.getCustomer();
        if (Objects.nonNull(customer)) {
            msg.append("Sản phẩm ").append(product.getName()).append(" đã được kích hoạt bảo hành bởi ").append(customer.getFullName());
            return new ResponseEntity<>(msg.toString(), HttpStatus.BAD_REQUEST);
        }
        try {
            warrantyService.active(warrantyActiveDTO);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            msg.append("Hệ thống lỗi !");
            return new ResponseEntity<>(msg.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
