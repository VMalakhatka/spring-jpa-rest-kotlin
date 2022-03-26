# Spring Data JPA и Kotlin
Пример restful-сервиса с использованием таких технологий как Spring Data JPA и Kotlin.

Таблица стран:

    create table country
    (
        id         serial constraint country_pk primary key,
        name       varchar not null,
        population integer not null
    );

Таблица городов, связанных со странами:

    create table city
    (
        id         serial constraint city_pk primary key,
        country_id integer not null
        constraint city_country_fk references country,
        name       varchar not null
    );
