package hu.inf.unideb.dailychallenges.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DailyChallengesDAO {

    //Main challenges DAO
    @Insert
    fun insert(challenge : DailyChallenges)

    @Update
    suspend fun update(challenge : DailyChallenges)

    @Query("SELECT * FROM dailychallenges_table WHERE challengeID = :key")
    suspend fun get(key: Long): DailyChallenges

    @Query("SELECT * FROM dailychallenges_table ORDER BY challengeID DESC")
    fun getAllChallenges(): LiveData<List<DailyChallenges>>

    @Query("DELETE FROM dailychallenges_table")
    suspend fun clear()

    //New challenges DAO
    @Insert
    fun category_insert(categories: DailyChallengesCategories)

    @Query("SELECT COUNT(*) FROM dailychallenges_category_table")
    fun category_count() : Int

    @Query("SELECT * FROM dailychallenges_category_table ORDER BY categoryID ASC LIMIT 1")
    suspend fun getCategories(): DailyChallengesCategories?

    @Query("SELECT * FROM dailychallenges_category_table ORDER BY categoryID ASC")
    fun getAllCategories(): LiveData<List<DailyChallengesCategories>>
}