INSERT INTO category (id, name)
SELECT 1, 'Wash'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 1);

INSERT INTO category (id, name)
SELECT 2, 'Styling'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 2);

INSERT INTO category (id, name)
SELECT 3, 'Accessories'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 3);

INSERT INTO category (id, name)
SELECT 4, 'Sales'
    WHERE NOT EXISTS (SELECT 1 FROM category WHERE id = 4);

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Cammille Rose Coconut Water Curl Cleanse', 'Gentle hidrating shampoo without sulfates', 14.99, 'images/camille-coconut-cleanse.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Cammille Rose Coconut Water Curl Cleanse');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Boucleme Curls Redefined Curl Conditioner', 'Moisturizing conditioner for curly hair', 34.99, 'images/conditioner-boucleme.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Boucleme Curls Redefined Curl Conditioner');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'As I Am Restore & Repair Masque', 'Deep nourishing and hidrating mask', 39.99, 'images/mask-AsIAm-Restore&Repair.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'As I Am Restore & Repair Masque');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'As I Am Restore & Repair Leave-in', 'Leave-in treatment that makes detangling easier', 11.99, 'images/leavein-AsIAm-Restore&Repair.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'As I Am Restore & Repair Leave-in');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Garnier Fructis Hydrating Aloe Vera Shampoo', 'Hydrating shampoo with aloe vera extract', 8.99, 'images/shampoo-fructis-aloevera.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Garnier Fructis Hydrating Aloe Vera Shampoo');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Mielle Rosemary Mint Shampoo', 'Instantly strengthen and cleanse hair', 31.50, 'images/shampoo_mielle-mint.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mielle Rosemary Mint Shampoo');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Innersense Clarity Hairbath Shampoo', 'Clean, gentle, fragrance-free hypoallergenic shampoo', 32.00, 'images/shampoo-innersense-clarity.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Innersense Clarity Hairbath Shampoo');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Not Your Mother’s Curl Talk 3in1 Conditioner', 'Conditioner to repair damaged hair', 35.99, 'images/conditioner-notYourMothers.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Not Your Mother’s Curl Talk 3in1 Conditioner');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Shea Moisture Raw Shea Butter Conditioner', 'Restorative Conditioner with Sea Kelp & Argan Oil', 33.50, 'images/conditioner-sheaMoisture-restorative.png', 1
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shea Moisture Raw Shea Butter Conditioner');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Fix My Curls Curl Quenching Hair Butter', 'This butter is recommended for wavy and curly hair that needs hydration.', 22.99, 'images/hair-butter.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Fix My Curls Curl Quenching Hair Butter');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'KeraCare CurlEssence Moisturizing Coco Water', 'Adds texture and volume', 23.99, 'images/coco-water.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'KeraCare CurlEssence Moisturizing Coco Water');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Moistful Curl Ultimate Hold Gel', 'Curl defining gel provides long-lasting hold and flexibility', 21.50, 'images/moistfulCurl-gel.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Moistful Curl Ultimate Hold Gel');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'As I Am Rosemary Styling Mousse', 'Provides flexible hold without the feeling of stiffness. Leaves hair soft, shiny and hydrated.', 15.50, 'images/mousse-AsIAm.png', 2
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'As I Am Rosemary Styling Mousse');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Denman Styling Brush D4', 'Hairdressing brush with 9 rows of nylon bristles.', 10.99, 'images/denmanD4.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Denman Styling Brush D4');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Bounce Curl Define EdgeLift Brush Pink', 'This innovative 5-in-1 brush creates frizz-free and defined curls, while the handle ensures precise hair separation and can also be used as a heat-free styling tool.', 12.99, 'images/bounceCurl-brush.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Bounce Curl Define EdgeLift Brush Pink');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Silk Pillowcase', 'Protects hair and skin overnight', 25.00, 'images/silk_pillowcase.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Silk Pillowcase');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Hair Tie', 'No-damage hair tie', 6.99, 'images/hairtie.jpeg', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Hair Tie');

INSERT INTO product (name, description, price, image, category_id)
SELECT 'Diffuser', 'Made of flexible, heat-resistant material (silicone), it fits perfectly with almost all hair dryers', 11.99, 'images/diffuser.png', 3
    WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Diffuser');