package dev.mrlee.gradle.chungus

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull

class ChungusPluginTest {
    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("dev.mrlee.gradle.chungus")

        // Verify the result
        assertNotNull(project.tasks.findByName("greeting"))
    }
}
