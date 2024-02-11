-- FILEPATH: /Users/dahang/workspace/projects/fbla/FBLA/src/main/resources/db/migration/V1.0__init

-- Create the partner table
-- For PostgreSQL database
CREATE TABLE partner (
  id BIGSERIAL NOT NULL,
  name VARCHAR(255) NOT NULL,
  type VARCHAR(255) NOT NULL,
  resources VARCHAR(255) NOT NULL,
  contact_name VARCHAR(255) NOT NULL,
  contact_email VARCHAR(255) NOT NULL,
  contact_phone VARCHAR(255) NOT NULL,
  about_me VARCHAR(255) NULL,
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE NULL,
  CONSTRAINT partner_pk PRIMARY KEY (id),
  CONSTRAINT contact_name_unq UNIQUE (contact_name),
  CONSTRAINT partner_email_unq UNIQUE (contact_email)
);
