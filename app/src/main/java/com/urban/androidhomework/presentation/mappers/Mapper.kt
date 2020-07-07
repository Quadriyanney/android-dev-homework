package com.urban.androidhomework.presentation.mappers

interface Mapper<Domain, UI> {

    fun mapToUI(domain: Domain): UI

    fun mapToDomain(ui: UI): Domain

    fun mapToDomainList(uiList: List<UI>): List<Domain> {
        return uiList.map {
            mapToDomain(it)
        }
    }

    fun mapToUIList(domainList: List<Domain>): List<UI> {
        return domainList.map {
            mapToUI(it)
        }
    }
}
