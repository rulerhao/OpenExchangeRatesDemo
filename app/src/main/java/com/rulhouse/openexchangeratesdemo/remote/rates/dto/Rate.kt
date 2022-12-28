package com.rulhouse.openexchangeratesdemo.remote.rates.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Rate(
    @PrimaryKey override val currency: String,
    override val value: Double
): CurrencyRate, Parcelable {
    constructor() : this(
        currency = "",
        value = 0.0,
    )
}
