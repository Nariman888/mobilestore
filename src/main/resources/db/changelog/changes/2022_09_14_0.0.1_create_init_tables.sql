CREATE TABLE roles(
                      id SERIAL PRIMARY KEY NOT NULL,
                      role VARCHAR NOT NULL
);

CREATE TABLE users(
                      id SERIAL PRIMARY KEY NOT NULL,
                      avatar TEXT NOT NULL,
                      email VARCHAR NOT NULL,
                      password VARCHAR NOT NULL,
                      full_name VARCHAR NOT NULL
);

CREATE TABLE users_roles(
                            users_id INT DEFAULT 0,
                            roles_id INT DEFAULT 0,
                            FOREIGN KEY (users_id) REFERENCES users (id),
                            FOREIGN KEY (roles_id) REFERENCES roles (id)
);

CREATE TABLE brands(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    country VARCHAR NOT NULL
);
CREATE TABLE category(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL
);
CREATE TABLE phone(
    id SERIAL PRIMARY KEY NOT NULL,
    photo VARCHAR NOT NULL,
    model VARCHAR NOT NULL,
    memory VARCHAR NOT NULL,
    camera VARCHAR NOT NULL,
    processor VARCHAR NOT NULL,
    interfaces TEXT NOT NULL,
    price INT DEFAULT 0,
    amount INT DEFAULT 0,
    category_id INT DEFAULT 0,
    CONSTRAINT fk_phone_category
        FOREIGN KEY (category_id)
            REFERENCES  category (id),
    brand_id INT DEFAULT 0,
    CONSTRAINT fk_phone_brands
                  FOREIGN KEY (brand_id)
                  REFERENCES brands (id)
);
CREATE TABLE basket(
    id SERIAL PRIMARY KEY NOT NULL,
    user_id INT DEFAULT 0,
    CONSTRAINT fk_basket_user
                   FOREIGN KEY (user_id)
                   REFERENCES users (id),
    phone_id INT DEFAULT 0,
    CONSTRAINT fk_basket_phone
                   FOREIGN KEY (phone_id)
                   REFERENCES phone(id)
);
CREATE TABLE favorites(
                       id SERIAL PRIMARY KEY NOT NULL,
                       user_id INT DEFAULT 0,
                       CONSTRAINT fk_basket_user
                           FOREIGN KEY (user_id)
                               REFERENCES users (id),
                       phone_id INT DEFAULT 0,
                       CONSTRAINT fk_basket_phone
                           FOREIGN KEY (phone_id)
                               REFERENCES phone(id)
);
CREATE TABLE orders(
                      id SERIAL PRIMARY KEY NOT NULL,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      user_id INT DEFAULT 0,
                      CONSTRAINT fk_order_user
                          FOREIGN KEY (user_id)
                              REFERENCES users (id),
                      phone_id INT DEFAULT 0,
                      CONSTRAINT fk_orders_phone
                          FOREIGN KEY (phone_id)
                              REFERENCES phone(id)
);

CREATE TABLE comments(
                         id SERIAL PRIMARY KEY NOT NULL,
                         comment TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         user_id INT DEFAULT 0,
                         CONSTRAINT fk_comments_users
                             FOREIGN KEY (user_id)
                                 REFERENCES users (id),
                         phone_id INT DEFAULT 0,
                         CONSTRAINT fk_comments_phone
                             FOREIGN KEY (phone_id)
                                 REFERENCES phone (id)
);
CREATE TABLE ratings(
                         id SERIAL PRIMARY KEY NOT NULL,
                         rating INT DEFAULT 0,
                         user_id INT DEFAULT 0,
                         CONSTRAINT fk_ratings_users
                             FOREIGN KEY (user_id)
                                 REFERENCES users (id),
                         phone_id INT DEFAULT 0,
                         CONSTRAINT fk_ratings_phone
                             FOREIGN KEY (phone_id)
                                 REFERENCES phone (id)
);




