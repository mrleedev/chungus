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
                create("initCache", InitCacheTask::class.java, config)
                create("fetchSpecs", FetchSpecsTask::class.java, config).dependsOn("initCache")
                create("buildClients", BuildClientsTask::class.java, config).dependsOn("fetchSpecs")
            }
        }
    }
}

@Suppress("unused")
internal fun Project.chungus(): ChungusConfig =
        extensions.getByName("chungus") as? ChungusConfig
                ?: throw  IllegalStateException("Chungus extension is not of correct type")
