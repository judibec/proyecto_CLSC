package com.financialLab.financedevapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends GenericModel<User>{

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String goal;
    private String points;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @OneToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_for_user", inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> userRoles = new HashSet<>();

}
