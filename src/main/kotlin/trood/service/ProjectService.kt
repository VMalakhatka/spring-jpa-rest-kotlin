package trood.service

import trood.dto.ProjectDto

interface ProjectService {
    fun getAll(): List<ProjectDto>

    fun getById(id: Int): ProjectDto

    fun create(dto: ProjectDto): Int

    fun update(id: Int, dto: ProjectDto)

    fun delete(id: Int)
}