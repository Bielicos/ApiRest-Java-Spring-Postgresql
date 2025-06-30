CREATE TABLE user_tb (
   id SERIAL PRIMARY KEY,
   first_name VARCHAR(255),
   last_name  VARCHAR(255),
   email      VARCHAR(255),
   password   VARCHAR(255)
);