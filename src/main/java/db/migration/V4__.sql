drop table if exists bigCategory,smallCategory;
create table bigCategory(
    id bigserial not null primary key ,
    big_categoryName character varying(255)
);
create table smallCategory(
    id bigserial not null primary key ,
    small_categoryName character varying(255),
    big_categoryId bigint references bigCategory(id)
)