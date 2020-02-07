DROP TABLE IF EXISTS PATIENT  ;
    create table patient (
       id integer not null,
        age integer not null,
        f_name varchar(255),
        l_name varchar(255),
        sex varchar(255),
        primary key (id)
    );

DROP TABLE IF EXISTS PRESCRIPTION ;
    create table prescription (
       pid integer not null,
        dosage integer not null,
        form varchar(255),
        medicine_name varchar(255),
        quantity integer not null,
        refill boolean not null,
        ppr_fk integer,
        primary key (pid)
    );