drop table if exists applicants CASCADE;
drop table if exists positions CASCADE;
drop table if exists profile_tags CASCADE;
drop table if exists profiles CASCADE;
drop table if exists recruit_post_tags CASCADE;
drop table if exists recruit_posts CASCADE;
drop table if exists tags CASCADE;
drop table if exists users CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;

create table applicants (
                            applicant_id bigint auto_increment,
                            status integer not null,
                            recruit_post_id bigint,
                            user_id bigint,
                            primary key (applicant_id)
);

create table positions (
                           position_id bigint auto_increment,
                           created_at timestamp,
                           updated_at timestamp,
                           position_name varchar(255),
                           primary key (position_id)
);

create table profile_tags (
                              profile_tag_id bigint auto_increment,
                              created_at timestamp,
                              updated_at timestamp,
                              profile_attribute_name varchar(255),
                              profile_id bigint,
                              tag_id bigint,
                              primary key (profile_tag_id)
);

create table profiles (
                          profile_id bigint auto_increment,
                          created_at timestamp,
                          updated_at timestamp,
                          available_period varchar(255),
                          available_time varchar(255),
                          career_period varchar(255),
                          face_to_face boolean,
                          image_url varchar(255),
                          phone_number varchar(255),
                          portfolio_url varchar(255),
                          residence varchar(255),
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
                               image_url varchar(255),
                               project_end_time date,
                               project_start_time date,
                               recruit_due_time date,
                               title varchar(255) not null,
                               total_member_count integer not null,
                               user_id bigint,
                               primary key (recruit_post_id)
);

create table tags (
                      tag_id bigint auto_increment,
                      created_at timestamp,
                      updated_at timestamp,
                      body varchar(255) not null,
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

alter table positions
    add constraint UK_b6lkwasxdrfpxihi038w6ixt6 unique (position_name);

alter table profile_tags
    add constraint UK_g4clkljxsntmx13odla6tipw0 unique (tag_id);

alter table tags
    add constraint UK_m3vc2stgr50365pdb7v6viv7j unique (body);

alter table applicants
    add constraint FK30477m048rwunfws9u80f4mp3
        foreign key (recruit_post_id)
            references recruit_posts;

alter table applicants
    add constraint FKomb2kaikjaatqt8uf5mp5jf1u
        foreign key (user_id)
            references users;

alter table profile_tags
    add constraint FKh2cnq3v5k0p5la4ec2rkt8or
        foreign key (profile_id)
            references profiles;

alter table profile_tags
    add constraint FK5ao5pw06w9glc2dkkko1lh26w
        foreign key (tag_id)
            references tags;

alter table profiles
    add constraint FK1rx5gmrfxycn1309s154a38g
        foreign key (position_id)
            references positions;

alter table profiles
    add constraint FK410q61iev7klncmpqfuo85ivh
        foreign key (user_id)
            references users;

alter table recruit_post_tags
    add constraint FKl9fa1qftq079r8jmxcmsuh2ch
        foreign key (recruit_post_id)
            references recruit_posts;

alter table recruit_post_tags
    add constraint FKsatq22sf6ry4j9f4gmd0uhc5d
        foreign key (tag_id)
            references tags;

alter table recruit_posts
    add constraint FK2400ym5oroydfv42fcv53rcf9
        foreign key (user_id)
            references users;
