package hu.inf.unideb.dailychallenges.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import hu.inf.unideb.dailychallenges.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


@Database(entities = [DailyChallenges::class,DailyChallengesCategories::class], version = 1, exportSchema = false)
abstract class DailyChallengesDatabase : RoomDatabase() {

    abstract val dailyChallengesDAO: DailyChallengesDAO

    companion object {
        @Volatile
        private var INSTANCE: DailyChallengesDatabase? = null

        fun getInstance(context: Context): DailyChallengesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DailyChallengesDatabase::class.java,
                        "daily_challenges_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(CategoriesCallback())
                        .allowMainThreadQueries()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private class CategoriesCallback : RoomDatabase.Callback(){

        private val applicationScope = CoroutineScope(SupervisorJob())

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            applicationScope.launch(Dispatchers.IO){
                INSTANCE?.let{ database ->
                    categoryInsertToDatabase(database.dailyChallengesDAO)
                }
            }
        }

        override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
            super.onDestructiveMigration(db)

            applicationScope.launch(Dispatchers.IO){
                INSTANCE?.let{ database ->
                    categoryInsertToDatabase(database.dailyChallengesDAO)
                }
            }
        }
    }
}

private fun categoryInsertToDatabase(dailyChallengesDAO: DailyChallengesDAO){
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(1,"Recreational",R.drawable.ic_recreational))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(2,"Education",R.drawable.ic_education))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(3,"Music",R.drawable.ic_music))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(4,"Diy",R.drawable.ic_diy))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(5,"Social",R.drawable.ic_social))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(6,"Cooking",R.drawable.ic_cooking))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(7,"Relaxation",R.drawable.ic_relaxation))
    dailyChallengesDAO.categoryInsert(DailyChallengesCategories(8,"Charity",R.drawable.ic_charity))
}

