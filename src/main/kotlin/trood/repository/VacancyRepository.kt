package trood.repository

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import trood.entity.ProjectEntity
import trood.entity.VacancyEntity
@Repository
interface VacancyRepository: CrudRepository<VacancyEntity,Int> {
    fun findAllByProjectId(projectId: Int): List<VacancyEntity>

    @EntityGraph(attributePaths = ["project"])
    fun findWithProjectById(id: Int): VacancyEntity?

}
