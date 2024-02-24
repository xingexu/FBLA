-- FILEPATH: /Users/dahang/workspace/projects/fbla/FBLA/src/main/resources/db/migration/V1.0__init

-- Create the partner table
-- For PostgreSQL database
CREATE TABLE partner (
  id BIGSERIAL PRIMARY KEY,
  organization_name VARCHAR(255) NOT NULL UNIQUE,
  type VARCHAR(255) NOT NULL,
  
  contact_email VARCHAR(255) NOT NULL UNIQUE,
  contact_phone VARCHAR(255) NOT NULL,
  contact_url VARCHAR(255) NULL,
  logo_path VARCHAR(255) NULL,
  description TEXT NULL,
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE NULL
);


CREATE TABLE tag (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE partner_tag (
  partner_id bigint NOT NULL,
  tag_id bigint NOT NULL,
  PRIMARY KEY (partner_id, tag_id),

  FOREIGN KEY (partner_id) REFERENCES partner (id),
  FOREIGN KEY (tag_id) REFERENCES tag (id)
)


-- CREATE TABLE users (
--     user_id SERIAL PRIMARY KEY,
--     username VARCHAR(45) NOT NULL UNIQUE,
--     password VARCHAR(64) NOT NULL,
--     enabled BOOLEAN NOT NULL
-- );

-- CREATE TABLE roles (
--     role_id SERIAL PRIMARY KEY,
--     name VARCHAR(45) NOT NULL UNIQUE
-- );

-- CREATE TABLE users_roles (
--     user_id INT NOT NULL,
--     role_id INT NOT NULL,
--     PRIMARY KEY (user_id, role_id),
--     FOREIGN KEY (user_id) REFERENCES users (user_id),
--     FOREIGN KEY (role_id) REFERENCES roles (role_id)
-- );
