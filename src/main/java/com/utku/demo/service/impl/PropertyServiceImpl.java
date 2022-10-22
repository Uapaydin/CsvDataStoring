package com.utku.demo.service.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.utku.demo.data.dto.PropertyDto;
import com.utku.demo.data.entity.Property;
import com.utku.demo.data.repo.PropertyRepository;
import com.utku.demo.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 3:41 PM
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Override
    public List<PropertyDto> getAllProperties(){
        List<Property> existingProperties = propertyRepository.findAll();
        return existingProperties.stream().map(Property::map).toList();
    }

    @Override
    public PropertyDto getPropertyByCode(String code){
        Property existingProperties = propertyRepository.getPropertiesByCode(code);
        if(existingProperties == null){
            return new PropertyDto();
        }else{
            return existingProperties.map();
        }
    }

    @Override
    public String deleteAllProperties() {
        Long propertyCount = propertyRepository.count();
        propertyRepository.deleteAll();
        return propertyCount + " properties deleted";
    }


    @Override
    public String uploadCsv(MultipartFile file) {
        try{
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            CsvToBean<PropertyDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(PropertyDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<PropertyDto> propertyList = csvToBean.parse();
            handlePropertyUpdate(propertyList);
            return propertyList.size() + " proeprties created";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    private void handlePropertyUpdate(List<PropertyDto> propertyDtoList){
        List<Property> existingProperties = propertyRepository.findPropertiesByCodeIn(propertyDtoList.stream().map(PropertyDto::getCode).toList());
        //update existing items
        existingProperties.forEach(existingProperty ->
                propertyDtoList.stream()
                        .filter(sourceItem -> sourceItem.getCode().equals(existingProperty.getCode())).findAny()
                        .ifPresent(propertyDto -> propertyRepository.save(propertyDto.map())));
        //create new items
        propertyDtoList.stream().filter(item-> !existingProperties.stream().map(Property::getCode).toList()
                .contains(item.getCode())).forEach(item ->existingProperties.add(item.map()));
        propertyRepository.saveAll(existingProperties);
    }

}
