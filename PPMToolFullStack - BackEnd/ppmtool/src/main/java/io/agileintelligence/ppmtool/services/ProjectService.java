package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);

        } catch (Exception e) {
           throw new ProjectIdException("Projeto ID '" +project.getProjectIdentifier().toUpperCase()+"' já existe");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase()); //Nota: importante lembrar do toUpperCase pq CamelCase

        //Quando não objeto não é achado, não gera erro mas sim valor "null"
        //Logo não passa pelo try/catch, necessário usar if.
        if(project == null){
            throw new ProjectIdException("Projeto ID '" +projectId.toUpperCase()+"' não existe");
        }

        return project;
    }
}
