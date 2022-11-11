package hu.inf.unideb.dailychallenges.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dailychallenges_table")
data class DailyChallenges(
    @PrimaryKey(autoGenerate = true)
    var challengeID: Long = 0L,

    @ColumnInfo(name = "challenge_type")
    val challengeType: String?,

    @ColumnInfo(name = "challenge_activityText")
    val challengeActivityText: String?,

    @ColumnInfo(name = "challenge_participants")
    val challengeParticipants: String?,

    @ColumnInfo(name = "challenge_price")
    val challengePrice: Int?,

    @ColumnInfo(name = "challenge_API_keyID")
    val challengeKey: Int?,

    @ColumnInfo(name = "challenge_done")
    val challengeDone: Boolean = false
)
