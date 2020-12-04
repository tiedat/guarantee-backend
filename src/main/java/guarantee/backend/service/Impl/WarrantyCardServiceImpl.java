package guarantee.backend.service.Impl;

import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.model.Customer;
import guarantee.backend.model.Product;
import guarantee.backend.model.WarrantyCard;
import guarantee.backend.repositories.CustomerRepository;
import guarantee.backend.repositories.WarrantyCardRepository;
import guarantee.backend.service.IWarrantyService;
import org.apache.poi.ss.usermodel.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class WarrantyCardServiceImpl implements IWarrantyService {

    @Autowired
    private WarrantyCardRepository warrantyCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;

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

    @Override
    @Transactional
    public void uploadDataWarranty(MultipartFile file) throws Throwable {

        StringBuilder queryBuilder = new StringBuilder();
        String catabase = System.getProperty( "catalina.base");
        File fileTmp = new File(catabase, "uploadFile.xlsx");
        queryBuilder.append("INSERT INTO warranty (SERIAL_NUMBER,PRODUCT_CODE) VALUES \n");

        file.transferTo(fileTmp);
        Workbook wb = new XSSFWorkbook(fileTmp);
        Sheet sheet = wb.getSheetAt(0);
        List<Row> rowList = new ArrayList<>();
        sheet.rowIterator().forEachRemaining(rowList::add);
        for (int i = 0; i < rowList.size(); i++) {
            Row row = rowList.get(i);
            queryBuilder.append("('");

            String serialNumber = row.getCell(0).getStringCellValue();
            queryBuilder.append(serialNumber).append("','");

            String productCode = row.getCell(1).getStringCellValue();

            if (i == rowList.size() - 1) {
                queryBuilder.append(productCode).append("')");
            } else {
                queryBuilder.append(productCode).append("'),\n");
            }
        }

        Query query = entityManager.createNativeQuery(queryBuilder.toString());
        query.executeUpdate();
    }
}

