INSERT INTO "recipes" ("name", "dishId", "instructions")
VALUES ('fried burger', '1', 'throw the burger into the deep fryer, serve on a bun with ketchup and mustard'),
('fried hot dog', '2', 'throw the frank into the deep fryer, serve on a bun with ketchup and mustard'),
('raw hamburger', '1', 'don''t cook it just serve it');

INSERT INTO "supplierIngredients"
("supplierId", "ingredientId", "qtyAvailable", "unit", "unitCost")
VALUES ('10', '8', '2000', 'eggs', '.30'), ('12', '9', '100', 'gallons', '1.30'),
('12', '10', '200', 'gallons', '2.00'), ('11', '8', '300', 'eggs', '.20');

-- UPDATE "supplierIngredients" SET 

SELECT *
FROM "menus" AS mns
LEFT JOIN "menuDishes" AS md ON d."id" = md."dishId"
LEFT JOIN "menus" AS "m" ON md."menuId" = "m"."id";

-- SELECT *
-- FROM "menus" AS me
-- LEFT JOIN "menuDishes" AS md ON me."id" = md."menuId"
-- LEFT JOIN "dishes" AS "d" ON md."dishId" = "d"."id";
