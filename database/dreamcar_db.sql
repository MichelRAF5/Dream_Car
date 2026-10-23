create database if not exists dreamcar;
use dreamcar;

create table if not exists users (
	id bigint primary key auto_increment,
    email varchar(255) unique not null,
    senha varchar(255) not null,
    nome varchar(255) not null,
    sobrenome varchar(255) not null,
    telefone char(11) not null
);

create table if not exists enderecos (
	id bigint primary key auto_increment,
    id_usuario bigint not null,
    cep char(8) not null,
    rua varchar(255) not null,
    numero varchar(10) not null,
    bairro varchar(255) not null,
    cidade varchar(255) not null,
    estado char(2) not null,
    observacao varchar(255),
    
    foreign key (id_usuario) references users(id)
);