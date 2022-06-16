package es.genix.admin.domain

import com.fasterxml.jackson.annotation.*
import java.io.Serializable


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("source", "songs", "name", "member", "user", "url", "thumbnail", "duration", "formattedDuration")
class Playlist : Serializable {
    @JsonProperty("source")
    var source: String? = null

    @JsonProperty("songs")
    var songs: List<Song>? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("member")
    var member: Member? = null

    @JsonProperty("user")
    var user: DistubeUser? = null

    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("thumbnail")
    var thumbnail: String? = null

    @JsonProperty("duration")
    var duration: Int? = null

    @JsonProperty("formattedDuration")
    var formattedDuration: String? = null

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
        private const val serialVersionUID = -2718740445946828516L
    }
}
