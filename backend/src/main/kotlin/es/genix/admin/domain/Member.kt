package es.genix.admin.domain

import com.fasterxml.jackson.annotation.*
import es.genix.admin.models.Guild
import java.io.Serializable


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
    "guildId",
    "guild",
    "joinedTimestamp",
    "premiumSinceTimestamp",
    "nickname",
    "pending",
    "communicationDisabledUntilTimestamp",
    "userId",
    "avatar",
    "displayName",
    "roles",
    "avatarURL",
    "displayAvatarURL"
)
class Member : Serializable {
    @JsonProperty("guildId")
    var guildId: String? = null

    @JsonProperty("guild")
    var guild: Guild? = null

    @JsonProperty("joinedTimestamp")
    var joinedTimestamp: Long? = null

    @JsonProperty("premiumSinceTimestamp")
    var premiumSinceTimestamp: Long? = null

    @JsonProperty("nickname")
    var nickname: Any? = null

    @JsonProperty("pending")
    var pending: Boolean? = null

    @JsonProperty("communicationDisabledUntilTimestamp")
    var communicationDisabledUntilTimestamp: Any? = null

    @JsonProperty("userId")
    var userId: String? = null

    @JsonProperty("avatar")
    var avatar: String? = null

    @JsonProperty("displayName")
    var displayName: String? = null

    @JsonProperty("roles")
    var roles: List<String>? = null

    @JsonProperty("avatarURL")
    var avatarURL: String? = null

    @JsonProperty("displayAvatarURL")
    var displayAvatarURL: String? = null

    @JsonIgnore
    private val additionalProperties: MutableMap<String, Any> = HashMap()
    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        additionalProperties[name] = value
    }

    companion object {
        private const val serialVersionUID = -7232254464279483790L
    }
}
