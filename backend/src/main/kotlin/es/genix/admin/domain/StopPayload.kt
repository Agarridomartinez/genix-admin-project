package es.genix.admin.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.io.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("id", "guildId", "status")
data class StopPayload(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("guildId")
    val guildId: String,
    @JsonProperty("status")
    val status: String
) : Serializable
