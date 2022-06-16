package es.genix.admin.repositories

import es.genix.admin.models.Guild
import es.genix.admin.models.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildRepository : MongoRepository<Guild, String> {
}
