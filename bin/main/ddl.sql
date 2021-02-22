create table user ( msrl bigint not null auto_increment, name varchar(100) not null, password varchar(100), provider varchar(100), uid varchar(50) not null, primary key (msrl) ) engine=InnoDB;

create table user_roles ( user_msrl bigint not null, roles varchar(255) ) engine=InnoDB;

alter table user add constraint UK_a7hlm8sj8kmijx6ucp7wfyt31 unique (uid);

alter table user_roles add constraint FKel3d4qj41g0sy1mtp4sh055g7 foreign key (user_msrl) references user (msrl);

-- oauth2 userRepository test
insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)
values('testClientId',null,'{bcrypt}$2a$10$H2oQgFY7qCRHWqkvAV4P6ONy2v74wfr3fQv.xERw3BJYSqh/Gcgrq','read,write','authorization_code','http://localhost:18090/oauth2/callback','ROLE_USER',36000,50000,null,null);