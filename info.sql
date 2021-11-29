drop table if exists sale_info;
create table sale_info(
    id bigint not null primary key ,
    total double,
    pay double,
    `change` double,
    detail varchar(200),
    `date` date
);

INSERT INTO sale_info(id,total,pay,`change`,detail,`date`) values(1,2200.0,22222.0,20022.0,'牛奶 40.055','2021-11-29');