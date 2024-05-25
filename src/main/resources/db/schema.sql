create table country (
  country_id varchar(255) primary key not null,
  name varchar(255)
);

create table city (
  town_id varchar(255) primary key not null,
  name varchar(255),
  country_id varchar(255) not null,
  foreign key (country_id) references country (country_id)
);

create table street (
  street_id varchar(255) primary key not null,
  name varchar(255),
  number integer,
  city_id varchar(255) not null,
  foreign key (city_id) references city (town_id)
);

create table home_or_workshop (
  homeOrWorkshop_id varchar(255) primary key not null,
  name varchar(255),
  street_id varchar(255) not null,
  foreign key (street_id) references street (street_id)
);

create table category (
  category_id varchar(255) primary key not null,
  name varchar(255),
  description varchar(255)
);

create table handyman (
  handyman_id varchar(255) primary key not null,
  first_name varchar(255),
  last_name varchar(255),
  email varchar(255),
  password varchar(255),
  rating double precision,
  is_suspended boolean,
  homeOrWorkshop_id varchar(255),
  foreign key (homeOrWorkshop_id) references home_or_workshop (homeOrWorkshop_id)
);

create table service (
  service_id varchar(255) primary key not null,
  name varchar(255),
  description varchar(255),
  price double precision,
  duration integer,
  category_id varchar(255) not null,
  handyman_id varchar(255) not null,
  foreign key (category_id) references category (category_id),
  foreign key (handyman_id) references handyman (handyman_id)
);

create table customer (
  customer_id varchar(255) primary key not null,
  first_name varchar(255),
  last_name varchar(255),
  email varchar(255),
  password varchar(255),
  strikes integer,
  is_suspended boolean,
  homeOrWorkshop_id varchar(255),
  foreign key (homeOrWorkshop_id) references home_or_workshop (homeOrWorkshop_id)
);

create table notification (
  notification_id varchar(255) primary key not null,
  message varchar(255),
  date timestamp with time zone,
  sender varchar(255),
  customer_id varchar(255),
  handyman_id varchar(255),
  foreign key (customer_id) references customer (customer_id),
  foreign key (handyman_id) references handyman (handyman_id)
);

create table schedule (
  schedule_id varchar(255) primary key not null,
  handyman_id varchar(255),
  foreign key (handyman_id) references handyman (handyman_id)
);

create table reservation (
  id varchar(255) primary key not null,
  date timestamp with time zone,
  status varchar(255) not null,
  customer_id varchar(255) not null,
  schedule_id varchar(255) not null,
  service_id varchar(255) not null,
  foreign key (customer_id) references customer (customer_id),
  foreign key (service_id) references service (service_id),
  foreign key (schedule_id) references schedule (schedule_id)
);
