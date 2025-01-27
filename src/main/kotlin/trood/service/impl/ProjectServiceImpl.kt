package trood.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import trood.entity.ProjectEntity
import trood.dto.ProjectDto
import trood.repository.ProjectsRepository
import trood.service.ProjectService
import trood.exception.ProjectNotFoundException1


@Service
class ProjectServiceImpl(
        private val projectsRepository: ProjectsRepository,
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

    private fun ProjectEntity.toDto(): ProjectDto =
            ProjectDto(
                    id=this.id,
                    name=this.name,
                    experience=this.experience,
                    deadline=this.deadline,
                    field=this.field,
                    description=this.description,
            )

    private fun ProjectDto.toEntity(): ProjectEntity =
            ProjectEntity(
                    id=0,
                    name=this.name,
                    experience=this.experience,
                    deadline=this.deadline,
                    field=this.field,
                    description=this.description,
            )
}