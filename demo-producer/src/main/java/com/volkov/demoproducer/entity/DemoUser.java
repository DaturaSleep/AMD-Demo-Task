package com.volkov.demoproducer.entity;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Document("demoUser")
@Valid
public class DemoUser {
    public final static int USERNAME_MAX_LENGTH = 30;
    public final static int FIRSTNAME_MAX_LENGTH = 50;

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "username must not be blank")
    @Size(max = USERNAME_MAX_LENGTH,
            message = "username must not be longer than " + USERNAME_MAX_LENGTH + " in length")
    private String username;

    @Indexed(unique = true)
    @NotBlank(message = "firstname must not be blank")
    @Size(max = FIRSTNAME_MAX_LENGTH,
            message = "firstname must not be longer than " + FIRSTNAME_MAX_LENGTH + " in length")
    private String firstname;

    LocalDateTime creationDate;

    public DemoUser(String username, String firstname) {
        this.username = username;
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "DemoUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
