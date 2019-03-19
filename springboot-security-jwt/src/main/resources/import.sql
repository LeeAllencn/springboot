insert into user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, now());
insert into user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) values (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, now());
insert into user (id, username, password, firstname, lastname, email, enabled, lastpasswordresetdate) values (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, now());

insert into authority (id, name) values (1, 'ROLE_USER');
insert into authority (id, name) values (2, 'ROLE_ADMIN');

insert into user_authority (user_id, authority_id) values (1, 1);
insert into user_authority (user_id, authority_id) values (1, 2);
insert into user_authority (user_id, authority_id) values (2, 1);
insert into user_authority (user_id, authority_id) values (3, 1);
