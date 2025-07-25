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


INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'Cammille Rose Coconut Water Curl Cleanse',
    'Gentle hydrating shampoo without sulfates',
    'The best hydration on earth comes from nature! Camille Rose Coconut Water Curl Cleanse, a sulfate-free hydrating shampoo is formulated with natural, hydrating ingredients that quench the thirst of your locks. Coconut water and oil nourish and hydrate both your scalp and curls. Your hair will thank you! Your curls will be shiny, hydrated and full of life, silky and frizz-free! \n
 <br>APPLICATION METHOD <br>
Wet your hair thoroughly with warm water, apply the shampoo to the entire length of your hair. With your fingertips or palms, massage until your hair and scalp are clean. Continue your routine with the Coconut Water treatment.',
    14.99,
    100,
    'images/camille-coconut-cleanse.png',
    1
WHERE NOT EXISTS (
    SELECT 1 FROM product WHERE name = 'Cammille Rose Coconut Water Curl Cleanse'
);
INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'Boucleme Curls Redefined Curl Conditioner',
    'Moisturizing conditioner for curly hair',
    'This conditioner penetrates the hair cuticle, leaving curls super hydrated.

Fortifying and protective, this nourishing blend helps protect hair from daily aggressions. Each strand will remain smooth, soft and hydrated, and curls will be bouncy and well-defined. It does not weigh down the hair.

Perfect for all types of curls.

97% ingredients from natural sources.

<br>APPLICATION METHOD<br>

After shampooing, apply generously to damp hair. Distribute with fingers. Leave-in conditioner for coarse hair or rinse for finer, wavy hair. Finish with Bouclème Curl Defining Gel.',
    34.99,
    200,
    'images/conditioner-boucleme.png',
    1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Boucleme Curls Redefined Curl Conditioner');

INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'As I Am Restore & Repair Masque',
    'Deep nourishing and hidrating mask',
    'Revitalize dry hair with a therapeutic mask. As I Am Restore & Repair Moisturizing Masque is infused with Jamaican Castor Oil, Vitamins C, E and Ceramides to hydrate, strengthen, detangle and add shine to curls. Leaves hair soft, reduces breakage and hydrates dry scalp.

For curly and very curly hair (type 3 and 4).

Increases hair hydration levels by 8% after a single application.

36% easier combing.

Makes curly and very curly hair 49% easier to detangle

APPLICATION METHOD

Apply a generous amount of the mask to wet, freshly washed hair and distribute from roots to ends. Leave on for 15-20 minutes, then rinse thoroughly with water.',
    39.99,
    150,
    'images/mask-AsIAm-Restore&Repair.png',
    1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'As I Am Restore & Repair Masque');

INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'As I Am Restore & Repair Leave-in',
    'Leave-in treatment that makes detangling easier',
    'As I Am Restore & Repair Leave-in Conditioner makes detangling wet hair easier while providing moisture and shine to the hair fibers. It should be used before applying styling products to avoid white marks. For even more hydration, combine the leave-in conditioner with As I Am Restore & Repair Water.

For curly and very curly hair (type 3 and 4).

Increases hair moisture by 42% after a single application.

Makes combing 40% easier.

APPLICATION METHOD

Apply an appropriate amount of product to clean, damp hair. Distribute evenly throughout the length. Detangle. Continue routine with As I Am Restore & Repair Curling Creme or As I Am Restore & Repair Smoothie.',
    11.99,
    175,
    'images/leavein-AsIAm-Restore&Repair.png',
    1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'As I Am Restore & Repair Leave-in');

INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'Garnier Fructis Hydrating Aloe Vera Shampoo',
    'Hydrating shampoo with aloe vera extract',
    'Garnier Fructis Aloe Vera Hair Food Moisturizing Shampoo effectively and delicately cleanses the scalp, deeply washes the hair and restores its natural beauty. Its formula enriched with aloe vera extracts helps moisturize dry hair and provides it with the indispensable nutrition to be beautiful and healthy. Coconut oil provides the necessary hydration, facilitates subsequent detangling and styling, smoothes and prevents frizz, protects hair against damage. Its rich and light texture is easily absorbed. It is a product with a vegan formula, with 98% natural substances, without silicone or synthetic dyes.

