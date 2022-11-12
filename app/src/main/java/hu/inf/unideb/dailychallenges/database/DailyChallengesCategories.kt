package hu.inf.unideb.dailychallenges.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dailychallenges_category_table")
data class DailyChallengesCategories(
    @PrimaryKey(autoGenerate = true)
    var categoryID: Long = 0L,

    @ColumnInfo(name = "category_name")
    val categoryName: String?,

    @ColumnInfo(name = "category_image")
    val categoryImage: Int
)

