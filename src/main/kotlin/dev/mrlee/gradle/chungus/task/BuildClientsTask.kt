package dev.mrlee.gradle.chungus.task

import dev.mrlee.gradle.chungus.config.ChungusConfig
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject


open class BuildClientsTask @Inject constructor(private val config: ChungusConfig): DefaultTask() {

    @TaskAction
    fun doIt() {

    }
}