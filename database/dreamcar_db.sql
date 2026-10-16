create database if not exists dreamcar;
use dreamcar;

create table if not exists users (
	id bigint primary key auto_increment,
    email varchar(255) unique not null,
    senha varchar(255) not null
);