package hu.inf.unideb.dailychallenges.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

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
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private class CategoriesCallback : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { db ->
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(0,"Education"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(1,"Recreational"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(2,"Music"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(3,"Diy"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(4,"Social"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(5,"Cooking"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(6,"Relaxation"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(7,"Charity"))
                    db.dailyChallengesDAO.category_insert(DailyChallengesCategories(8,"Busywork"))
                }
        }
    }
}