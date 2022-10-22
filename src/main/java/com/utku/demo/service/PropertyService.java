package com.utku.demo.service;

import com.utku.demo.data.dto.PropertyDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 3:20 PM
 */
public interface PropertyService {

    String uploadCsv(MultipartFile file);

    List<PropertyDto> getAllProperties();
    PropertyDto getPropertyByCode(String code);

    String deleteAllProperties();
}
