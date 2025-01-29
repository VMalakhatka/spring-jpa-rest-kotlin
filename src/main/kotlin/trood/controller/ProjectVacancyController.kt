package trood.controller

import org.springframework.web.bind.annotation.*
import trood.dto.VacancyDto
import trood.service.VacancysServise

@RestController
@RequestMapping("/projects/{projectId}/vacancies")
class ProjectVacancyController(
    private val vacancysServise: VacancysServise,
) {
    @GetMapping
    fun getAllVacancies(@PathVariable projectId: Int): List<VacancyDto> = vacancysServise.getAllVacancies(projectId)
    @GetMapping("/{vacancyId}")
    fun getVacancyById(
        @PathVariable projectId: Int,
        @PathVariable vacancyId: Int
    ): VacancyDto  = vacancysServise.getVacancyById(vacancyId)
    @PostMapping
    fun addVacancy(@PathVariable projectId: Int, @RequestBody vacancyDto: VacancyDto) {
        vacancysServise.addVacancy(projectId, vacancyDto)
    }

}