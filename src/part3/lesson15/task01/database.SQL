create table "USER"
(
    id          serial  not null
        constraint """USER""_pk"
            primary key,
    name        varchar,
    birthday    date,
    "login_ID"  varchar not null,
    city        varchar,
    email       varchar,
    description varchar
);

alter table "USER"
    owner to postgres;

create unique index """USER""_""login_ID""_uindex"
    on "USER" ("login_ID");

create table "ROLE"
(
    id          serial  not null
        constraint """ROLE""_pk"
            primary key,
    name        varchar not null,
    description varchar
);

alter table "ROLE"
    owner to postgres;

create unique index """ROLE""_name_uindex"
    on "ROLE" (name);

create table "USER_ROLE"
(
    id      serial  not null
        constraint """USER_ROLE""_pk"
            primary key,
    user_id integer not null
        constraint """USER_ROLE""_USER_id_fk"
            references "USER"
            on update restrict on delete restrict,
    role_id integer not null
        constraint """USER_ROLE""_ROLE_id_fk"
            references "ROLE"
            on update restrict on delete restrict
);

alter table "USER_ROLE"
    owner to postgres;

INSERT INTO public."ROLE" (id, name, description)
VALUES (1, 'Administration', null);
INSERT INTO public."ROLE" (id, name, description)
VALUES (2, 'Clients', null);
INSERT INTO public."ROLE" (id, name, description)
VALUES (3, 'Billing', null);