package hu.inf.unideb.dailychallenges.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DailyChallengesDAO {

    //Main challenges DAO
    @Insert
    fun insert(challenge : DailyChallenges)

    @Update
    fun update(challenge : DailyChallenges)

    @Query("SELECT EXISTS(SELECT * FROM dailychallenges_table WHERE challenge_activityText = :activityText)" )
    fun checkExists(activityText : String): Boolean

    @Query("SELECT category_image FROM dailychallenges_category_table WHERE category_name = :categoryName" )
    fun getImageSrc(categoryName : String): Int

    @Query("SELECT * FROM dailychallenges_table WHERE challengeID = :key")
    fun getChallengeId(key: Long): LiveData<DailyChallenges>

    @Query("SELECT * FROM dailychallenges_table WHERE challenge_done != 1 ORDER BY challengeID DESC ")
    fun getAllChallenges(): LiveData<List<DailyChallenges>>

    @Query("SELECT * FROM dailychallenges_table WHERE challenge_done == 1 ORDER BY challengeID DESC ")
    fun getAllCompletedChallenges(): LiveData<List<DailyChallenges>>

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