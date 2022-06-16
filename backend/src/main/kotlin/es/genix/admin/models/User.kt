package es.genix.admin.models

import com.google.gson.annotations.SerializedName
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.oauth2.core.user.OAuth2User

@Document(collection = "users")
class User(
    var principal: OAuth2User? = null,
    var id: String? = null,
    var username: String? = null,
    var avatar: String? = null,
    var discriminator: String? = null,
    var bot: Boolean? = null,
    var system: Boolean? = null,
    @SerializedName("mfa_enabled") var mfaEnabled: Boolean? = null,
    var locale: String? = null,
    var verified: Boolean? = null,
    var email: String? = null,
    var flags: Int? = null,
    @SerializedName("premium_type")
    var premiumType: Int? = null,
) {
    fun getFullUsername(): String {
        return "$username#$discriminator"
    }

    fun getAvatarUrl(): String {
        return "https://cdn.discordapp.com/embed/avatars/${id}/${avatar}.png"
    }

    constructor(user: OAuth2User) : this() {
        principal = user
        id = user.attributes["id"] as String?
        username = user.attributes["username"] as String?
        avatar = user.attributes["avatar"] as String?
        discriminator = user.attributes["discriminator"] as String?
        bot = user.attributes["bot"] as Boolean? ?: false
        system = user.attributes["system"] as Boolean? ?: false
        mfaEnabled = user.attributes["mfa_enabled"] as Boolean?
        locale = user.attributes["locale"] as String?
        verified = user.attributes["verified"] as Boolean?
        email = user.attributes["email"] as String?
        flags = user.attributes["flags"] as Int?
        premiumType = user.attributes["premium_type"] as Int?
    }
}

