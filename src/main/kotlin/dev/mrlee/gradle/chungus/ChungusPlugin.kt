package dev.mrlee.gradle.chungus

import dev.mrlee.gradle.chungus.config.ChungusConfig
import dev.mrlee.gradle.chungus.task.BuildClientsTask
import dev.mrlee.gradle.chungus.task.FetchSpecsTask
import dev.mrlee.gradle.chungus.task.InitCacheTask
import org.gradle.api.Project
import org.gradle.api.Plugin
import java.lang.IllegalStateException


@Suppress("unused")
class ChungusPlugin: Plugin<Project> {
    companion object {
        const val pluginGroup = "chungus"
    }

    override fun apply(project: Project) {
        project.group = pluginGroup

        project.run {
            val config = project.extensions.create(pluginGroup, ChungusConfig::class.java)

            tasks.apply {
                register("initCache", InitCacheTask::class.java, config)

                register("fetchSpecs", FetchSpecsTask::class.java, config).configure {
                    it.dependsOn("initCache")
                }

                register("buildClients", BuildClientsTask::class.java, config).configure {
                    it.dependsOn("fetchSpecs")
                }
            }
        }
    }
}

@Suppress("unused")
internal fun Project.chungus(): ChungusConfig =
        extensions.getByName("chungus") as? ChungusConfig
                ?: throw  IllegalStateException("Chungus extension is not of correct type")
