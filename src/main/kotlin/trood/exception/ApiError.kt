package trood.exception

data class ApiError(
    val errorCode: String, // project.not.found
    val description: String,
)
