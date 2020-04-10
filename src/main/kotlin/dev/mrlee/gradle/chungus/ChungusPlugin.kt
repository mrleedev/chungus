package dev.mrlee.gradle.chungus

import org.gradle.api.Project
import org.gradle.api.Plugin


class ChungusPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("openApiFetch") { task ->
            task.doFirst {
                val client = ApiRegistryClient()
                val json = client.fetchOpenApiSpecForService()
                println(json)
            }
        }
    }
}
