CREATE DATABASE ascenthr
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
	
CREATE SCHEMA ascenthr;

ALTER SCHEMA ascenthr OWNER TO postgres;

CREATE TABLE ascenthr.queue_message (
	id SERIAL PRIMARY KEY,
    tenant_id text NOT NULL,
    employee_json text NOT NULL,
	created_date timestamp without time zone DEFAULT (to_char(CURRENT_TIMESTAMP, 'YYYY-MM-DD HH24:MI:SS'::text))::timestamp without time zone
);

ALTER TABLE ascenthr.queue_message OWNER to postgres;