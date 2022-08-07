drop table if exists project_members CASCADE;

drop table if exists project_tags CASCADE;

drop table if exists projects CASCADE;

drop table if exists work_spaces CASCADE;


create table project_members (
                                 project_member_id bigint not null auto_increment,
                                 project_id bigint,
                                 user_id bigint,
                                 primary key (project_member_id)
) engine=InnoDB;

create table project_tags (
                              project_tags_id bigint not null auto_increment,
                              project_id bigint,
                              tag_id bigint,
                              primary key (project_tags_id)
) engine=InnoDB;

create table projects (
                          project_id bigint not null auto_increment,
                          created_at datetime(6),
                          updated_at datetime(6),
                          img_url varchar(255),
                          project_name varchar(255),
                          uuid varchar(255),
                          primary key (project_id)
) engine=InnoDB;


create table work_spaces (
                             id bigint not null auto_increment,
                             created_at datetime(6),
                             updated_at datetime(6),
                             content varchar(255),
                             title varchar(255),
                             project_id bigint,
                             primary key (id)
) engine=InnoDB;


alter table projects
    add constraint UK_11wu5erb8fu7opoghntvx5lag unique (uuid);

alter table project_members
    add constraint FKdki1sp2homqsdcvqm9yrix31g
        foreign key (project_id)
            references projects (project_id);

alter table project_members
    add constraint FKgul2el0qjk5lsvig3wgajwm77
        foreign key (user_id)
            references users (user_id);

alter table project_tags
    add constraint FKra1vi3p19o2pqtm3c1geaose9
        foreign key (project_id)
            references projects (project_id);

alter table project_tags
    add constraint FK1xxsh0w5fhlulg5kvid04dpkm
        foreign key (tag_id)
            references tags (tag_id);

alter table work_spaces
    add constraint FK3leg86g0r5eb7vf5387q1js69
        foreign key (project_id)
            references projects (project_id);