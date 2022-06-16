package es.genix.admin.domain

import com.fasterxml.jackson.annotation.*
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
    "id",
    "bot",
    "system",
    "flags",
    "username",
    "discriminator",
    "avatar",
    "createdTimestamp",
    "defaultAvatarURL",
    "tag",
    "avatarURL",
    "displayAvatarURL"
)

class DistubeUser : Serializable {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("bot")
    var bot: Boolean? = null

    @JsonProperty("system")
    var system: Boolean? = null

    @JsonProperty("flags")
    var flags: Int? = null

    @JsonProperty("username")
    var username: String? = null

    @JsonProperty("discriminator")
    var discriminator: String? = null

    @JsonProperty("avatar")
    var avatar: String? = null

    @JsonProperty("createdTimestamp")
    var createdTimestamp: Long? = null

    @JsonProperty("defaultAvatarURL")
    var defaultAvatarURL: String? = null

    @JsonProperty("tag")
    var tag: String? = null

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
        private const val serialVersionUID = 128634507306182470L
    }
}
