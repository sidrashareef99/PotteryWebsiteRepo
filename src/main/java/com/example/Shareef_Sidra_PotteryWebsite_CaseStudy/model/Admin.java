package com.example.Shareef_Sidra_PotteryWebsite_CaseStudy.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Admin extends User{
    public Admin() {
        super();
        setRole(Role.ADMIN);
    }
}
