SET GLOBAL time_zone = '+1:00';
create schema ify;
create user 'ifyDB'@'%' identified by 'root'; -- Creates the user
grant all on ify.* to 'ifyDB'@'%'; -- Gives all privileges to the new user on the newly created database
use ify;