DELETE FROM "orderDishes";
DELETE FROM "menuDishes";
DELETE FROM "supplierIngredients";
DELETE FROM "recipeIngredients";
DELETE FROM "recipes";
DELETE FROM "orders";
DELETE FROM "ingredients";
DELETE FROM "dishes";
DELETE FROM "menus";
DELETE FROM "suppliers";
DELETE FROM "tabletops";

ALTER SEQUENCE dishes_id_seq RESTART WITH 1;
ALTER SEQUENCE ingredients_id_seq RESTART WITH 1;
ALTER SEQUENCE menus_id_seq RESTART WITH 1;
ALTER SEQUENCE orders_id_seq RESTART WITH 1;
ALTER SEQUENCE recipes_id_seq RESTART WITH 1;
ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;
ALTER SEQUENCE tabletops_id_seq RESTART WITH 1;