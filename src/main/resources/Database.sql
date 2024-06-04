# 2024-06-04
# 记录所有数据库表格异动情况
# 参考此表进行POJO MAPPER设计

CREATE TABLE user (
                      user_id INT NOT NULL AUTO_INCREMENT,
                      bit_id CHAR(10) NOT NULL,
                      name VARCHAR(50) NOT NULL,
                      gender ENUM('male', 'female', 'unknown') NOT NULL DEFAULT 'unknown',
                      password VARCHAR(50) NOT NULL,
                      contact_info VARCHAR(255) DEFAULT '',
                      personal_signature VARCHAR(255) DEFAULT '',
                      avatar_url VARCHAR(500) NOT NULL DEFAULT 'default_avatar_url',
                      state ENUM('frozen', 'normal') NOT NULL DEFAULT 'normal',
                      created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (user_id)
);

ALTER TABLE user
    MODIFY COLUMN user_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE user
    MODIFY COLUMN name VARCHAR(30) NOT NULL;

ALTER TABLE user
    ADD COLUMN update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE user
    CHANGE COLUMN created_time create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

SET GLOBAL log_bin_trust_function_creators = 1;

CREATE TRIGGER user_insert
    BEFORE INSERT ON user
    FOR EACH ROW
BEGIN
    SET NEW.create_time = NOW();
    SET NEW.update_time = NOW();
END;

CREATE TRIGGER user_update
    BEFORE UPDATE ON user
    FOR EACH ROW
BEGIN
    IF OLD.bit_id <> NEW.bit_id OR OLD.name <> NEW.name OR OLD.gender <> NEW.gender OR OLD.password <> NEW.password OR OLD.contact_info <> NEW.contact_info OR OLD.personal_signature <> NEW.personal_signature OR OLD.avatar_url <> NEW.avatar_url OR OLD.state <> NEW.state OR OLD.create_time <> NEW.create_time THEN
        SET NEW.update_time = NOW();
    END IF;
END;

CREATE TRIGGER check_bit_id_before_insert
    BEFORE INSERT ON user
    FOR EACH ROW
BEGIN
    IF EXISTS (SELECT 1 FROM user WHERE bit_id = NEW.bit_id) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'bit_id already exists';
    END IF;
END;

CREATE TABLE product (
                         product_id INT NOT NULL AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL DEFAULT 0,
                         purchase_method ENUM('pickup', 'express', 'self-negotiated', 'others') NOT NULL DEFAULT 'self-negotiated',
                         product_category ENUM('电脑电子', '游戏影视', '衣包饰品', '体育休闲', '学习办公', '食品饮料', '其他') NOT NULL,
                         publisher_id INT NOT NULL,
                         status ENUM('on-sale', 'frozen', 'sold-out') NOT NULL DEFAULT 'on-sale',
                         inventory INT NOT NULL DEFAULT 1,
                         description TEXT,
                         is_anonymous TINYINT(1) NOT NULL DEFAULT 0,
                         created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (product_id),
                         FOREIGN KEY (publisher_id) REFERENCES user(user_id)
);

ALTER TABLE product
    MODIFY COLUMN product_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE product
    MODIFY COLUMN publisher_id INTEGER NOT NULL;

ALTER TABLE product
    MODIFY COLUMN inventory INTEGER NOT NULL DEFAULT 1;

ALTER TABLE product
    MODIFY COLUMN is_anonymous INTEGER NOT NULL DEFAULT 0;

ALTER TABLE product
    MODIFY COLUMN name VARCHAR(50) NOT NULL;

ALTER TABLE product
    CHANGE COLUMN created_time create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE product
    CHANGE COLUMN updated_time update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TRIGGER product_insert
    BEFORE INSERT ON product
    FOR EACH ROW
BEGIN
    SET NEW.create_time = NOW();
    SET NEW.update_time = NOW();
END;

CREATE TRIGGER update_time_trigger
    BEFORE UPDATE ON product
    FOR EACH ROW
BEGIN
    IF NEW.name <> OLD.name OR NEW.price <> OLD.price OR NEW.purchase_method <> OLD.purchase_method OR NEW.product_category <> OLD.product_category OR NEW.publisher_id <> OLD.publisher_id OR NEW.status <> OLD.status OR NEW.inventory <> OLD.inventory OR NEW.description <> OLD.description OR NEW.is_anonymous <> OLD.is_anonymous OR NEW.create_time <> OLD.create_time THEN
        SET NEW.update_time = NOW();
    END IF;
END;

CREATE TABLE product_image (
                               image_id INT NOT NULL AUTO_INCREMENT,
                               product_id INT NOT NULL,
                               image_url VARCHAR(500) NOT NULL,
                               created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (image_id),
                               FOREIGN KEY (product_id) REFERENCES product(product_id)
);

ALTER TABLE product_image
    MODIFY COLUMN image_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE product_image
    MODIFY COLUMN product_id INTEGER NOT NULL;

ALTER TABLE product_image
    CHANGE COLUMN created_time create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TRIGGER product_image_insert
    BEFORE INSERT ON product_image
    FOR EACH ROW
BEGIN
    SET NEW.create_time = NOW();
END;

CREATE TABLE collect (
                         user_id INT NOT NULL,
                         product_id INT NOT NULL,
                         collect_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (user_id, product_id),
                         FOREIGN KEY (user_id) REFERENCES user(user_id),
                         FOREIGN KEY (product_id) REFERENCES product(product_id)
);

