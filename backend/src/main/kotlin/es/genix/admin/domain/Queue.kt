package es.genix.admin.domain

import com.fasterxml.jackson.annotation.*
import java.io.Serializable


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("id", "guildId", "songs")
class Queue : Serializable {
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("guildId")
    var guildId: String? = null

    @JsonProperty("songs")
    var songs: List<Song>? = null

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
        private const val serialVersionUID = -5012403574257189200L
    }
}
