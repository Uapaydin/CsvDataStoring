package com.utku.demo.data.repo;

import com.utku.demo.data.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 3:18 PM
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {
    List<Property> findPropertiesByCodeIn(List<String> codeList);

    Property getPropertiesByCode(String code);

}
