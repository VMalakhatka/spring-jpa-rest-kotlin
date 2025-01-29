package trood.exception

import org.springframework.http.HttpStatus

class VacancyNotFoundExeption(vacanyId: Int): BaseException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "project.not.found",
        description = "Project not found with id=$vacanyId"
    )
) {
}