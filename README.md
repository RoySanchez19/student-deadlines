# student-deadlines

> **AI-assisted learning project.** Roy confirmed that this project was developed with AI assistance. The reviewed source is an early backend scaffold: one entity, repository query, and migration. No API, authentication, document parser, or verified public service is implemented. A guided explanation and a small understood change are the next learning step. Source review: October 30, 2026; the service was not started in this review.

International students in the US carry a set of dates that do not forgive being
late. The I-20 program end date. The window to file for OPT. Whether the travel
signature is still valid before leaving the country. Miss one and the
consequence is not a late fee — it is status.

There is no single place where those dates live. They are scattered across a PDF
in an email from two years ago, a screenshot in a phone gallery, and whatever
somebody remembers a friend saying. So students end up asking each other, or
asking their advisor the same question a hundred other students already asked.

The project goal is a tool for keeping student-entered dates in one place. The current scaffold does not yet provide that complete workflow.

## What it is not

**This is not immigration advice, and it never will be.** It organizes dates the
student enters. It does not interpret rules, it does not tell anyone what they
are allowed to do, and it is not a substitute for a DSO or an attorney. Every
answer it gives ends at "confirm this with your DSO."

That boundary is a design constraint, not a disclaimer bolted on at the end.

## A deliberate decision about data

An I-20 contains a SEVIS ID, a date of birth, and a home address. Holding that
for other people is a real responsibility, not a footnote.

A possible future design is to **read a document, extract dates, and keep only the
dates**. Document parsing and that retention workflow are not implemented or
verified in the current source. Use synthetic data while learning.

## Status

Week 1. What exists right now:

- Spring Boot 4.1 on Java 21
- PostgreSQL 18 in Docker Compose
- Schema owned by Flyway; Hibernate is set to `validate` and refuses to start if
  the entities and the migrations disagree
- One entity (`Deadline`) and one migration

There is no API yet, no authentication, and no document parsing. Those are the
next increments, and each one gets written up as it lands.

## Running it

Requires Java 21 and Docker.

```bash
docker compose up -d
./mvnw spring-boot:run
```

The app starts on port 8080. Flyway applies migrations on boot.

To reset the database completely:

```bash
docker compose down -v && docker compose up -d
```

## Layout

```
src/main/java/dev/roysanchez/deadlines/
  deadline/
    Deadline.java            the entity
    DeadlineKind.java        what kind of date this is
    DeadlineRepository.java  data access
src/main/resources/
  db/migration/              Flyway migrations, applied in order
  application.properties
compose.yaml                 PostgreSQL for local development
```

## Notes on a few choices

**Flyway owns the schema, not Hibernate.** `ddl-auto=validate` means the
application will not start if an entity does not match what the migrations
built. The alternative, `update`, lets Hibernate quietly alter the database, and
then the real schema is not written down anywhere you can review.

**UUID primary keys instead of auto-incrementing integers.** Sequential ids leak
how many records exist and in what order they were created. For an app about
immigration dates, that is information worth not giving away.

**Enums are stored as text, not as ordinals.** Inserting a new value into the
middle of the enum would silently change the meaning of every existing row.
