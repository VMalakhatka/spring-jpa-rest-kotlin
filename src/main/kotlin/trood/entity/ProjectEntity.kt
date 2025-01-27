package trood.entity

import jakarta.persistence.*
import jakarta.validation.constraints.*
import trood.service.Field
import java.time.LocalDate

@Entity
@Table(name = "projects")
class ProjectEntity (
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int =0,
        @Column(nullable = false, length = 50)
    @field:NotBlank(message = "A name can't be empty") // Checks that the string is not empty
    @field:Size(max = 50, message = "Name cannot exceed 50 characters")
    var name: String,
        @field:Min(value = 0, message = "Experience can't be negative")
    @field:Max(value = 150, message = "Experience cannot be more than 50")
    var experience : Int,
        @Column(nullable = false)
    @field:NotNull(message = "The date cannot be blank")
    @field:Future(message = "The date must be in the future")
    var deadline : LocalDate,
        @Enumerated(EnumType.STRING) // Storing an enumeration value as a string
    @Column(nullable = false)
    @NotNull(message = "The position for the vacancy is required")
    val field: Field,
        @field:Size(max = 2500, message = "Текст не должен превышать 2500 символов")
    @Column(nullable = false, length = 2500) // length specifies a restriction at the database level
    val description: String
)