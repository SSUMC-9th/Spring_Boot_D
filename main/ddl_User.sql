CREATE TABLE user
(
    user_id      BIGINT       NOT NULL,
    name         VARCHAR(50)  NOT NULL,
    gender       VARCHAR(10)  NOT NULL,
    birth        VARCHAR(10)  NOT NULL,
    address      VARCHAR(10)  NOT NULL,
    email        VARCHAR(20)  NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    point        INT          NOT NULL,
    social_uid   VARCHAR(255) NOT NULL,
    social_type  VARCHAR(20)  NOT NULL,
    created_at   datetime     NOT NULL,
    updated_at   datetime     NOT NULL,
    deleted_at   datetime     NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);
CREATE TABLE user
(
    user_id      BIGINT       NOT NULL,
    name         VARCHAR(50)  NOT NULL,
    gender       VARCHAR(10)  NOT NULL,
    birth        VARCHAR(10)  NOT NULL,
    address      VARCHAR(10)  NOT NULL,
    email        VARCHAR(20)  NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    point        INT          NOT NULL,
    social_uid   VARCHAR(255) NOT NULL,
    social_type  VARCHAR(20)  NOT NULL,
    created_at   datetime     NOT NULL,
    updated_at   datetime     NOT NULL,
    deleted_at   datetime     NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);