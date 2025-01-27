package trood.service.impl

import trood.entity.ProjectEntity
import trood.dto.ProjectDto
import trood.repository.ProjectsRepository
import trood.service.ProjectService

class ProjectServiceImpl(
        private val projectsRepository: ProjectsRepository,
) : ProjectService {
    override fun getAll(): List<ProjectDto> =
        projectsRepository.findAll().map { it.toDto() }


    override fun getById(id: Int): ProjectDto {
        TODO("Not yet implemented")
    }

    override fun create(dto: ProjectDto): Int {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, dto: ProjectDto) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
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
}