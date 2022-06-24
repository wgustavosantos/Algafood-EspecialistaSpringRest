insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into estado (nome) values ('PA');
insert into estado (nome) values ('AM');
insert into estado (nome) values ('SP');

insert into cidade (nome, estado_id) values ('Bragança', 1);
insert into cidade (nome, estado_id) values ('Manaus', 2);
insert into cidade (nome, estado_id) values ('São Paulo', 3);
insert into cidade (nome) values ('Olinda');
