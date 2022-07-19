-- set foreign_key_checks = 0;

drop table if exists applicants CASCADE;
drop table if exists positions CASCADE;
drop table if exists profile_tags CASCADE;
drop table if exists profiles CASCADE;
drop table if exists recruit_post_tags CASCADE;
drop table if exists recruit_posts CASCADE;
drop table if exists tags CASCADE;
drop table if exists users CASCADE;


create table applicants (
                            applicant_id bigint auto_increment,
                            created_at timestamp,
                            updated_at timestamp,
                            status varchar(10) default '대기중',
                            position varchar(255),
                            recruit_post_id bigint,
                            user_id bigint,
                            primary key (applicant_id)
);

create table positions (
                           position_id bigint auto_increment,
                           created_at timestamp,
                           updated_at timestamp,
                           position_name varchar(255) unique,
                           primary key (position_id)
);

create table profile_tags (
                              profile_tag_id bigint auto_increment,
                              created_at timestamp,
                              updated_at timestamp,
                              profile_attribute_name varchar(255),
                              profile_id bigint,
                              tag_id bigint unique ,
                              primary key (profile_tag_id)
);

create table profiles (
                          profile_id bigint auto_increment,
                          created_at timestamp,
                          updated_at timestamp,
                          available_period varchar(255) default '',
                          available_time varchar(255) default '',
                          career_period varchar(255) default '',
                          face_to_face boolean,
                          image_url varchar(255) default '',
                          phone_number varchar(255) default '',
                          portfolio_url varchar(255) default '',
                          residence varchar(255) default '',
                          position_id bigint,
                          user_id bigint,
                          primary key (profile_id)
);

create table recruit_post_tags (
                                recruit_post_tag_id bigint auto_increment,
                                created_at timestamp,
                                updated_at timestamp,
                                recruit_post_id bigint,
                                tag_id bigint,
                                primary key (recruit_post_tag_id)
);

create table recruit_posts (
                               recruit_post_id bigint auto_increment,
                               created_at timestamp,
                               updated_at timestamp,
                               body varchar(255) not null,
                               image_url varchar(255) default '',
                               project_end_time date,
                               project_start_time date,
                               recruit_due_time date,
                               title varchar(255) not null,
                               required_developers integer default 0,
                               required_designers integer default 0,
                               required_project_managers integer default 0,
                               recruit_status  boolean  default true,
                               user_id bigint,
                               profile_id bigint,
                               primary key (recruit_post_id)
);

create table tags (
                      tag_id bigint auto_increment,
                      created_at timestamp,
                      updated_at timestamp,
                      body varchar(255) not null unique,
                      primary key (tag_id)
);

create table users (
                       user_id bigint auto_increment,
                       created_at timestamp,
                       updated_at timestamp,
                       email varchar(255) not null,
                       social_type varchar(255),
                       user_role varchar(255),
                       username varchar(255) not null,
                       primary key (user_id)
);


alter table applicants
    add constraint fk_applicant_recruit_post_id
        foreign key (recruit_post_id)
            references recruit_posts(recruit_post_id);

alter table applicants
    add constraint fk_applicant_user_id
        foreign key (user_id)
            references users(user_id);

alter table profile_tags
    add constraint fk_profile_tag_profile_id
        foreign key (profile_id)
            references profiles(profile_id);

alter table profile_tags
    add constraint fk_profile_tag_tag_id
        foreign key (tag_id)
            references tags(tag_id);

alter table profiles
    add constraint fk_profile_position_id
        foreign key (position_id)
            references positions(position_id);

alter table profiles
    add constraint fk_profile_user_id
        foreign key (user_id)
            references users(user_id);

alter table recruit_post_tags
    add constraint fk_recruit_post_tag_recruit_post_id
        foreign key (recruit_post_id)
            references recruit_posts(recruit_post_id);

alter table recruit_post_tags
    add constraint fk_recruit_post_tag_tag_id
        foreign key (tag_id)
            references tags(tag_id);

alter table recruit_posts
    add constraint fk_recruit_post_user_id
        foreign key (user_id)
            references users(user_id);

alter table recruit_posts
    add constraint fk_recruit_post_profile_id
        foreign key (profile_id)
            references profiles(profile_id);
