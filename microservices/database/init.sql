-- Table: public.projects

-- DROP TABLE public.projects;

CREATE TABLE public.projects
(
    project_id   bigint NOT NULL,
    created_time timestamp without time zone,
    projectName  character varying(255) COLLATE pg_catalog."default",
    projectPath  character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT projects_pkey PRIMARY KEY (project_id)
)
    TABLESPACE pg_default;

ALTER TABLE public.projects
    OWNER to postgres;

-- Table: public.commands

-- DROP TABLE public.commands;

CREATE TABLE public.commands
(
    process_id   bigint NOT NULL,
    command      character varying(255) COLLATE pg_catalog."default",
    created_time timestamp without time zone,
    project_id   bigint,
    stdout       boolean,
    CONSTRAINT commands_pkey PRIMARY KEY (process_id),
    CONSTRAINT projects_pkey FOREIGN KEY (project_id)
        REFERENCES public.projects (project_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
    TABLESPACE pg_default;

ALTER TABLE public.commands
    OWNER to postgres;
