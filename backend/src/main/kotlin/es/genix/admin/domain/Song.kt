package es.genix.admin.domain

import com.fasterxml.jackson.annotation.*
import es.genix.admin.models.Guild
import java.io.Serializable


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
    "source",
    "member",
    "user",
    "id",
    "name",
    "isLive",
    "duration",
    "formattedDuration",
    "url",
    "streamURL",
    "thumbnail",
    "views",
    "likes",
    "dislikes",
    "uploader",
    "age_restricted",
    "chapters",
    "reposts",
    "currentTime"
)
class Song : Serializable {
    @JsonProperty("source")
    var source: String? = null

    @JsonProperty("member")
    var member: Member? = null

    @JsonProperty("user")
    var user: DistubeUser? = null

    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("isLive")
    var isLive: Boolean? = null

    @JsonProperty("duration")
    var duration: Int? = null

    @JsonProperty("formattedDuration")
    var formattedDuration: String? = null

    @JsonProperty("url")
    var url: String? = null

    @JsonProperty("streamURL")
    var streamURL: String? = null

    @JsonProperty("thumbnail")
    var thumbnail: String? = null

    @JsonProperty("views")
    var views: Int? = null

    @JsonProperty("likes")
    var likes: Int? = null

    @JsonProperty("dislikes")
    var dislikes: Int? = null

    @JsonProperty("uploader")
    var uploader: Uploader? = null

    @JsonProperty("age_restricted")
    var ageRestricted: Boolean? = null

    @JsonProperty("chapters")
    var chapters: List<Any>? = null

    @JsonProperty("reposts")
    var reposts: Int? = null

    @JsonProperty("currentTime")
    var currentTime: Int? = null

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
        private const val serialVersionUID = 1018042079674907313L
    }
}
