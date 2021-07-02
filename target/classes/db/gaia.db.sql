create user gaia with password 'gaia' ;

ALTER USER gaia WITH PASSWORD 'gaia';

create database gaia_dev with encoding='utf8' ;
create database gaia_prod with encoding='utf8' ;
create database gaia_test with encoding='utf8' ;

grant all privileges on database gaia_dev to gaia ;
grant all privileges on database gaia_prod to gaia;
grant all privileges on database gaia_test to gaia;

\connect gaia_dev;
create schema extensions;
create extension hstore schema extensions;
ALTER DATABASE gaia_dev SET search_path to "$user",public,extensions;
alter database gaia_dev owner to gaia;
alter schema public owner to gaia;
alter schema extensions owner to gaia;
GRANT USAGE ON SCHEMA public to gaia;

\connect gaia_prod;
create schema extensions;
create extension hstore schema extensions;
ALTER DATABASE gaia_prod SET search_path to "$user",public,extensions;
alter database gaia_prod owner to gaia;
alter schema public owner to gaia;
alter schema extensions owner to gaia;
GRANT USAGE ON SCHEMA public to gaia;


\connect gaia_test;
create schema extensions;
create extension hstore schema extensions;
ALTER DATABASE gaia_test SET search_path to "$user",public,extensions;
alter database gaia_test owner to gaia;
alter schema public owner to gaia;
alter schema extensions owner to gaia;
GRANT USAGE ON SCHEMA public to gaia;
