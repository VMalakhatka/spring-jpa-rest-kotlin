package trood.dto

import trood.service.Field
import java.time.LocalDate

data class ProjectDto(
        var id: Int =0,
        var name: String,
        var experience : Int,
        var deadline : LocalDate,
        val field: Field,
        val description: String,
        val vacancies: List<VacancyDto> = emptyList()  // DTO для вакансий
 )