Recyclable packaging.

APPLICATION METHOD

Apply an appropriate amount of shampoo to wet hair. Shampoo hair until foam forms, then rinse thoroughly. Repeat step if necessary.',
    8.99,
    230,
    'images/shampoo-fructis-aloevera.png',
    1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Garnier Fructis Hydrating Aloe Vera Shampoo');

INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'Mielle Rosemary Mint Shampoo',
    'Instantly strengthen and cleanse hair',
    'Instantly strengthen and cleanse dry, weak, and dull hair with this scalp-tingling rosemary and mint shampoo from Mielle! It was created to gently cleanse and deliver a boost of nutrients and intensely hydrating ingredients to your hair, leaving strands smooth and manageable!

Free from: parabens, sulfates, paraffin, mineral oils, DEA.

The product has not been tested on animals.

APPLICATION METHOD

Apply a small amount of shampoo to your palms and massage into wet hair until a rich lather forms. Rinse thoroughly. Repeat step if necessary and follow with the Rosemary and Mint Fortifying Mask.',
    31.50,
    75,
    'images/shampoo_mielle-mint.png',
    1

WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Mielle Rosemary Mint Shampoo');

INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'Innersense Clarity Hairbath Shampoo',
    'Clean, gentle, fragrance-free hypoallergenic shampoo',
    'Clarity is a clean, gentle, fragrance-free hypoallergenic shampoo. It provides an extremely gentle cleanse for the hair and scalp of those with sensitive skin. Formulated with effective plant-derived ingredients such as yarrow leaf and Swertia Japonica, it has anti-inflammatory properties for a calming and soothing effect on the skin. Chufa milk, a plant extract rich in strengthening amino acids, moisturizes, promotes elasticity, shine and brings antioxidant benefits to the hair and scalp.

Clarity Hairbath works for all hair types and textures. It is specially designed for people with allergies and sensitivities, free of soy, lactose, gluten and essential oils.

APPLICATION METHOD

Apply a small amount of shampoo to your hands and work into a light lather. Using your fingertips, massage the lather into your scalp and hair. Rinse thoroughly.',
    32.00,
    210,
    'images/shampoo-innersense-clarity.png',
    1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Innersense Clarity Hairbath Shampoo');

INSERT INTO product (name, short_description, long_description, price,quantity, image, category_id)
SELECT
    'Not Your Mother’s Curl Talk 3in1 Conditioner',
    'Conditioner to repair damaged hair',
    'Caring for your curls can manifest differently depending on the day. That''s why Not Your Mother''s 3-in-1 Conditioner is designed to hydrate, yet is flexible enough to be used as a leave-in conditioner, a classic conditioner, or a wash-in conditioner. It seals in moisture and restores curl elasticity. It''s formulated with Rice Curl Complex - a blend of rice, amino acids, and enzymes to hydrate, protect, and strengthen hair.

Does not contain: sulfates, silicones, parabens, phthalates, alcohols, gluten, artificial colors.

It has not been tested on animals.

Suitable for dyed hair.

APPLICATION METHOD

Distribute a generous amount of conditioner through wet or dry hair. Use as desired: leave-in conditioner, rinse-out conditioner, or wash-in conditioner.',
    35.99,
    100,
    'images/conditioner-notYourMothers.png',
    1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Not Your Mother’s Curl Talk 3in1 Conditioner');
INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Shea Moisture Raw Shea Butter Conditioner', 'Restorative Conditioner with Sea Kelp & Argan Oil',
       'Shea Moisture Shea Butter Repair Conditioner hydrates and restores vitality to damaged hair, making it easier to comb. It is perfect for chemically or thermally treated hair that is in transition. It can be used as a leave-in conditioner.

       APPLICATION METHOD

       After washing with Shea Butter Moisture Retention Shampoo, apply conditioner from roots to ends. Leave on for 3 minutes, then rinse thoroughly. For deeper hydration, leave on for 15 minutes. As a leave-in, apply a small amount to damp hair and style.',
       33.50, 140,'images/conditioner-sheaMoisture-restorative.png', 1
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Shea Moisture Raw Shea Butter Conditioner');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Fix My Curls Curl Quenching Hair Butter', 'This butter is recommended for wavy and curly hair that needs hydration.',
       'Fix My Curls Curl Quenching Hair Butter was created for dry, dehydrated hair. It is a delicious blend of light butters such as tucuma, to which are added strengthening ingredients such as flaxseed extract. It is suitable for all hair types and can be used as a leave-in conditioner. Leaves hair smooth and easy to style, defines and holds.

       Almond and mango flavor.

       Vegan product.

       Does not contain: parabens, silicones, wax, mineral oils

       APPLICATION METHOD

       Apply butter to very wet or damp hair to hydrate and seal hair. Comb each section of hair with your fingers, then smooth it out with your hands together (praying hands).

       You can also use a brush to get even more defined curls.

       Do not apply the product to the roots, to enjoy the volume. This butter gives great results used as a sole styling product. Can be combined with Curl Quenching Gelly for fabulous curls.',
       22.99,190, 'images/hair-butter.png', 2
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Fix My Curls Curl Quenching Hair Butter');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'KeraCare CurlEssence Moisturizing Coco Water', 'Adds texture and volume',
       'It''s better than water! Formulated with Jamaican Castor Oil and Coconut Oil, KeraCare''s CurlEssence Coco Water Hydrating Spray seals in moisture, leaving your hair soft, manageable and healthy. If you''re looking to add a little moisture to your dry hair, this revolutionary spray is perfect. Infused with emollients and humectants, it delivers even more moisture and shine with every use and when you style your hair. Combats frizz and defines curls.

       BENEFITS

       Rehydrates and revitalizes hair
       Helps prevent hair frizz
       Improves curl definition
       Gives a natural shine to the hair
       Ideal for hair type 3-4.

       APPLICATION METHOD

       Use instead of plain water. Shake the container well before use. Spray to re-moisten hair while styling. The product is also beneficial for revitalizing curls (refreshing).',
       23.99,170, 'images/coco-water.png', 2
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'KeraCare CurlEssence Moisturizing Coco Water');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Moistful Curl Ultimate Hold Gel', 'Curl defining gel provides long-lasting hold and flexibility',
       'Moistful Curl Ultimate Hold curl defining gel provides long-lasting hold and flexibility. It controls frizz, giving a natural look. It has a very light formula that doesn''t weigh down the hair or leave it sticky.

       APPLICATION METHOD

       For fine hair , apply gel to wet, pre-combed hair. Squeeze strands into a fist, let hair air dry or use a diffuser.

       For thick hair , apply a curling cream or leave-in conditioner first, then the gel. Scrunch the strands into a fist, let the hair air dry or use a diffuser.',
       21.50,120, 'images/moistfulCurl-gel.png', 2
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Moistful Curl Ultimate Hold Gel');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'As I Am Rosemary Styling Mousse', 'Provides flexible hold without the feeling of stiffness. Leaves hair soft, shiny and hydrated.',
       'As I Am Rosemary Styling Mousse provides flexible hold without the feeling of stiffness. Leaves hair soft, shiny and hydrated. Contains amino acids that strengthen the hair shaft. Has a soothing aroma of rosemary oil and mint. The container is equipped with a pump dispenser for easy application. Helps combat frizz.

       It offers a double benefit: it strengthens hair by up to 30% and moisturizes it to the same extent.

       Key ingredients:

       Rosemary Oil : Protects against damage, improves blood flow as a vasodilator. A 10% concentration of Rosemary oil is more effective than 2% minoxidil.

       Peppermint Oil: Combats itchy scalp. Provides a natural fragrance and a cooling sensation to the scalp.

       Melatonin: Improves hair thickness and density. Clinical studies show that melatonin is a preventive treatment for alopecia and hair loss, which delays hair aging.

       Copper Tripeptide: Strengthens hair follicles, improves hair elasticity and stimulates hair growth.

       APPLICATION METHOD

       Shampoo and apply conditioner to hair. Shake the container containing the foam well. Use 1-2 pumps to start, then apply more if needed. Apply the foam to hair and distribute it evenly along the entire length with a comb. Style your hair as desired. Dry your hair.',
       15.50,130, 'images/mousse-AsIAm.png', 2
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'As I Am Rosemary Styling Mousse');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Denman Styling Brush D4', 'Hairdressing brush with 9 rows of nylon bristles.',
       'Denman Styling D4 Nylon Bristle Hair Brush  is  considered by specialists to be the most famous styling brush in the world.

       It has 9 rows of nylon bristles with rounded ends to avoid irritating the scalp.
       Ensures maximum control during blow drying.
       Helps detangle wet hair and dry quickly.
       It is ideal for defining and creating curls on curly hair.
       Antistatic rubber base.
       The lightweight, perfectly balanced, teardrop-shaped handle, with a longer and more rounded shape,  offers a good grip.',
       10.99,0, 'images/denmanD4.png', 3
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Denman Styling Brush D4');

