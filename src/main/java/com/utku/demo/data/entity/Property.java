package com.utku.demo.data.entity;


import com.utku.demo.data.dto.PropertyDto;
import com.utku.demo.util.DateFormatter;
import com.utku.demo.util.Sanitizer;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Utku APAYDIN
 * @created 22/10/2022 - 3:17 PM
 */
@Getter
@Setter
@Entity
@Table(name = "t_properties")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
public class Property  {
    @Id
    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private Date fromDate;
    private Date toDate;
    private Integer sortingPriority;

    public PropertyDto map() {
        PropertyDto mappedData = new PropertyDto();
        mappedData.setCode(this.getCode());
        mappedData.setSource(this.getSource());
        mappedData.setCodeListCode(this.getCodeListCode());
        mappedData.setDisplayValue(this.getDisplayValue());
        mappedData.setLongDescription(this.getLongDescription());
        mappedData.setSortingPriority(this.getSortingPriority());
        mappedData.setFromDate(this.getFromDate()== null ? null:DateFormatter.FORMATTER.format(this.getFromDate()));
        mappedData.setToDate(this.getToDate()== null ? null:DateFormatter.FORMATTER.format(this.getToDate()));
        return mappedData;
    }
}
