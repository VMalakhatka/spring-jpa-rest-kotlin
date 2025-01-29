package trood.controller

import org.springframework.web.bind.annotation.*
import trood.dto.VacancyDto
import trood.service.VacancysServise

@RestController
@RequestMapping("/vacancies")
class VacancyController(
    private val vacancysServise: VacancysServise
) {

    @GetMapping("/{vacancyId}")
    fun getVacancyById(
        @PathVariable vacancyId: Int
    ): VacancyDto = vacancysServise.getVacancyById( vacancyId)

    @PutMapping("/{vacancyId}")
    fun update(@PathVariable vacancyId: Int, @RequestBody vacancyDto: VacancyDto) {
        vacancysServise.update(vacancyId, vacancyDto)
    }

    @DeleteMapping("/{vacancyId}")
    fun deleteVacancy(@PathVariable vacancyId: Int) {
        vacancysServise.deleteVacancy(vacancyId)
    }
}