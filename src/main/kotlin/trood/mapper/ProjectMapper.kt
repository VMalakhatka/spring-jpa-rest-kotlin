package trood.mapper

import org.springframework.stereotype.Component
import trood.dto.ProjectDto
import trood.dto.VacancyDto
import trood.entity.ProjectEntity
import trood.entity.VacancyEntity

@Component
class ProjectMapper {

    fun toEntity(dto: ProjectDto): ProjectEntity {
        val projectEntity = ProjectEntity(
            id = 0,
            name = dto.name,
            experience = dto.experience,
            deadline = dto.deadline,
            field = dto.field,
            description = dto.description
        )

        projectEntity.vacancies = dto.vacancies.map { toEntity(it, projectEntity) }.toMutableList()
        return projectEntity
    }

    fun toEntity(dto: VacancyDto, project: ProjectEntity): VacancyEntity {
        return VacancyEntity(
            id = 0,
            name = dto.name,
            experience = dto.experience,
            field = dto.field,
            country = dto.country,
            description = dto.description,
            project = project
        )
    }

    fun toDto(entity: ProjectEntity): ProjectDto {
        return ProjectDto(
            id = entity.id,
            name = entity.name,
            experience = entity.experience,
            deadline = entity.deadline,
            field = entity.field,
            description = entity.description,
            vacancies = entity.vacancies.map { toDto(it) }
        )
    }

    fun toDto(entity: VacancyEntity): VacancyDto {
        return VacancyDto(
            id = entity.id,
            name = entity.name,
            experience = entity.experience,
            field = entity.field,
            country = entity.country,
            description = entity.description,
        )
    }
}