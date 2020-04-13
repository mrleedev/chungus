package dev.mrlee.gradle.chungus

import groovy.lang.Closure
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

internal typealias ServicesConfigContainer = NamedDomainObjectContainer<ServiceConfig>

open class ChungusExtension(private val project: Project) {

    var specDir by GradleProperty(project, String::class.java, "chungus/openapi")
    var clientDir by GradleProperty(project, String::class.java, "chungus/client")

    val services: ServicesConfigContainer = project.container(ServiceConfig::class.java) { name ->
        ServiceConfig(name, project)
    }

    fun services(config: ServicesConfigContainer.() -> Unit) {
        services.configure(object: Closure<Unit>(this, this) {
            fun doCall() {
                (delegate as? ServicesConfigContainer)?.let {
                    config(it)
                }
            }
        })
    }

    fun services(config: Closure<Unit>) = services.configure(config)
}