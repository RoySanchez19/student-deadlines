create table deadlines (
    id         uuid         primary key,
    title      varchar(120) not null,
    kind       varchar(40)  not null,
    due_date   date         not null,
    notes      text,
    created_at timestamptz  not null default now()
);

-- La consulta central de esta app es "que se me viene encima", siempre
-- ordenada por fecha. Sin este indice, cada consulta recorre la tabla entera.
create index idx_deadlines_due_date on deadlines (due_date);
