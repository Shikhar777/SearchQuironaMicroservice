package com.example.SearchQuinora.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "educationsearch")
public class Education {

    @Id
    @GenericGenerator(name = "education_id_seq", strategy = "increment")
    @GeneratedValue(generator = "education_id_seq", strategy = GenerationType.AUTO)
    private int educationId;
    private String school;
    private String primaryDetail;
    private Long graduationYear;
    private String degree;
}
