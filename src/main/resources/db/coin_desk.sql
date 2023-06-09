create table coin_desk (
    code varchar(4) primary key,
    symbol varchar(16) not null,
    rate numeric(38,2) not null,
    description varchar(128),
    rate_float numeric(38,2) not null
);

insert into coin_desk (CODE, SYMBOL, RATE, DESCRIPTION, RATE_FLOAT)
values ('TWD', '$', 1.001, 'TAIWAN', 1.001);