package edu.miu.quizapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Quiz::class],
    version = 1,
    exportSchema = true
)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun getQuizDao(): QuizRepo

    companion object {

        @Volatile
        private var instance: QuizDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        // Function to build database
        private fun buildDatabase(context: Context): QuizDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuizDatabase::class.java,
                "quizdb"
            ).build()
        }
    }

}