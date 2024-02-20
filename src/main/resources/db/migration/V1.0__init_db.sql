-- FILEPATH: /Users/dahang/workspace/projects/fbla/FBLA/src/main/resources/db/migration/V1.0__init

-- Create the partner table
-- For PostgreSQL database
CREATE TABLE partner (
  id BIGSERIAL PRIMARY KEY,
  organization_name VARCHAR(255) NOT NULL UNIQUE,
  type VARCHAR(255) NOT NULL,
  
  contact_email VARCHAR(255) NOT NULL UNIQUE,
  contact_phone VARCHAR(255) NOT NULL,
  contact_url VARCHAR(255) NOT NULL,
  description TEXT NULL,
  created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITHOUT TIME ZONE NULL
);


CREATE TABLE tag (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE partner_tag (
  partner_id bigint REFERENCES partner(id),
  tag_id bigint REFERENCES tag(id),
  PRIMARY KEY (partner_id, tag_id)
)
