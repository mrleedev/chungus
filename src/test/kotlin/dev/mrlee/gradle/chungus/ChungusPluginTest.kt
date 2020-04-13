package dev.mrlee.gradle.chungus

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class ChungusPluginTest {
    @Test fun `fetchOpenApiSpecs task is registered`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("dev.mrlee.gradle.chungus")

        assertNotNull(project.tasks.findByName("fetchOpenApiSpecs"))
    }

    @Test fun `generateOpenApiClients is registered`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("dev.mrlee.gradle.chungus")
        assertNotNull(project.tasks.findByName("generateOpenApiClients"))
    }

}
