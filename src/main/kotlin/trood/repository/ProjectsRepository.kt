package trood.repository

import trood.entity.ProjectEntity
import org.springframework.data.repository.CrudRepository

interface ProjectsRepository: CrudRepository<ProjectEntity,Int> {

}