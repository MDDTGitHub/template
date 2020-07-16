package com.madd.template.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;  //
    private String username;  //
    private String password;  //
    private String nickname;  //
    private String phone;  //
    private Integer delFlag;  //
    private String email;  //
    private Date created;  //
    private Date updated;  //

    private Set<String> roles;
    
     
    @Override
    public String toString() {
	 return ToStringBuilder.reflectionToString(this);
    }

}
