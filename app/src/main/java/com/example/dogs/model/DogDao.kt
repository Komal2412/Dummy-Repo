package com.example.dogs.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {

    // vararg means argument variables and we can pass as many arguments of type dog breed
    // returns list of uuids
    // number of uuids in list is equal to number of args
    // suspend will make it all on a seperate thread
    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>


    @Query("SELECT * FROM dogbreed")
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int) : DogBreed

    @Query("DELETE FROM dogbreed")
    suspend fun deleteAllDogs()


}