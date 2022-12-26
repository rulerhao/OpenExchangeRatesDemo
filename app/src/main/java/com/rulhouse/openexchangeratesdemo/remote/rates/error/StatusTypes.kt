package com.rulhouse.openexchangeratesdemo.remote.rates.error

enum class StatusTypes {
    NotFound,
    MissingAppId,
    InvalidAppId,
    NotAllowed,
    AccessRestricted,
    InvalidBase,
    Success,
    NetworkError,
    UnknownError,
    NoResponse;

    companion object {
        fun mapToStatus(code: Int, message: String?): StatusTypes {
            when (code) {
                429 -> return NotAllowed
                404 -> return NotFound
                403 -> return AccessRestricted
                401 -> {
                    when (message) {
                        "invalid_app_id" -> return InvalidAppId
                        "missing_app_id" -> return MissingAppId
                    }
                }
                400 -> return InvalidBase
                200 -> return Success
            }

            return UnknownError
        }
    }
}