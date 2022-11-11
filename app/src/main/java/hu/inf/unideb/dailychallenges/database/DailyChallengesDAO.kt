package hu.inf.unideb.dailychallenges.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DailyChallengesDAO {

    @Insert
    suspend fun insert(challenge : DailyChallenges)

    @Update
    suspend fun update(challenge : DailyChallenges)

    @Query("SELECT * FROM dailychallenges_table WHERE challengeID = :key")
    suspend fun get(key: Long): DailyChallenges

    @Query("SELECT * FROM dailychallenges_table ORDER BY challengeID DESC")
    fun getAllChallenges(): LiveData<List<DailyChallenges>>

    @Query("DELETE FROM dailychallenges_table")
    suspend fun clear()

    @Insert
    fun category_insert(categories: DailyChallengesCategories)
}