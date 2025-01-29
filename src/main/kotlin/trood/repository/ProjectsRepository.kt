package trood.repository

import trood.entity.ProjectEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectsRepository: CrudRepository<ProjectEntity,Int> {

}