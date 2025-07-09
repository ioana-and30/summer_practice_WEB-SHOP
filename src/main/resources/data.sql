-- Inserare categorii fără duplicate
INSERT INTO category (id, name)
SELECT 1, 'Wash'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 1);

INSERT INTO category (id, name)
SELECT 2, 'Styling'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 2);

INSERT INTO category (id, name)
SELECT 3, 'Accessories'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 3);

-- Inserare produse fără duplicate pe nume
INSERT INTO product (name, description, price, image, category_id)
SELECT 'Shampoo', 'Gentle cleansing shampoo', 29.99, 'images/shampoo.jpg', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shampoo');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Conditioner', 'Moisturizing conditioner', 34.99, 'images/conditioner.jpg', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Conditioner');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mask', 'Deep nourishing mask', 39.99, 'images/mask.jpg', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mask');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Leave-in', 'Leave-in treatment', 24.99, 'images/leavein.jpg', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Leave-in');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Gel', 'Strong hold gel', 19.99, 'images/gel.jpg', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Gel');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mousse', 'Volume mousse', 22.99, 'images/mousse.jpg', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mousse');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Curl cream', 'Define your curls', 27.99, 'images/curlcream.jpg', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Curl cream');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Ulei', 'Hair nourishing oil', 31.99, 'images/ulei.jpg', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Ulei');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Hairdryer', 'Compact hairdryer with diffuser', 79.99, 'images/hairdryer.jpg', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Hairdryer');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Hair clips', 'Set of 6 strong clips', 9.99, 'images/clips.jpg', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Hair clips');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Brush', 'Detangling brush for curls', 14.99, 'images/brush.jpg', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Brush');
