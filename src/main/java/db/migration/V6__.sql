create table chapter
(
    id          BIGSERIAL not null primary key,
    chapterName character varying(255),
    course_id   bigint references course (cou_id),
    created    TIMESTAMP WITHOUT TIME ZONE,
    modified   TIMESTAMP WITHOUT TIME ZONE
);
create table note(
    id bigserial not null primary key ,
    noteName character varying(255),
    course_id   bigint references course (cou_id),
    created    TIMESTAMP WITHOUT TIME ZONE
);
CREATE TABLE video
(
    id         BIGSERIAL              NOT NULL PRIMARY KEY,
    video_path character varying(255) not null,
    section_id bigint                 not null,
    -- common columns
    created    TIMESTAMP WITHOUT TIME ZONE,
    modified   TIMESTAMP WITHOUT TIME ZONE
);
create table section
(
    id          BIGSERIAL not null primary key,
    sectionName character varying(255),
    chapter_id   bigint references chapter (id),
    created    TIMESTAMP WITHOUT TIME ZONE,
    modified   TIMESTAMP WITHOUT TIME ZONE
);