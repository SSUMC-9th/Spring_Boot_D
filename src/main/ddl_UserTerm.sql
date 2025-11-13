CREATE TABLE user_term
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT                NULL,
    term_id BIGINT                NULL,
    CONSTRAINT pk_user_term PRIMARY KEY (id)
);

ALTER TABLE user_term
    ADD CONSTRAINT FK_USER_TERM_ON_TERM FOREIGN KEY (term_id) REFERENCES term (term_id);

ALTER TABLE user_term
    ADD CONSTRAINT FK_USER_TERM_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);