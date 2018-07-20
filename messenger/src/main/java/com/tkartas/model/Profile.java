package com.tkartas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
public class Profile {

    // == fields ==
    private long id;
    private String profileName;
    private String firstName;
    private String lastName;
    private Date created;

    // == constructors ==
    public Profile(long id, String profileName, String firstName, String lastName) {
        this.id = id;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = new Date();
    }
}
