package com.example.SearchQuinora.entity.newentity;

import com.example.SearchQuinora.entity.Education;
import com.example.SearchQuinora.entity.Employment;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDetailsFromUser {

    //@Id
    //@org.springframework.data.annotation.Id
    //@GenericGenerator(name = "user_id_seq", strategy = "increment")
    //@GeneratedValue(generator = "user_id_seq", strategy = GenerationType.AUTO)
    private int userId;
    //@NotBlank
    //@Size(min = 1, max = 100)
    private String firstName;
    private String lastName;
    //@Column(unique = true)
    private String username;
    private String profileCredential;
    private String address;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime joiningDate;
    private String bio;
    private String profileImage;

    //@OneToMany(cascade = CascadeType.ALL)
    private List<Education> education;

    //@OneToMany(cascade = CascadeType.ALL)
    private List<Employment> employment;

    @Override
    public String toString() {
        return "UserDetailsFromUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", profileCredential='" + profileCredential + '\'' +
                ", address='" + address + '\'' +
                ", bio='" + bio + '\'' +
                ", education=" + education +
                ", employment=" + employment +
                '}';
    }
}
