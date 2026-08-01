package dev.roysanchez.deadlines.deadline;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Una fecha que un estudiante no se puede permitir olvidar.
 *
 * <p>Deliberadamente no guarda el documento de donde salio la fecha. Un I-20
 * contiene SEVIS ID, fecha de nacimiento y direccion; aqui solo vive la fecha.
 */
@Entity
@Table(name = "deadlines")
public class Deadline {

    /**
     * UUID en lugar de un entero autoincremental: un id secuencial filtra
     * cuantos registros hay y en que orden se crearon. En una app sobre fechas
     * migratorias eso es informacion que no hace falta regalar.
     */
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private DeadlineKind kind;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(columnDefinition = "text")
    private String notes;

    /** Lo pone la base de datos con `default now()`, no la aplicacion. */
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private Instant createdAt;

    /** Requerido por JPA. No usar desde el codigo de la aplicacion. */
    protected Deadline() {
    }

    public Deadline(String title, DeadlineKind kind, LocalDate dueDate, String notes) {
        this.title = title;
        this.kind = kind;
        this.dueDate = dueDate;
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public DeadlineKind getKind() {
        return kind;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
