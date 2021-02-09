--DISHES--
INSERT INTO "public"."dishes" ("name")
VALUES ('Hamburger'), ('Hot Dog'), ('Tomato Salad'),
('Breakfast Burrito'), ('Scrambled Eggs');

--INGREDIENTS--
INSERT INTO "public"."ingredients" ("stock", "unit")
VALUES ('Ground Chuck', 60.00, 'lbs'), ('Buns', 300.00, 'loaves'), ('Tomatoes', 30.00, 'lbs'),
('Onions', 45.00, 'lbs'), ('Hot Dog Franks', 25.00, 'lbs'), ('Ketchup', 200.00, 'fl.Oz.'),
('Mustard', 160.00, 'fl.Oz.'), ('Eggs', 500.00, 'eggs'),
('Olive Oil', 300.00, 'fl.Oz.'),( 'Vinegar', 150.00, 'fl.Oz.');

--MENUS--
INSERT INTO "public"."menus" ("name", "startTime", "endTime")
VALUES ('Breakfast', '06:00:00-05', '11:00:00-05'), ('Lunch', '11:01:00-05', '04:00:00-05'),
('Dinner', '04:01:00-05', '10:00:00-05'), ('Specials', NULL, NULL), ;

--MENU DISHES--
INSERT INTO "public"."menuDishes" ("menuId", "dishId", "price")
VALUES (1, 4, 8.50), (1, 5, 4.00), (2, 1, 9.85), (2, 2, 6.50),
(3, 1, 10.85), (3, 2, 7.50), (4, 3, 5.00);

--RECIPES--
INSERT INTO "public"."recipes" ("name", "dishId", "instructions")
VALUES ('flat top burger', 1, 'Grill burger on flat top, Put on a bun with a slice of tomato and onion and ketchup and mustard'), 
('flat top hot dog', 2, 'Grill hot dog on flat top, Put on a bun with mustard and ketchup'), 
('tomato salad', 3, 'Slice onion and tomatoes, Dress with olive oil and vinegar'), 
('breakfast burrito', 4, 'Place scrambled eggs into a steamed tortilla, Wrap'), 
('scrambled eggs', 5, 'crack 3 eggs on flat top, mix around until they have congealed');

--RECIPE INGREDIENTS--
INSERT INTO "public"."recipeIngredients" ("recipeId", "ingredientId", "quantity", "unit")
VALUES (1, 1, 6.00, 'oz'), (1, 2, 1.00, 'loaf'), (1, 3, 1.00, 'slice'),
(1, 4, 1.00, 'slice'), (1, 6, 2.00, 'fl.oz.'), (1, 7, 2.00, 'fl.oz'),
(2, 5, 1.00, 'frank'), (2, 6, 2.00, 'fl.oz.'), (2, 7, 2.00, 'fl.oz'),
(3, 3, 2.00, 'tomato'), (3, 4, 1.00, 'onion'), (3, 9, 4.00, 'fl.oz'),
(3, 10, 2.00, 'fl.oz.');

-- --SUPPLIERS--
INSERT INTO "public"."suppliers" ("name")
VALUES ('Tom''s Tomato Farm'), ('Chuck''s Beef Factory'), ('Sally''s Restaurant Supply');

-- --SUPPLIER INGREDIENTS--
INSERT INTO "public"."supplierIngredients" ("supplierId", "ingredientId", "qtyAvailable", "unit", "unitCost")
VALUES ('1', '3', '300', 'lbs', '.80'), ('1', '4', '100', 'lbs', '.60'),
('2', '1', '3000', 'lbs', '3.00'), ('2', '5','1000', 'lbs', '1.50'),
('3', '6', NULL, NULL, '0.30'), ('3','7', NULL, NULL, '0.25'), ('3','2', '3000', 'loaves', '0.50');

--TABLETOPS--
INSERT INTO "public"."tabletops" ("seatCount")
VALUES ('4'), ('4'), ('2'), ('8');

 --ORDERS--
INSERT INTO "public"."orders" ("tabletopId")
VALUES ('13'), ('14'), ('15');

--ORDER DISHES--
INSERT INTO "public"."orderDishes" ("orderId", "dishId", "menuId", "quantity")
VALUES ('13', '1', '2', '3'), ('13', '3', '4', '1'), ('14', '2', '3', '2'), ('15', '4', '1', '1');


SELECT * FROM public."orders" AS "o"
LEFT JOIN public."orderDishes" AS "od" ON "o"."id" = "od"."orderId"
LEFT JOIN public."dishes" AS "d" ON "od"."dishId" = "d"."id";
