package ru.devmark.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.devmark.dto.CountryDto
import ru.devmark.entity.CountryEntity
import ru.devmark.exception.CountryNotFoundException
import ru.devmark.repository.CountryRepository
import ru.devmark.service.CountryService

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
) : CountryService {

    override fun getAll(pageIndex: Int): List<CountryDto> =
        countryRepository.findByOrderByName(PageRequest.of(pageIndex, 2))
            .map { it.toDto() }

    override fun getById(id: Int): CountryDto =
        countryRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw CountryNotFoundException(id)

    override fun search(prefix: String): List<CountryDto> =
        countryRepository.findByNameStartsWithIgnoreCaseOrderByName(prefix)
            .map { it.toDto() }

    override fun create(dto: CountryDto): Int =
        countryRepository.save(dto.toEntity()).id

    override fun update(id: Int, dto: CountryDto) {
        val existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        existingCountry.name = dto.name
        existingCountry.population = dto.population

        countryRepository.save(existingCountry)
    }

    override fun delete(id: Int) {
        val existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        countryRepository.deleteById(existingCountry.id)
    }

    private fun CountryEntity.toDto(): CountryDto =
        CountryDto(
            id = this.id,
            name = this.name,
            population = this.population,
        )

    private fun CountryDto.toEntity(): CountryEntity =
        CountryEntity(
            id = 0,
            name = this.name,
            population = this.population,
        )
}