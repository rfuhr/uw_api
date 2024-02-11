create sequence seq_users start with 2;

create table if not exists users (
	id 			bigint 			not null constraint user_pkey primary key,
	username 	varchar(30) 	not null,
	password 	varchar(255)	not null
);

insert into users (id, username, password) values (1, 'admin', '$2a$10$Ms8wDFrOvybTXdW/AV5PtulIees4/PZ2tuK4iSCmfPeDbIvxrkkSm');