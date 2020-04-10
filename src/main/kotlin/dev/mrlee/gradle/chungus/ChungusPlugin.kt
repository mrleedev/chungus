package dev.mrlee.gradle.chungus

import org.gradle.api.Project
import org.gradle.api.Plugin


class ChungusPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        // Register a task
        project.tasks.register("greeting") { task ->
            task.doLast {
                println("Hello from plugin 'chungus.greeting'")
            }
        }
    }
}
