package guarantee.backend.service.Impl;

import guarantee.backend.DTO.WarrantyActiveDTO;
import guarantee.backend.DTO.WarrantyCardDTO;
import guarantee.backend.model.Customer;
import guarantee.backend.model.Product;
import guarantee.backend.model.WarrantyCard;
import guarantee.backend.repositories.CustomerRepository;
import guarantee.backend.repositories.WarrantyCardDAO;
import guarantee.backend.repositories.WarrantyCardRepository;
import guarantee.backend.service.IWarrantyService;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private WarrantyCardDAO warrantyCardDAO;

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
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
        String uploadsDir = "/uploads/";
        String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
        if (!new File(realPathToUploads).exists()) {
            new File(realPathToUploads).mkdir();
        }
        String fileName = date + file.getOriginalFilename();
        String filePath = realPathToUploads + fileName;
        File fileTmp = new File(filePath);
        file.transferTo(fileTmp);

        Workbook wb = new XSSFWorkbook(new FileInputStream(fileTmp));
        Sheet sheet = wb.getSheetAt(0);
        List<Row> rowList = new ArrayList<>();
        sheet.rowIterator().forEachRemaining(rowList::add);

        queryBuilder.append("INSERT INTO warranty (SERIAL_NUMBER,PRODUCT_CODE) VALUES \n");
        for (int i = 1; i < rowList.size(); i++) {
            Row row = rowList.get(i);

            Cell serialCell = row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            Cell prodCodeCell = row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            serialCell.setCellType(CellType.STRING);
            prodCodeCell.setCellType(CellType.STRING);
            String serialNumber = serialCell.getStringCellValue();
            String productCode = prodCodeCell.getStringCellValue();

            if (serialNumber.trim().isEmpty() || productCode.trim().isEmpty()) {
                if (i == rowList.size() - 1) {
                    queryBuilder.setLength(queryBuilder.length() - 2);
                }
                continue;
            }
            queryBuilder.append("('");
            queryBuilder.append(serialNumber).append("','");

            if (i == rowList.size() - 1) {
                queryBuilder.append(productCode).append("')");
            } else {
                queryBuilder.append(productCode).append("'),\n");
            }
        }
        try {
            Query query = entityManager.createNativeQuery(queryBuilder.toString());
            query.executeUpdate();
        } finally {
            wb.close();
            if (fileTmp.delete()) {
                System.out.println(fileTmp.getName() + " is deleted!");
            } else {
                System.out.println("Sorry, unable to delete the file.");
            }
        }
    }

    @Override
    public List<WarrantyCard> findByStatus(int status) {
        List<WarrantyCard> warrantyCardList;
        if (status == 0) {
            warrantyCardList = warrantyCardRepository.findByCustomerIsNull();
        } else {
            warrantyCardList = warrantyCardRepository.findByCustomerIsNotNull();
        }
        return warrantyCardList;
    }

    @Override
    public List<WarrantyCardDTO> getAllWarrantyCardDTO() {
            String sql = "SELECT * FROM warranty WHERE customer_id is null";
            Query query = entityManager.createNativeQuery(sql).unwrap(NativeQuery.class)
                    .setResultSetMapping("mappingWarrantyCardDTO");


//                    .setResultTransformer(Transformers.aliasToBean(WarrantyCardDTO.class));



            return query.getResultList();
    }
}

