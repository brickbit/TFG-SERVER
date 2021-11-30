create table if not exists departments (
    id                      VARCHAR(60)                 DEFAULT RANDOM_UUID() PRIMARY KEY,
    name                    VARCHAR(200)                NOT NULL
);

create table if not exists teachers (
    id                          VARCHAR(60)                 DEFAULT RANDOM_UUID() PRIMARY KEY,
    name                        VARCHAR(200)                NOT NULL,
    id_department               VARCHAR(60)                 NOT NULL,
    CONSTRAINT teachers_ibfk_1 FOREIGN KEY (id_department) REFERENCES departments (id) ON DELETE CASCADE
);

create table if not exists classrooms (
    id                      VARCHAR(60)                 DEFAULT RANDOM_UUID() PRIMARY KEY,
    name                    VARCHAR(200)                NOT NULL,
    pavilion                VARCHAR(300)                NOT NULL,
    capacity                INTEGER                     NOT NULL
);

create table if not exists degrees (
    id                      VARCHAR(60)                 DEFAULT RANDOM_UUID() PRIMARY KEY,
    name                    VARCHAR(200)                NOT NULL,
    num_semesters           INTEGER                     NOT NULL
);

create table if not exists subjects (
    id                      VARCHAR(60)                 DEFAULT RANDOM_UUID() PRIMARY KEY,
    name                    VARCHAR(200)                NOT NULL,
    semester                INTEGER                     NOT NULL,
    laboratory              BOOLEAN                     NOT NULL DEFAULT FALSE,
    conference              BOOLEAN                     NOT NULL DEFAULT FALSE,
    time_subject            INTEGER                     NOT NULL,
    id_classroom            VARCHAR(60)                 NOT NULL,
    id_department           VARCHAR(60)                 NOT NULL,
    id_degree               VARCHAR(60)                 NOT NULL,
    CONSTRAINT subjects_ibfk_1 FOREIGN KEY (id_classroom) REFERENCES classrooms (id) ON DELETE CASCADE,
    CONSTRAINT subjects_ibfk_2 FOREIGN KEY (id_department) REFERENCES departments (id) ON DELETE CASCADE,
    CONSTRAINT subjects_ibfk_3 FOREIGN KEY (id_degree) REFERENCES degrees (id) ON DELETE CASCADE
);

create table if not exists schedules (
    id                      VARCHAR(60)                 DEFAULT RANDOM_UUID() PRIMARY KEY,
    start_hour              VARCHAR(5)                  NOT NULL,
    end_hour                VARCHAR(5)                  NOT NULL,
    id_subject            VARCHAR(60)                 NOT NULL,
    CONSTRAINT schedules_ibfk_1 FOREIGN KEY (id_subject) REFERENCES subjects (id) ON DELETE CASCADE
);

