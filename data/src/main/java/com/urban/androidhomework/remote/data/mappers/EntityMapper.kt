package com.urban.androidhomework.remote.data.mappers

abstract class EntityMapper<Data, Domain> {

    abstract fun mapFromEntity(entity: Data): Domain

    abstract fun mapToEntity(domain: Domain): Data

    fun mapFromEntityList(entities: List<Data>): List<Domain> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntityList(domainModels: List<Domain>): List<Data> {
        return domainModels.map {
            mapToEntity(it)
        }
    }
}
