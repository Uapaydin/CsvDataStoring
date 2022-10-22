package com.utku.demo.controller;

import com.utku.demo.data.dto.PropertyDto;
import com.utku.demo.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 3:36 PM
 */
@RestController
@RequiredArgsConstructor
public class PropertyController {

    private static final String VALID_FILE_TYPE= "TEXT/CSV";
    private static final String INVALID_FILE_RESPONSE = "Please select a CSV file to upload";
    private final PropertyService propertyService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadCsVFile(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body(INVALID_FILE_RESPONSE);
        }else if (Boolean.TRUE.equals(StringUtils.equalsIgnoreCase(VALID_FILE_TYPE,file.getContentType()))){
            return ResponseEntity.accepted().body(propertyService.uploadCsv(file));
        }else {
            return ResponseEntity.badRequest().body(INVALID_FILE_RESPONSE);
        }
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDto>> getAllProperties(){
        return ResponseEntity.ok(propertyService.getAllProperties());
    }

    @GetMapping("/property/{code}")
    public ResponseEntity<PropertyDto> getPropertyByCode(@PathVariable String code){
        return ResponseEntity.ok(propertyService.getPropertyByCode(code));
    }

    @DeleteMapping("/properties")
    public ResponseEntity<String> deleteAllProperties(){
        return ResponseEntity.ok(propertyService.deleteAllProperties());
    }

}
