package trood.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import trood.entity.ProjectEntity
import trood.dto.ProjectDto
import trood.dto.VacancyDto
import trood.entity.VacancyEntity
import trood.repository.ProjectsRepository
import trood.service.ProjectService
import trood.exception.ProjectNotFoundException1
import trood.mapper.ProjectMapper


@Service
class ProjectServiceImpl(
        private val projectsRepository: ProjectsRepository,
        private val mapper: ProjectMapper,
) : ProjectService {
    override fun getAll(): List<ProjectDto> =
        projectsRepository.findAll().map { it.toDto() }


    override fun getById(id: Int): ProjectDto =
        projectsRepository.findByIdOrNull(id)
                ?.toDto()
                ?:throw ProjectNotFoundException1(id)



    override fun create(dto: ProjectDto): Int {
        val projectEntity = projectsRepository.save(dto.toEntity())
        return projectEntity.id
    }

    override fun update(id: Int, dto: ProjectDto) {
        var existingProject = projectsRepository.findByIdOrNull(id)
                ?: throw ProjectNotFoundException1(id)
        existingProject.name=dto.name
        existingProject.field=dto.field
        existingProject.deadline=dto.deadline
        existingProject.experience=dto.experience
        existingProject.description=dto.description
        existingProject=projectsRepository.save(existingProject)
    }

    override fun delete(id: Int) {
        val existingProject = projectsRepository.findByIdOrNull(id)
                ?: throw ProjectNotFoundException1(id)
        projectsRepository.delete(existingProject)
    }

    private fun ProjectEntity.toDto(): ProjectDto = mapper.toDto(this)
//            ProjectDto(
//                    id=this.id,
//                    name=this.name,
//                    experience=this.experience,
//                    deadline=this.deadline,
//                    field=this.field,
//                    description=this.description,
//                    vacancies = vacancies.map { it.toDto() }  // Convert vacancies list
//            )

    private fun VacancyEntity.toDto(): VacancyDto = mapper.toDto(this)
//             VacancyDto(
//                id = id,
//                name = name,
//                experience = experience,
//                field = field,
//                country = country,
//                description = description
//            )


    private fun ProjectDto.toEntity(): ProjectEntity =mapper.toEntity(this)
//
//    {
//        val projectEntity = ProjectEntity(
//            id = 0,
//            name = this.name,
//            experience = this.experience,
//            deadline = this.deadline,
//            field = this.field,
//            description = this.description
//        )
//        // Linking vacancies to the project
//        projectEntity.vacancies = this.vacancies.map { it.toEntity(projectEntity) }.toMutableList()
//
//        return projectEntity
//    }
    fun VacancyDto.toEntity(inProject: ProjectEntity): VacancyEntity = mapper.toEntity(this,inProject)
//        VacancyEntity(
//            id = 0,
//            name = this.name,
//            experience = this.experience,
//            field = this.field,
//            country = this.country,
//            description = this.description,
//            project = inProject,
//        )

}