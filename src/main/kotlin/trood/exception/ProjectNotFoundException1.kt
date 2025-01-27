package trood.exception

import org.springframework.http.HttpStatus

class ProjectNotFoundException1(projectId: Int) :BaseException(
        HttpStatus.NOT_FOUND,
        ApiError(
                errorCode = "project.not.found",
                description = "Project not found with id=$projectId"
        )
)