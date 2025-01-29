package trood.dto

import trood.service.Field

data class VacancyDto(
    val id: Int,
    val name: String,
    val experience: Int,
    val field: Field,
    val country: String,
    val description: String
)
