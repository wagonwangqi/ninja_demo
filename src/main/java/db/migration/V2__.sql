CREATE TABLE homework
(
    homework_id BIGSERIAL NOT NULL PRIMARY KEY,
    name     character  varying(255),
    content     character varying(255),
    chapter_id  bigint    not null,
    created     TIMESTAMP WITHOUT TIME ZONE,
    modified    TIMESTAMP WITHOUT TIME ZONE
);
create table mem_home_sta
(
    id      bigserial not null primary key,
    mem_id  bigint    not null,
    hom_id  bigint    not null,
    is_Done boolean,
    is_Cro boolean,
    grade   int,
    feedback character varying(255)
);

