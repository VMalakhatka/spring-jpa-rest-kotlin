package trood.service

import trood.dto.VacancyDto

interface VacancysServise {
    fun getAllVacancies(projectId: Int): List<VacancyDto>
    fun getVacancyById(projectId: Int, vacancyId: Int): VacancyDto
    fun getVacancyById( vacancyId: Int): VacancyDto
    fun addVacancy(projectId: Int, vacancyDto: VacancyDto)
    fun deleteVacancy(vacancyId: Int)
    fun update(vacancyId: Int, vacancyDto: VacancyDto)
}