package dev.mrlee.gradle.chungus.extension

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested

abstract class ChungusExtension {

    @get:Input
    abstract var clientDir : String

    @get:Input
    abstract var specDir : String

    @get:Nested
    abstract val services : NamedDomainObjectContainer<ServiceExtension>

    init {
        applyDefaults()
    }

    private fun applyDefaults() {
        specDir = "chungus/spec"
        clientDir = "chungus/client"
    }
}