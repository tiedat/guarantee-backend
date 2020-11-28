package guarantee.backend.service;

import guarantee.backend.model.TypeStation;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public interface ITypeStationService {
    List<TypeStation> getAll();
}
