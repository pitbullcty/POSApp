drop table if exists sale_info;
create table sale_info
(
    id       bigint not null primary key,
    total    double,
    pay      double,
    `change` double,
    detail   varchar(200),
    `date`   date
);