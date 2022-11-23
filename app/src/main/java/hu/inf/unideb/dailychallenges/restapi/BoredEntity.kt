package hu.inf.unideb.dailychallenges.restapi

data class BoredEntity(
    val error: String?,
    val activity: String?,
    val accessibility: Double?,
    val type: String?,
    val participants: Int?,
    val price: Double?,
    val link: String?,
    val key: Int?
)
