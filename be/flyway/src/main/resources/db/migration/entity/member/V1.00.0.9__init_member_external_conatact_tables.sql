create table member_external_contact
(
    id         bigint       not null auto_increment,
    create_at  datetime(6)  not null,
    deleted    bit          not null,
    update_at  datetime(6)  not null,
    member_fk  bigint       not null,
    contact_tp varchar(255) not null,
    resource   json,
    source     varchar(255) not null,
    primary key (id)
);
