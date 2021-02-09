SELECT * FROM "public"."dishes";

SELECT "d"."name", "i"."name", "ri"."quantity" * "si"."unitCost" AS "ingredientCost" 
FROM "public"."dishes" AS "d"
INNER JOIN "public"."recipes" AS "r" ON "d"."id" = "r"."dishId"
INNER JOIN "public"."recipeIngredients" AS "ri" ON "r"."id" = "ri"."recipeId"
INNER JOIN "public"."ingredients" AS "i" ON "ri"."ingredientId" = "i"."id"
INNER JOIN "public"."supplierIngredients" AS "si" ON "si"."ingredientId" = "i"."id";

SELECT "i"."name", MIN("si"."unitCost")
FROM "public"."dishes" AS "d"
INNER JOIN "public"."recipes" AS "r" ON "d"."id" = "r"."dishId"
INNER JOIN "public"."recipeIngredients" AS "ri" ON "r"."id" = "ri"."recipeId"
INNER JOIN "public"."ingredients" AS "i" ON "ri"."ingredientId" = "i"."id"
INNER JOIN "public"."supplierIngredients" AS "si" ON "si"."ingredientId" = "i"."id"
GROUP BY "i"."id";

SELECT *
FROM "public"."dishes" AS "d"
	INNER JOIN "public"."recipes" AS "r"
	ON "d"."id" = "r"."dishId"
	INNER JOIN "public"."recipeIngredients" AS "ri"
	ON "r"."id" = "ri"."recipeId"
	LEFT JOIN (
		SELECT * 
			FROM (
				SELECT "si"."ingredientId", min("unitCost") AS "minCost"
				FROM "supplierIngredients" AS "si"
				GROUP BY "ingredientId"
			) as t
		INNER JOIN "ingredients" AS i
		ON "i"."id" = t."ingredientId"
	) as imin
	ON "ri"."ingredientId" = "imin"."ingredientId";
	
SELECT imin."min" * ri."quantity" AS "minIngrCost"
FROM "public"."dishes" AS "d"
	INNER JOIN "public"."recipes" AS "r"
	ON "d"."id" = "r"."dishId"
	INNER JOIN "public"."recipeIngredients" AS "ri"
	ON "r"."id" = "ri"."recipeId"
	LEFT JOIN (
		SELECT * 
			FROM (
				SELECT "si"."ingredientId", min("unitCost")
				FROM "supplierIngredients" AS "si"
				GROUP BY "ingredientId"
			) as t
		INNER JOIN "ingredients" AS i
		ON "i"."id" = t."ingredientId"
	) as imin
	ON "ri"."ingredientId" = "imin"."ingredientId";

SELECT * FROM (
	SELECT "ingredientId", min("unitCost")
	AS "minCost" FROM "supplierIngredients" GROUP BY "ingredientId"
) as t
INNER JOIN "ingredients" AS i on i.id = t."ingredientId";

SELECT tc."totalCost", r2.* FROM (
	SELECT r."id" AS "recipeId", SUM(mpi."min" * ri."quantity") AS "totalCost"
		FROM "recipes" AS "r"
		LEFT JOIN "public"."recipeIngredients" AS "ri"
			ON "r"."id" = "ri"."recipeId"
		LEFT JOIN "MinPriceIngredients" as "mpi"
			ON "ri"."ingredientId" = "mpi"."ingredientId"
		GROUP BY "r"."id"
	) AS tc
	INNER JOIN "recipes" r2
		ON r2."id" = tc."recipeId";
		
SELECT tc."totalCost", r2.* FROM (
	SELECT r."id" AS "recipeId", SUM(mpi."min" * ri."quantity") AS "totalCost"
		FROM "recipes" AS "r"
		LEFT JOIN "public"."recipeIngredients" AS "ri"
			ON "r"."id" = "ri"."recipeId"
		LEFT JOIN "MinPriceIngredients" as "mpi"
			ON "ri"."ingredientId" = "mpi"."ingredientId"
		GROUP BY "r"."id"
	) AS tc
	INNER JOIN "recipes" r2
		ON r2."id" = tc."recipeId";
	