insert into manufacturer (id, name, date_register, date_update) values (1, 'WEG', utc_timestamp, utc_timestamp);
insert into manufacturer (id, name, date_register, date_update) values (2, 'GM', utc_timestamp, utc_timestamp);
insert into manufacturer (id, name, date_register, date_update) values (3, 'Embraer', utc_timestamp, utc_timestamp);



insert into state(id, name) values(1, 'Rio De Janeiro');
insert into state(id, name) values(2, 'São Paulo');
insert into state(id, name) values(3, 'Santa Catarina');
insert into state(id, name) values(4, 'Minas Gerais');



INSERT INTO city (id, name, state_id) values(1, 'Niteroi', 1);
INSERT INTO city (id, name, state_id) values(2, 'Bangu', 1);