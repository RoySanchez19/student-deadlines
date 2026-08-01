package dev.roysanchez.deadlines.deadline;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeadlineRepository extends JpaRepository<Deadline, UUID> {

    /**
     * Lo que se viene encima, en orden. Spring Data deriva la consulta del
     * nombre del metodo, y el indice de `due_date` la resuelve sin recorrer
     * la tabla entera.
     */
    List<Deadline> findByDueDateGreaterThanEqualOrderByDueDateAsc(LocalDate from);
}
