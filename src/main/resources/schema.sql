CREATE TABLE IF NOT EXISTS franchise(
    id SERIAL PRIMARY KEY,
    name VARCHAR(70) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS subsidiary(
    id SERIAL PRIMARY KEY,
    name VARCHAR(70) UNIQUE NOT NULL,
    franchise_id INTEGER NOT NULL,
    FOREIGN KEY (franchise_id) REFERENCES franchise(id)
);
CREATE TABLE  IF NOT EXISTS product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(70) UNIQUE NOT NULL,
    stock INTEGER NOT NULL,
    description VARCHAR(130) NOT NULL,
    price FLOAT NOT NULL,
    subsidiary_id INTEGER NOT NULL,
    FOREIGN KEY (subsidiary_id) REFERENCES subsidiary(id)
);
