package edu.miu.quizapp.db

import androidx.room.*

@Dao
interface QuizRepo {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOne(quiz: Quiz)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMany(vararg quiz: Quiz)

    @Query("SELECT * FROM quiz ORDER BY id")
    suspend fun getAll(): List<Quiz>

    @Update
    suspend fun update(quiz: Quiz)

    @Delete
    suspend fun delete(quiz: Quiz)

    @Query("DELETE FROM quiz WHERE 1=1")
    suspend fun deleteAll()
}
