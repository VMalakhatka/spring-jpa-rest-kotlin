package trood.controller

import trood.dto.ProjectDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import trood.service.ProjectService

@RestController
@RequestMapping("/projects")
class ProjectController(
        private val projectService: ProjectService,
) {
    @GetMapping("/")
    fun getAll(): List<ProjectDto> = projectService.getAll()


    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): ProjectDto =
            projectService.getById(id)

    @PostMapping
    fun create(@RequestBody dto: ProjectDto): Int = projectService.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: ProjectDto) {
        projectService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        projectService.delete(id)
    }

}