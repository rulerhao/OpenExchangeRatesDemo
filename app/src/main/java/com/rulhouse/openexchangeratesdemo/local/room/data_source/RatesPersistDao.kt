package com.rulhouse.openexchangeratesdemo.local.room.data_source

import androidx.room.*
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate
import kotlinx.coroutines.flow.Flow

@Dao
interface RatesPersistDao {

    @Query("SELECT * FROM Rate")
    fun getRatesFlow(): Flow<List<Rate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(rates: List<Rate>)

}