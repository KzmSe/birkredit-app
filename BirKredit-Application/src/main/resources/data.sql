create table user
(
    id           int          not null
        auto_increment
        constraint table_nameuser_pk
            primary key,
    username     varchar(50)  not null,
    password     varchar(100) not null,
    name         varchar(50)  not null,
    surname      varchar(50)  not null,
    phone_number varchar(50)  not null,
    role         varchar(50)  not null
);

create table customer
(
    id              int          not null
        auto_increment
        constraint table_namecustomer_pk
            primary key,
    name            varchar(50)  not null,
    surname         varchar(50)  not null,
    customer_number varchar(50)  not null,
    address         varchar(100) not null,
    date_of_birth   timestamp    not null
);

create table credit
(
    id                   int         not null
        auto_increment
        constraint table_namecredit_pk
            primary key,
    credit_number        varchar(50) not null,
    start_date           timestamp   not null,
    end_date             timestamp   not null,
    amount               double      not null,
    debt                 double      not null,
    percentage           double      not null,
    months               double      not null,
    percentage_per_month double      not null,
    payment_per_month    double      not null,
    fk_customer_id       int         not null
);

create table payment
(
    id                       int     not null
        auto_increment
        constraint table_namepayment_pk
            primary key,
    payment_number           varchar(50),
    payment_date             timestamp,
    amount_of_payment        double,
    duration_per_month       double,
    main_amount_of_month     double  not null,
    interest_amount_of_month double  not null,

    is_payed                 boolean not null,
    fk_credit_id             int     not null
);


alter table credit
    add constraint credit_customer_id_fk
        foreign key (fk_customer_id) references customer;

alter table payment
    add constraint payment_credit_id_fk
        foreign key (fk_credit_id) references credit;


INSERT INTO user(username, password, name, surname, phone_number, role)
VALUES ('sanank',
        '$2a$12$mweQEVwtV5EfnVW9yWnSSuaG1VBX2BXtFHDrjYBK0kBtLSbgT/Zvu',
        'Sanan',
        'Kazimov',
        '+994-55-921-03-30',
        'ROLE_ADMINISTRATOR');

INSERT INTO customer(name, surname, customer_number, address, date_of_birth)
VALUES ('Sanan',
        'Kazimov',
        '1q2w3e4r5t6y',
        'Baku Azerbaijan',
        '1996-02-26');