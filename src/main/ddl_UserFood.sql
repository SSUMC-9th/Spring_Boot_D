CREATE TABLE user_food
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    user_id BIGINT                NULL,
    food_id INT                   NULL,
    CONSTRAINT pk_user_food PRIMARY KEY (id)
);

ALTER TABLE user_food
    ADD CONSTRAINT FK_USER_FOOD_ON_FOOD FOREIGN KEY (food_id) REFERENCES food (food_id);

ALTER TABLE user_food
    ADD CONSTRAINT FK_USER_FOOD_ON_USER FOREIGN KEY (user_id) REFERENCES user (user_id);