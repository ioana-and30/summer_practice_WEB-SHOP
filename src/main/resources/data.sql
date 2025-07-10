INSERT INTO category (id, name)
SELECT 1, 'Wash'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 1);

INSERT INTO category (id, name)
SELECT 2, 'Styling'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 2);

INSERT INTO category (id, name)
SELECT 3, 'Accessories'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 3);

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Shampoo', 'Gentle cleansing shampoo', 29.99, 'images/shampoo.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shampoo');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Conditioner', 'Moisturizing conditioner', 34.99, 'images/conditioner.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Conditioner');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mask', 'Deep nourishing mask', 39.99, 'images/mask.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mask');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Leave-in', 'Leave-in treatment', 24.99, 'images/leavein.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Leave-in');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Shampoo - Hydrating Aloe Vera', 'Hydrating shampoo with aloe vera extract', 30.99, 'images/shampoo_hydrating.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shampoo - Hydrating Aloe Vera');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Shampoo - Volumizing Mint', 'Refreshing volumizing shampoo with mint', 31.50, 'images/shampoo_volumizing.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shampoo - Volumizing Mint');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Shampoo - Soothing Chamomile', 'Gentle shampoo with chamomile for sensitive scalp', 32.00, 'images/shampoo_chamomile.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shampoo - Soothing Chamomile');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Conditioner - Repair & Strengthen', 'Conditioner to repair damaged hair', 35.99, 'images/conditioner_repair.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Conditioner - Repair & Strengthen');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Conditioner - Lightweight Nourish', 'Lightweight conditioner for fine hair', 33.50, 'images/conditioner_lightweight.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Conditioner - Lightweight Nourish');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Conditioner - Color Protect', 'Conditioner with UV filters for color-treated hair', 36.00, 'images/conditioner_color.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Conditioner - Color Protect');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mask - Deep Repair', 'Intensive repair hair mask', 40.99, 'images/mask_deep_repair.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mask - Deep Repair');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mask - Hydrating Coconut', 'Moisturizing hair mask with coconut oil', 38.50, 'images/mask_coconut.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mask - Hydrating Coconut');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mask - Volume Boost', 'Mask for adding volume and body', 39.00, 'images/mask_volume.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mask - Volume Boost');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Leave-in - Curl Enhancer', 'Defines and hydrates curls', 26.99, 'images/leavein_curl.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Leave-in - Curl Enhancer');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Leave-in - Heat Protection', 'Protects hair from heat styling', 25.50, 'images/leavein_heat.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Leave-in - Heat Protection');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Leave-in - Frizz Control', 'Controls frizz and smoothes hair', 27.00, 'images/leavein_frizz.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Leave-in - Frizz Control');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Styling Wax', 'Natural wax for flexible hold', 22.99, 'images/styling_wax.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Styling Wax');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Texturizing Spray', 'Adds texture and volume', 23.99, 'images/texturizing_spray.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Texturizing Spray');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Pomade', 'Shiny hold pomade', 21.50, 'images/pomade.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Pomade');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Wide-Tooth Comb', 'Gentle detangling comb', 13.99, 'images/wide_tooth_comb.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Wide-Tooth Comb');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Silk Pillowcase', 'Protects hair and skin overnight', 25.00, 'images/silk_pillowcase.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Silk Pillowcase');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Hair Ties Set', 'Set of 10 no-damage hair ties', 9.99, 'images/hair_ties_set.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Hair Ties Set');
