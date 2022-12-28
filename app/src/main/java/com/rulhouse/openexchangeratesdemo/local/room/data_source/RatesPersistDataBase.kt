package com.rulhouse.openexchangeratesdemo.local.room.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rulhouse.openexchangeratesdemo.local.room.domain.util.Converters
import com.rulhouse.openexchangeratesdemo.remote.rates.dto.Rate

@Database(
    entities = [Rate::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RatesPersistDataBase : RoomDatabase() {

    abstract val ratesPersistDao: RatesPersistDao

    companion object {
        const val DATABASE_NAME = "rates_persist_db"
    }
}