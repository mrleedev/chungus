package dev.mrlee.gradle.chungus

import org.gradle.api.Project
import java.lang.IllegalArgumentException

class ServiceConfig(val name: String, project: Project) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Service name must not be blank or empty")
        }
    }

    var url by GradleProperty(project, String::class.java)
    var format by GradleProperty(project, String::class.java, "yaml")
}