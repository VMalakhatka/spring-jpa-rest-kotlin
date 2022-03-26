package ru.devmark.service.impl

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.devmark.dto.CityDto
import ru.devmark.dto.CountryDto
import ru.devmark.entity.CityEntity
import ru.devmark.entity.CountryEntity
import ru.devmark.exception.CountryNotFoundException
import ru.devmark.repository.CityRepository
import ru.devmark.repository.CountryRepository
import ru.devmark.service.CountryService

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository,
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

    override fun getCountryNames(): List<String> =
        countryRepository.findAllByOrderByName().map { it.name }

    @Transactional
    override fun create(dto: CountryDto): Int {
        val countryEntity = countryRepository.save(dto.toEntity())
        val cities = dto.cities.map { it.toEntity(countryEntity) }
        cityRepository.saveAll(cities)
        return countryEntity.id
    }

    @Transactional
    override fun update(id: Int, dto: CountryDto) {
        var existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        existingCountry.name = dto.name
        existingCountry.population = dto.population

        existingCountry = countryRepository.save(existingCountry)

        val cities = dto.cities.map { it.toEntity(existingCountry) }
        cityRepository.deleteAllByCountry(existingCountry)
        cityRepository.saveAll(cities)
    }

    @Transactional
    override fun delete(id: Int) {
        val existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        cityRepository.deleteAllByCountry(existingCountry)
        countryRepository.deleteById(existingCountry.id)
    }

    private fun CountryEntity.toDto(): CountryDto =
        CountryDto(
            id = this.id,
            name = this.name,
            population = this.population,
            cities = this.cities.map { it.toDto() },
        )

    private fun CityEntity.toDto(): CityDto =
        CityDto(
            name = this.name,
        )

    private fun CountryDto.toEntity(): CountryEntity =
        CountryEntity(
            id = 0,
            name = this.name,
            population = this.population,
        )

    private fun CityDto.toEntity(country: CountryEntity): CityEntity =
        CityEntity(
            id = 0,
            name = this.name,
            country = country,
        )
}