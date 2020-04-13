package dev.mrlee.gradle.chungus

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project
import org.gradle.api.Plugin
import java.lang.IllegalStateException

class ChungusPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("chungus", ChungusExtension::class.java, project)

        project.tasks.register("buildCache") {
            it.outputs.dirs(
                project.buildDir.resolve(extension.specDir),
                project.buildDir.resolve(extension.clientDir)
            )

            it.doFirst {
                project.buildDir.resolve(extension.specDir).mkdir()
                project.buildDir.resolve(extension.clientDir).mkdir()
            }
        }

        project.tasks.register("fetchOpenApiSpecs") { task ->
            task.dependsOn("buildCache")

            task.outputs.cacheIf { true }

            task.doFirst {
                val client = ApiRegistryClient()
                runBlocking {
                    extension.services.all { service ->
                        launch {
                            val json = client.fetchOpenApiSpecForService(service.url)
                            project.buildDir.resolve("${extension.specDir}/${service.name}.${service.format}").writeText(json)
                        }
                    }
                }
            }
        }

        project.tasks.register("generateOpenApiClients") { task ->
            task.dependsOn("fetchOpenApiSpecs")

            
        }

    }
}

internal fun Project.chungus(): ChungusExtension =
        extensions.getByName("chungus") as? ChungusExtension
                ?: throw  IllegalStateException("Chungus extension is not of correct type")
