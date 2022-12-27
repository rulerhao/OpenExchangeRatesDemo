package com.rulhouse.openexchangeratesdemo.remote.rates.error

enum class StatusTypes {
    NotFound,
    MissingAppId,
    InvalidAppId,
    NotAllowed,
    AccessRestricted,
    InvalidBase,
    Success,
    UnSuccess,
    NetworkError,
    UnknownError,
    NoResponse,
    NullBody,
    BaseNotAvailable;

    companion object {
        fun mapToStatus(code: Int, message: String?): StatusTypes {
            println(code)
            println(message)
            when (code) {
                429 -> return NotAllowed
                404 -> return NotFound
                403 -> // It's because the actually error status not equal to the documents.
                    when (message) {
                        "invalid_app_id" -> return InvalidAppId
                        "missing_app_id" -> return MissingAppId
                        "not_allowed" -> return NotAllowed
                        else -> return AccessRestricted
                    }
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