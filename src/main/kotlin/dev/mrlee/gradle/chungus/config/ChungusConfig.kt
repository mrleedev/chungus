package dev.mrlee.gradle.chungus.config

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Nested


abstract class ChungusConfig {

    @get:Input
    abstract var clientDir : String

    @get:Input
    abstract var specDir : String

    @get:Nested
    abstract val services : ServicesConfig

    init {
        applyDefaults()
    }

    private fun applyDefaults() {
        specDir = "chungus/spec"
        clientDir = "chungus/client"
    }
}