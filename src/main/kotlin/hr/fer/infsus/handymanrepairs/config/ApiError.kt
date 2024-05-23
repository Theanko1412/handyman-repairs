package hr.fer.infsus.handymanrepairs.config

import java.time.OffsetDateTime

data class ApiError(
    val statusCode: Int,
    val status: String,
    val message: String,
    val path: String,
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
)
