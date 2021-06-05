create table "public"."client_eligibility"
(
    id              bigserial constraint firstkey primary key,
    client_document varchar(20) not null,
    allow           boolean not null,
    reason          varchar(100) not null,
    registry_date   timestamp not null,
    last_update     timestamp not null
);