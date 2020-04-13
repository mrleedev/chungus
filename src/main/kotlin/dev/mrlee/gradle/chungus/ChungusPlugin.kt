package dev.mrlee.gradle.chungus

import org.gradle.api.Project
import org.gradle.api.Plugin
import java.lang.IllegalStateException

private const val PLUGIN_NAME = "chungus"
private const val OPENAPI_CACHE = "openapi"

class ChungusPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create(PLUGIN_NAME, ChungusExtension::class.java, project)

        project.tasks.register("buildCache") {
            it.outputs.dir(project.buildDir.resolve(PLUGIN_NAME))

            it.doFirst {
                project.buildDir.resolve("${PLUGIN_NAME}/${OPENAPI_CACHE}").mkdir()
            }
        }

        project.tasks.register("fetchOpenApiSpecs") { task ->
            task.dependsOn("buildCache")

            task.outputs.cacheIf { true }

            task.doFirst {
                val client = ApiRegistryClient()

                extension.services.all { service ->
                    val json = client.fetchOpenApiSpecForService(service.url)
                    project.buildDir.resolve("${PLUGIN_NAME}/${OPENAPI_CACHE}/${service.name}.${service.format}").writeText(json)
                }
            }
        }

        project.pluginManager.withPlugin("org.openapi.generator") { plugin ->
            project.tasks.register("generateOpenApiClients") { task ->
                task.dependsOn("fetchOpenApiSpecs")
            }
        }
    }
}

internal fun Project.chungus(): ChungusExtension =
        extensions.getByName("chungus") as? ChungusExtension
                ?: throw  IllegalStateException("Chungus extension is not of correct type")
