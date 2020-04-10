package dev.mrlee.gradle.chungus

import org.gradle.api.Project
import org.gradle.api.Plugin
import java.io.File


class ChungusPlugin: Plugin<Project> {
    override fun apply(project: Project) {

        project.tasks.register("buildCache") {
            it.outputs.dir("${project.buildDir}/chungus")

            it.doFirst {
                File(project.buildDir, "chungus/openapi").mkdir()
            }
        }

        project.tasks.register("openApiFetch") {
            it.outputs.cacheIf { true }

            it.doFirst {
                val client = ApiRegistryClient()
                val json = client.fetchOpenApiSpecForService()
                File("${project.buildDir}/chungus/openapi", "test.json").writeText(json)
            }
        }
    }
}
