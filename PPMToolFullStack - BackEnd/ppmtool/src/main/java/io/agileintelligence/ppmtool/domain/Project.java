package io.agileintelligence.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Requirido nome do Projeto")
    private String projectName;
    @NotBlank(message = "Requirido Identificador do Projeto")
    @Size(min=4, max=5, message = "Por favor use de 4 a 5 caracteres")
    @Column(updatable = false, unique = true)
    private String projectIdentifier; //Será usado como ID é, para buscar o valor único (na URL, regra pedida)
    @NotBlank(message = "Requirido descrição do Projeto")
    private String description;
    @JsonFormat(pattern = "dd-MM-yyyy")//melhor formato Json para Date, formato original e timestamp, mês MM pra não dar erro
    private Date start_date;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date end_date;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date created_At;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updated_At;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    //Criar registra date automaticamente
    //toda vez que um objeto for criado
    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
}
