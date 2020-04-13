package dev.mrlee.gradle.chungus.task

import dev.mrlee.gradle.chungus.config.ChungusConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectories
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

open class InitCacheTask @Inject constructor(private val config: ChungusConfig) : DefaultTask() {

    @OutputDirectories
    val outputs = listOf(
        project.buildDir.resolve(config.specDir),
        project.buildDir.resolve(config.clientDir)
    )

    @TaskAction
    fun doIt() {
        project.buildDir.resolve(config.specDir)
        project.buildDir.resolve(config.clientDir)
    }
}