package dev.mrlee.gradle.chungus.config

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import java.net.URI


typealias ServicesConfig = NamedDomainObjectContainer<ServiceConfig>

abstract class ServiceConfig(val name: String) {
    @get:Input
    abstract var url : URI

    @get:Input
    abstract var format : String

    @get:Nested
    abstract val client : NamedDomainObjectContainer<ClientConfig>

    init {
        applyDefaults()
    }

    private fun applyDefaults() {
        format = "yaml"
    }

}