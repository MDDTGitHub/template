package com.madd.template.pojo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 
 */
@Data
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Integer id;  //
    private String roleCode;  //
    private String roleName;  //

    private List<Permission> permissions;
     
    @Override
    public String toString() {
	 return ToStringBuilder.reflectionToString(this);
    }

}
