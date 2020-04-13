package dev.mrlee.gradle.chungus

import groovy.lang.Closure
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import java.lang.IllegalArgumentException

internal typealias ClientConfigContainer = NamedDomainObjectContainer<ClientConfig>

open class ServiceConfig(val name: String, project: Project) {
    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Service name must not be blank or empty")
        }
    }

    var url by GradleProperty(project, String::class.java)
    var format by GradleProperty(project, String::class.java, "yaml")

    val client: ClientConfigContainer = project.container(ClientConfig::class.java) {
        ClientConfig(project)
    }

    fun client(config: ClientConfigContainer.() -> Unit) {
        client.configure(object: Closure<Unit>(this, this) {
            fun doCall() {
                (delegate as? ClientConfigContainer)?.let {
                    config(it)
                }
            }
        })
    }

    fun client(config: Closure<Unit>) = client.configure(config)
}