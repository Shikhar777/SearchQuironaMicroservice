package com.example.SearchQuinora.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "employmentsearch")
public class Employment {

    @Id
    @GenericGenerator(name = "employment_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employment_id_seq", strategy = GenerationType.AUTO)
    private int employmentId;
    private String position;
    private String organization;
    private String startYear;
    private String endYear;
}
