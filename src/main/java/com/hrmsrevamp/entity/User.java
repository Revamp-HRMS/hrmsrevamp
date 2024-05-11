package com.hrmsrevamp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrmsrevamp.constant.UserStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * User entity.
 */
@Data
@Builder
@Entity
@Table(name = "users")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull(message = "Email must not be null")
    @NotEmpty(message = "Email must not be empty")
    private String email;
    private String mobileNumber;
    @Size(max = 50, message = "fullName should be under 50 chars")
    private String fullName;
    private String password;
    @NotNull(message = "Status must not be null")
    @NotEmpty(message = "Status must not empty")
    private String status = UserStatusEnum.ACTIVE.name();
    private Boolean userRegistered = false;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles;
}
