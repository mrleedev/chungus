package dev.mrlee.gradle.chungus.task

import dev.mrlee.gradle.chungus.api.ApiRegistryClient
import dev.mrlee.gradle.chungus.config.ChungusConfig
import dev.mrlee.gradle.chungus.config.ServiceConfig
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

@CacheableTask
open class FetchSpecsTask @Inject constructor(private val config: ChungusConfig) : DefaultTask() {

    private val apiClient = ApiRegistryClient()

    @TaskAction
    fun doIt() {
        runBlocking {
            config.services.all { service ->
                launch {
                    val json = apiClient.fetchOpenApiSpecForService(service.url)
                    specFile(service).writeText(json)
                }
            }
        }
    }

    private fun specFile(service: ServiceConfig) =
        project.buildDir.resolve("${config.specDir}/${service.name}.${service.format}")
}