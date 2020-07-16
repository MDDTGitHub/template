package com.madd.template.pojo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * 
 */
@Data
public class UserRole implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;  //
    private Integer userId;  //
    private Integer roleId;  //
    
     
    @Override
    public String toString() {
	 return ToStringBuilder.reflectionToString(this);
    }

}
