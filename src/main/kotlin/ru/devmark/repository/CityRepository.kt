package ru.devmark.repository

import org.springframework.data.repository.CrudRepository
import ru.devmark.entity.CityEntity
import ru.devmark.entity.CountryEntity

interface CityRepository: CrudRepository<CityEntity, Int> {

    fun deleteAllByCountry(country: CountryEntity)
}