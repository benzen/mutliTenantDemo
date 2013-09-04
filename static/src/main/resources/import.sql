CREATE USER "mtUser" with password 'secret';
CREATE DATABASE gold owner "mtUser";
\c gold
CREATE TABLE contact(text name);
ALTER TABLE contact OWNER TO "mtUser";
INSERT INTO contact VALUES ('1');
INSERT INTO contact VALUES ('3');
INSERT INTO contact VALUES ('2');

CREATE DATABASE silver owner "mtUser";
\c silver;
CREATE TABLE contact(text name);
ALTER TABLE contact OWNER TO "mtUser";
INSERT INTO contact VALUES ('1');
INSERT INTO contact VALUES ('2');


CREATE DATABASE bronze owner "mtUser";
\c bronze
CREATE TABLE contact(text name);
ALTER TABLE contact OWNER TO "mtUser";
INSERT INTO contact VALUES ('1');
