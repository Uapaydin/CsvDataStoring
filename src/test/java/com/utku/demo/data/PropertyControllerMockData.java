package com.utku.demo.data;

import com.utku.demo.data.dto.PropertyDto;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 5:25 PM
 */
public class PropertyControllerMockData {

    public static PropertyDto validPropertyResponseModel = PropertyDto.builder()
            .code("code1")
            .codeListCode("codeListCode1")
            .displayValue("displayValue1")
            .longDescription("longDescription1")
            .source("source1")
            .fromDate(null)
            .toDate(null)
            .build();

}