ALTER TABLE collect
    MODIFY COLUMN user_id INTEGER NOT NULL;

ALTER TABLE collect
    MODIFY COLUMN product_id INTEGER NOT NULL;

CREATE TRIGGER collect_insert
    BEFORE INSERT ON collect
    FOR EACH ROW
BEGIN
    SET NEW.collect_time = NOW();
END;

CREATE TABLE follow (
                        follower_id INT NOT NULL,
                        followed_id INT NOT NULL,
                        follow_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (follower_id, followed_id),
                        FOREIGN KEY (follower_id) REFERENCES user(user_id),
                        FOREIGN KEY (followed_id) REFERENCES user(user_id)
);

ALTER TABLE follow
    MODIFY COLUMN follower_id INTEGER NOT NULL;

ALTER TABLE follow
    MODIFY COLUMN followed_id INTEGER NOT NULL;

CREATE TRIGGER follow_insert
    BEFORE INSERT ON follow
    FOR EACH ROW
BEGIN
    SET NEW.follow_time = NOW();
END;

CREATE TABLE comment (
                         comment_id INT NOT NULL AUTO_INCREMENT,
                         user_id INT NOT NULL,
                         product_id INT NOT NULL,
                         is_anonymous TINYINT(1) NOT NULL DEFAULT 0,
                         content TEXT NOT NULL,
                         created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (comment_id),
                         FOREIGN KEY (user_id) REFERENCES user(user_id),
                         FOREIGN KEY (product_id) REFERENCES product(product_id)
);

ALTER TABLE comment
    MODIFY COLUMN is_anonymous INTEGER NOT NULL DEFAULT 0;

ALTER TABLE comment
    MODIFY COLUMN comment_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE comment
    MODIFY COLUMN user_id INTEGER NOT NULL;

ALTER TABLE comment
    MODIFY COLUMN product_id INTEGER NOT NULL;

ALTER TABLE comment
    MODIFY COLUMN product_id INTEGER NOT NULL;

ALTER TABLE comment
    CHANGE COLUMN created_time create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TRIGGER comment_insert
    BEFORE INSERT ON comment
    FOR EACH ROW
BEGIN
    SET NEW.create_time = NOW();
END;

CREATE TABLE message (
                         message_id INT NOT NULL AUTO_INCREMENT,
                         from_id INT NOT NULL,
                         to_id INT NOT NULL,
                         is_anonymous TINYINT(1) NOT NULL DEFAULT 0,
                         content TEXT NOT NULL,
                         is_read TINYINT(1) NOT NULL DEFAULT 0,
                         created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (message_id),
                         FOREIGN KEY (from_id) REFERENCES user(user_id),
                         FOREIGN KEY (to_id) REFERENCES user(user_id)
);

ALTER TABLE message
    MODIFY COLUMN message_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE message
    MODIFY COLUMN from_id INTEGER NOT NULL;

ALTER TABLE message
    MODIFY COLUMN to_id INTEGER NOT NULL;

ALTER TABLE message
    MODIFY COLUMN is_anonymous INTEGER NOT NULL DEFAULT 0;

ALTER TABLE message
    MODIFY COLUMN is_read INTEGER NOT NULL DEFAULT 0;

ALTER TABLE message
    CHANGE COLUMN created_time create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TRIGGER message_insert
    BEFORE INSERT ON message
    FOR EACH ROW
BEGIN
    SET NEW.create_time = NOW();
END;

CREATE TABLE report (
                        report_id INT NOT NULL AUTO_INCREMENT,
                        reporter_id INT NOT NULL,
                        product_id INT NOT NULL,
                        violation_type ENUM('广告骚扰', '虚假宣传', '侵权行为', '违规内容', '其他') NOT NULL,
                        description TEXT,
                        created_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (report_id),
                        FOREIGN KEY (reporter_id) REFERENCES user(user_id),
                        FOREIGN KEY (product_id) REFERENCES product(product_id)
);

ALTER TABLE report
    MODIFY COLUMN report_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE report
    MODIFY COLUMN reporter_id INTEGER NOT NULL;

ALTER TABLE report
    MODIFY COLUMN product_id INTEGER NOT NULL;

ALTER TABLE report
    CHANGE COLUMN created_time create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TRIGGER report_insert
    BEFORE INSERT ON report
    FOR EACH ROW
BEGIN
    SET NEW.create_time = NOW();
END;

CREATE TABLE admin (
                       admin_id INT NOT NULL AUTO_INCREMENT,
                       password VARCHAR(50) NOT NULL,
                       name VARCHAR(30) NOT NULL,
                       PRIMARY KEY (admin_id)
);

ALTER TABLE admin
    MODIFY COLUMN admin_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE admin
    ADD COLUMN create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE product_label (
                               label_id INT NOT NULL AUTO_INCREMENT,
                               product_id INT NOT NULL,
                               name VARCHAR(50) NOT NULL,
                               PRIMARY KEY (label_id),
                               FOREIGN KEY (product_id) REFERENCES product(product_id)
);

ALTER TABLE product_label
    MODIFY COLUMN label_id INTEGER NOT NULL AUTO_INCREMENT;

ALTER TABLE product_label
    MODIFY COLUMN product_id INTEGER NOT NULL;

ALTER TABLE product_label
    ADD COLUMN create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP;




