CREATE TABLE customers
(
    id   SERIAL,
    name VARCHAR NOT NULL,
    balance INTEGER NOT NULL
);

CREATE TABLE products
(
    id   SERIAL,
    name VARCHAR NOT NULL,
    price INTEGER NOT NULL,
    type VARCHAR NOT NULL
);

CREATE TABLE purchases
(
    id   SERIAL,
    name VARCHAR NOT NULL,
    totalPrice INTEGER NOT NULL,
    customerId INTEGER NOT NULL
);

CREATE TABLE purchase_items
(
    id   SERIAL,
    priceAtPurchase INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    purchaseId INTEGER NOT NULL,
    productId INTEGER NOT NULL
);
