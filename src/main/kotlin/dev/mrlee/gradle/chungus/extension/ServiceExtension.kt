package dev.mrlee.gradle.chungus.extension

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested
import java.net.URI
import java.net.URL


abstract class ServiceExtension(val name: String) {
    @get:Input
    abstract var url : URI

    @get:Input
    abstract var format : String

    @get:Nested
    abstract val client : NamedDomainObjectContainer<ClientExtension>

    init {
        applyDefaults()
    }

    private fun applyDefaults() {
        format = "yaml"
    }

}