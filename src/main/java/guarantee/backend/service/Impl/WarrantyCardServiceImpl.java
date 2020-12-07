package guarantee.backend.service.Impl;

import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.model.Customer;
import guarantee.backend.model.Product;
import guarantee.backend.model.WarrantyCard;
import guarantee.backend.repositories.CustomerRepository;
import guarantee.backend.repositories.WarrantyCardRepository;
import guarantee.backend.service.IWarrantyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

@Service
public class WarrantyCardServiceImpl implements IWarrantyService {

    @Autowired
    private WarrantyCardRepository warrantyCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public WarrantyCard findBySerialNumber(String serialNumber) {
        WarrantyCard warrantyCard = warrantyCardRepository.findBySerialNumber(serialNumber).orElseThrow(() ->
                new IllegalArgumentException("Not found warranty card " + serialNumber));
        return warrantyCard;
    }

    @Override
    public void active(WarrantyActiveDTO warrantyActiveDTO) {
        Customer customer = modelMapper.map(warrantyActiveDTO, Customer.class);
        customerRepository.saveAndFlush(customer);
        WarrantyCard warrantyCard = warrantyCardRepository.findBySerialNumber(warrantyActiveDTO.getSerialNumber()).orElseThrow(() ->
                new IllegalArgumentException("Not found warranty card "));

        Product product = warrantyCard.getProduct();

        warrantyCard.setCustomer(customer);

        LocalDate startTime = LocalDate.now();
        warrantyCard.setStartTime(startTime);
        warrantyCard.setEndTime(startTime.plusMonths(Long.valueOf(product.getPeriodMonthWarranty())));

        warrantyCard.setStoreAddr(warrantyActiveDTO.getStoreAddr());
        warrantyCard.setStorePhone(warrantyActiveDTO.getStorePhone());
        warrantyCard.setSoldDate(warrantyActiveDTO.getSoldDate());

        warrantyCardRepository.saveAndFlush(warrantyCard);

    }
}
