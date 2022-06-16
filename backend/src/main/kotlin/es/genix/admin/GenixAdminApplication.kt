package es.genix.admin

import es.genix.admin.configs.DiscordProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableConfigurationProperties(DiscordProperties::class)
class GenixAdminApplication

fun main(args: Array<String>) {
	runApplication<GenixAdminApplication>(*args)
}
