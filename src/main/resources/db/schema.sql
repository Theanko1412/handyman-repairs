create table country (
  country_id character varying(255) primary key not null,
  name character varying(255)
);

create table city (
  country_id character varying(255) not null,
  name character varying(255),
  town_id character varying(255) primary key not null,
  foreign key (country_id) references country (country_id)
);

create table street (
  city_id character varying(255) not null,
  name character varying(255),
  number integer,
  street_id character varying(255) primary key not null,
  foreign key (city_id) references city (town_id)
);

create table home_or_workshop (
  home_or_workshop_id character varying(255) primary key not null,
  street_id character varying(255) not null,
  foreign key (street_id) references street (street_id)
);

create table category (
  category_id character varying(255) primary key not null,
  description character varying(255),
  name character varying(255)
);

create table service (
  duration integer,
  price double precision,
  category_id character varying(255) not null,
  description character varying(255),
  name character varying(255),
  service_id character varying(255) primary key not null,
  handyman_id character varying(255),
  foreign key (category_id) references category (category_id),
  foreign key (handyman_id) references handyman (handyman_id)
);

create table customer (
  is_suspended boolean,
  strikes integer,
  customer_id character varying(255) primary key not null,
  email character varying(255),
  first_name character varying(255),
  home_or_workshop_id character varying(255),
  last_name character varying(255),
  foreign key (home_or_workshop_id) references home_or_workshop (home_or_workshop_id)
);

create table handyman (
  is_suspended boolean,
  rating double precision,
  email character varying(255),
  first_name character varying(255),
  handyman_id character varying(255) primary key not null,
  home_or_workshop_id character varying(255),
  last_name character varying(255),
  foreign key (home_or_workshop_id) references home_or_workshop (home_or_workshop_id)
);

create table notification (
  date timestamp(6) with time zone,
  customer_id character varying(255),
  handyman_id character varying(255),
  message character varying(255),
  notification_id character varying(255) primary key not null,
  sender character varying(255),
  foreign key (customer_id) references customer (customer_id),
  foreign key (handyman_id) references handyman (handyman_id)
);

create table schedule (
  handyman_id character varying(255),
  schedule_id character varying(255) primary key not null,
  foreign key (handyman_id) references handyman (handyman_id)
);

create table reservation (
  date timestamp(6) with time zone,
  id character varying(255) primary key not null,
  schedule_id character varying(255) not null,
  service_id character varying(255) not null,
  customer_id character varying(255) not null,
  status character varying(255) NOT NULL,
  foreign key (customer_id) references customer (customer_id),
  foreign key (service_id) references service (service_id),
  foreign key (schedule_id) references schedule (schedule_id)
);