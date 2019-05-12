drop table if exists meta;
drop table if exists context;

create table context
(
    id               uuid    not null
        constraint context_pk
            primary key,
    app_start_time   bigint  not null,
    debug_mode       boolean not null,
    java_version     varchar not null,
    jvm_name         varchar not null,
    jvm_vendor       varchar not null,
    jvm_version      varchar not null,
    os_name          varchar not null,
    os_version       varchar not null,
    os_arch          varchar not null,
    cpu              varchar not null,
    available_cores  varchar not null,
    ram              varchar not null,
    swap             varchar not null,
    available_memory varchar not null,
    jvm_params       varchar not null,
    session_notes    varchar not null
);

create table meta
(
    id                uuid    not null
        constraint meta_pk
            primary key,
    context_id        uuid    not null
        constraint meta_context_id_fk
            references context (id)
            on update cascade on delete cascade,
    parent_id         varchar,
    class_type        varchar not null,
    method_name       varchar not null,
    argument_types    varchar not null,
    return_type       varchar not null,
    method_start_time varchar not null,
    method_run_time   varchar not null,
    thread_name       varchar not null,
    thread_run_time   varchar not null,
    threw_exception   boolean not null
);
