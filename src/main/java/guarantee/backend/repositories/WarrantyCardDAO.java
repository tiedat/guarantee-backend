package guarantee.backend.repositories;

import guarantee.backend.DTO.WarrantyCardDTO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class WarrantyCardDAO {

    @Autowired(required = false)
    private EntityManager entityManager;

    public Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    public List<WarrantyCardDTO> getAllWarrantyCardDTO() {
        Session session = getSession();
        String sql = "SELECT * FROM warranty";
        Query query = session.createSQLQuery(sql).unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(WarrantyCardDTO.class));
        return query.getResultList();
    }

}
