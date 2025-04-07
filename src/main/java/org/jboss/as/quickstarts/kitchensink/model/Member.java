package org.jboss.as.quickstarts.kitchensink.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Document(collection = "member")  // MongoDB collection name
public class Member implements Serializable {

    @Id  // This is the MongoDB id
    private String id;  // Use String (ObjectId as default) for MongoDB

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    @Field("name")  // Field annotation to map this attribute to MongoDB
    private String name;

    @NotNull
    @NotEmpty
    @Email
    @Field("email")  // Field annotation to map this attribute to MongoDB
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Pattern(regexp = "^[789]\\d{9}$", message = "Invalid phone number. It should be 10 digits and start with 7, 8, or 9.")
    @Field("phone_number")  // Field annotation to map this attribute to MongoDB
    private String phoneNumber;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
