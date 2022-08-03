set foreign_key_checks = 0;

drop table if exists applicants;

drop table if exists positions;

drop table if exists profile_tags;

drop table if exists profiles;

drop table if exists recruit_post_tags;

drop table if exists recruit_posts;

drop table if exists tags;

drop table if exists users;

create table applicants (
                            applicant_id bigint not null auto_increment,
                            created_at datetime(6),
                            updated_at datetime(6),
                            position varchar(255),
                            status varchar(10) default '대기중',
                            recruit_post_id bigint,
                            user_id bigint,
                            primary key (applicant_id)
) engine=InnoDB;

create table positions (
                           position_id bigint not null auto_increment,
                           created_at datetime(6),
                           updated_at datetime(6),
                           position_name varchar(255),
                           primary key (position_id)
) engine=InnoDB;

create table profile_tags (
                              profile_tag_id bigint not null auto_increment,
                              created_at datetime(6),
                              updated_at datetime(6),
                              profile_attribute_name varchar(255),
                              profile_id bigint,
                              tag_id bigint,
                              primary key (profile_tag_id)
) engine=InnoDB;

create table profiles (
                          profile_id bigint not null auto_increment,
                          created_at datetime(6),
                          updated_at datetime(6),
                          available_period varchar(255) default '',
                          available_time varchar(255) default '',
                          career_period varchar(255) default '',
                          face_to_face boolean default false,
                          image_url varchar(255) default '',
                          phone_number varchar(255) default '',
                          portfolio_url varchar(255) default '',
                          residence varchar(255) default '',
                          position_id bigint,
                          user_id bigint,
                          primary key (profile_id)
) engine=InnoDB;

create table recruit_post_tags (
                                   recruit_post_tag_id bigint not null auto_increment,
                                   created_at datetime(6),
                                   updated_at datetime(6),
                                   recruit_post_id bigint,
                                   tag_id bigint,
                                   primary key (recruit_post_tag_id)
) engine=InnoDB;

create table recruit_posts (
                               recruit_post_id bigint not null auto_increment,
                               created_at datetime(6),
                               updated_at datetime(6),
                               body longtext not null,
                               image_url varchar(255) default '',
                               project_end_time date,
                               project_start_time date,
                               recruit_due_time date,
                               recruit_status boolean default true,
                               required_designers integer default 0,
                               required_developers integer default 0,
                               required_project_managers integer default 0,
                               title varchar(255) not null,
                               profile_id bigint,
                               user_id bigint,
                               primary key (recruit_post_id)
) engine=InnoDB;

create table tags (
                      tag_id bigint not null auto_increment,
                      created_at datetime(6),
                      updated_at datetime(6),
                      body varchar(255) not null,
                      primary key (tag_id)
) engine=InnoDB;

create table users (
                       user_id bigint not null auto_increment,
                       created_at datetime(6),
                       updated_at datetime(6),
                       email varchar(255) not null,
                       social_type varchar(255),
                       user_role varchar(255),
                       username varchar(255) not null,
                       primary key (user_id)
) engine=InnoDB;

alter table positions
    add constraint uk_position_name unique (position_name);

alter table tags
    add constraint uk_tags unique (body);

alter table applicants
    add constraint fk_applicants_recruit_post_id
        foreign key (recruit_post_id)
            references recruit_posts (recruit_post_id);

alter table applicants
    add constraint fk_applicants_user_id
        foreign key (user_id)
            references users (user_id);

alter table profile_tags
    add constraint fk_profile_tags_profile_id
        foreign key (profile_id)
            references profiles (profile_id);

alter table profile_tags
    add constraint fk_profile_tags_tag_id
        foreign key (tag_id)
            references tags (tag_id);

alter table profiles
    add constraint fk_profiles_position_id
        foreign key (position_id)
            references positions (position_id);

alter table profiles
    add constraint fk_profiles_user_id
        foreign key (user_id)
            references users (user_id);

alter table recruit_post_tags
    add constraint fk_recruit_post_tags_recruit_post_id
        foreign key (recruit_post_id)
            references recruit_posts (recruit_post_id);

alter table recruit_post_tags
    add constraint fk_recruit_post_tags_tag_id
        foreign key (tag_id)
            references tags (tag_id);

alter table recruit_posts
    add constraint fk_recruit_posts_profile_id
        foreign key (profile_id)
            references profiles (profile_id);

alter table recruit_posts
    add constraint fk_recruit_posts_user_id
        foreign key (user_id)
            references users (user_id);