INSERT INTO product (name, short_description,long_description, price, quantity, image, category_id)
SELECT 'Bounce Curl Define EdgeLift Brush Pink', 'This innovative 5-in-1 brush creates frizz-free and defined curls, while the handle ensures precise hair separation and can also be used as a heat-free styling tool.',
       'Bounce Curl’s Define EdgeLift Brush offers a revolutionary styling experience with its custom, patented design. Crafted from PLA materials, it’s designed to create smooth, medium-sized clumped curls while offering precision parting and heatless curling. Perfect for wavy, curly, and coily hair, it delivers defined curls with ease.',
       12.99,80, 'images/bounceCurl-brush.png', 3
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Bounce Curl Define EdgeLift Brush Pink');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Silk Pillowcase', 'Protects hair and skin overnight',
       'Protect your hair and skin at night with this satin pillowcase! Many people don''t realize how important the pillow they sleep on is.

       Satin, thanks to its glossy surface, prevents hair frizz and split ends.

       Unlike cotton, which dries out your hair, satin keeps it hydrated. Plus, this shiny material prevents wrinkles, protecting your skin throughout the night.

       What is satin? It is a dense fabric characterized by a glossy surface and shine in the light. It is applied to silk, bamboo, modal, cotton or synthetic fibers. Therefore, the term refers to the finish of the fabric and not to the type of fiber, content or density.

       Material: satin made from synthetic fibers (polyester)
       . Wash at 40 degrees.',
       25.00,40, 'images/silk_pillowcase.png', 3
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Silk Pillowcase');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Hair Tie', 'No-damage hair tie',
       'Unlike traditional hair ties, silk scrunchies prevent friction and tension on your hair due to their smooth material, preventing breakage. The Silk Scrunchie from Natural Hysteria is gentle on your hair, helping it stay hydrated, strong and healthy. Now you can tie your curls in a pineapple at night without worrying about waking up with messy hair in the morning.

       MATERIAL

       100% natural silk',
       6.99, 40,'images/hairtie.jpeg', 3
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Hair Tie');

INSERT INTO product (name, short_description,long_description, price,quantity, image, category_id)
SELECT 'Diffuser', 'Made of flexible, heat-resistant material (silicone), it fits perfectly with almost all hair dryers',
       'It is the ideal accessory for drying wavy or curly hair. Made of flexible, heat-resistant material (silicone), it fits perfectly with almost all hair dryers. It is also ideal for traveling, because it can be folded, so it takes up little space.

       The opening diameter is 5.4 cm and fits hair dryers with a barrel up to 4.7 cm. Make sure you measure your hair dryer correctly before ordering the diffuser!

       HOW TO USE

       Apply your favorite styling products to very damp hair and let it dry partially (5-10 minutes for best results) before using the diffuser. Set the dryer to medium heat, cool setting. Be sure not to use too much heat as this will cause frizz. The small teeth will help separate and lift your hair, allowing it to dry quickly and limiting your exposure to heat. Tilt your head to one side or the front, carefully placing sections of hair in the diffuser. Move the diffuser towards the roots to dry.',
       11.99,30,'images/diffuser.png', 3
WHERE NOT EXISTS (SELECT 1 FROM product WHERE name = 'Diffuser');
