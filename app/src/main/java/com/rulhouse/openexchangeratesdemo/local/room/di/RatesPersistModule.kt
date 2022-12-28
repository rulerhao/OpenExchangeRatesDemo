package com.rulhouse.openexchangeratesdemo.local.room.di

import android.app.Application
import androidx.room.Room
import com.rulhouse.openexchangeratesdemo.local.room.impl.RatesPersistImpl
import com.rulhouse.openexchangeratesdemo.local.room.domain.repository.RatesPersistRepository
import com.rulhouse.openexchangeratesdemo.local.room.domain.use_case.GetRatesPersistFlow
import com.rulhouse.openexchangeratesdemo.local.room.domain.use_case.InsertRatesPersist
import com.rulhouse.openexchangeratesdemo.local.room.domain.use_case.RatesPersistUseCases
import com.rulhouse.openexchangeratesdemo.local.room.data_source.RatesPersistDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RatesPersistModule {

    @Provides
    @Singleton
    fun provideRatesPersistDatabase(app: Application): RatesPersistDataBase {
        return Room.databaseBuilder(
            app,
            RatesPersistDataBase::class.java,
            RatesPersistDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRatesPersistRepository(db: RatesPersistDataBase): RatesPersistRepository {
        return RatesPersistImpl(db.ratesPersistDao)
    }

    @Provides
    @Singleton
    fun provideRatesPersistUseCases(repository: RatesPersistRepository): RatesPersistUseCases {
        return RatesPersistUseCases(
            getRatesPersistFlow = GetRatesPersistFlow(repository),
            insertRatesPersist = InsertRatesPersist(repository)
        )
    }

}