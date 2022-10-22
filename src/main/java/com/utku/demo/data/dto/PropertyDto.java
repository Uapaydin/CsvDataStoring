package com.utku.demo.data.dto;

import com.opencsv.bean.CsvBindByName;
import com.utku.demo.data.entity.Property;
import com.utku.demo.util.DateFormatter;
import com.utku.demo.util.Sanitizer;
import lombok.*;

import java.text.ParseException;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 3:51 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PropertyDto {

    @CsvBindByName
    private String code;
    @CsvBindByName
    private String source;
    @CsvBindByName
    private String codeListCode;
    @CsvBindByName
    private String displayValue;
    @CsvBindByName
    private String longDescription;
    @CsvBindByName
    private String fromDate;
    @CsvBindByName
    private String toDate;
    @CsvBindByName
    private Integer sortingPriority;

    public Property map(){
        Property mappedData = new Property();
        mappedData.setCode(Sanitizer.cleanIt(this.getCode().isEmpty()?"":this.getCode()));
        mappedData.setSource(Sanitizer.cleanIt(this.getSource().isEmpty()?"":this.getSource()));
        mappedData.setCodeListCode(Sanitizer.cleanIt(this.getCodeListCode().isEmpty()?"":this.getCodeListCode()));
        mappedData.setDisplayValue(Sanitizer.cleanIt(this.getDisplayValue().isEmpty()?"":this.getDisplayValue()));
        mappedData.setLongDescription(Sanitizer.cleanIt(this.getLongDescription().isEmpty()?"":this.getLongDescription()));
        mappedData.setSortingPriority(this.getSortingPriority());
        try{
            mappedData.setFromDate(DateFormatter.FORMATTER.parse(this.getFromDate()));
        }catch (ParseException e){
            mappedData.setFromDate(null);
        }
        try{
            mappedData.setToDate(DateFormatter.FORMATTER.parse(this.getToDate()));
        }catch (ParseException e){
            mappedData.setToDate(null);
        }
        return mappedData;
    }



}
