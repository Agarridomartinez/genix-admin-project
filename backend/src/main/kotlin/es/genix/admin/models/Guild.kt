package es.genix.admin.models

import com.fasterxml.jackson.annotation.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "guilds")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("id", "name", "icon", "features")
class Guild {
    @Id
    @JsonProperty("id")
    var id: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("icon")
    var icon: String? = null

    @JsonProperty("features")
    var features: List<String>? = null
}
