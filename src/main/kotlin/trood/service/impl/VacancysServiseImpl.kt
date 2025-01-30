package trood.service.impl

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import trood.dto.VacancyDto
import trood.entity.VacancyEntity
import trood.exception.ProjectNotFoundException1
import trood.exception.VacancyNotFoundExeption
import trood.mapper.ProjectMapper
import trood.repository.ProjectsRepository
import trood.repository.VacancyRepository
import trood.service.VacancysServise
@Service
class VacancysServiseImpl(
    private val projectsRepository: ProjectsRepository,
    private val vacancyRepository: VacancyRepository,
    private val projectMapper: ProjectMapper,
) : VacancysServise {
    override fun getAllVacancies(projectId: Int): List<VacancyDto> =
        vacancyRepository.findAllByProjectId(projectId).map { it.toDto() }

    override fun getVacancyById(vacancyId: Int): VacancyDto =
        vacancyRepository.findByIdOrNull(vacancyId)
            ?.toDto()
            ?: throw VacancyNotFoundExeption(vacancyId)


    override fun getVacancyById(projectId: Int, vacancyId: Int): VacancyDto =
        vacancyRepository.findByIdOrNull(vacancyId)
            ?.toDto()
            ?: throw VacancyNotFoundExeption(vacancyId)

    @Transactional
    override fun addVacancy(projectId: Int, vacancyDto: VacancyDto) {
        val projectEntity = projectsRepository.findByIdOrNull(projectId)?:throw ProjectNotFoundException1(projectId)
        projectEntity.addVacancy(projectMapper.toEntity(vacancyDto,projectEntity))
        projectsRepository.save(projectEntity)
    }
    @Transactional
    override fun deleteVacancy(vacancyId: Int) {
        val existingVacancy = vacancyRepository.findWithProjectById(vacancyId)
            ?: throw VacancyNotFoundExeption(vacancyId)
        val existingProject = existingVacancy.project?:throw ProjectNotFoundException1(vacancyId)
        existingVacancy.project!!.removeVacancy(existingVacancy)
        projectsRepository.save(existingProject)
        vacancyRepository.delete(existingVacancy)
    }
    @Transactional
    override fun update(vacancyId: Int, vacancyDto: VacancyDto) {
        val vacancyEntity=vacancyRepository.findByIdOrNull(vacancyId)?:throw VacancyNotFoundExeption(vacancyId)
        vacancyEntity.name=vacancyDto.name
        vacancyEntity.field=vacancyDto.field
        vacancyEntity.experience=vacancyDto.experience
        vacancyEntity.description=vacancyDto.description
        vacancyEntity.country=vacancyDto.country
        vacancyRepository.save(vacancyEntity)
    }

    fun VacancyEntity.toDto():VacancyDto= projectMapper.toDto(this)
}