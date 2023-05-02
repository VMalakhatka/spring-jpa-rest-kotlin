package ru.devmark.entity

import jakarta.persistence.*

@Entity
@Table(name = "country")
class CountryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var name: String = "",
    var population: Int = 0,
    @OneToMany(mappedBy = "country")
    var cities: List<CityEntity> = emptyList(),
)
