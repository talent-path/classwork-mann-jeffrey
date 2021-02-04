--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-02-04 09:18:06 EST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "restaurant";
--
-- TOC entry 3343 (class 1262 OID 16427)
-- Name: restaurant; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "restaurant" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';


ALTER DATABASE "restaurant" OWNER TO "postgres";

\connect "restaurant"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = "heap";

--
-- TOC entry 214 (class 1259 OID 16565)
-- Name: RecipeIngredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."RecipeIngredients" (
    "recipeId" integer NOT NULL,
    "ingredientId" integer NOT NULL,
    "quantity" numeric(6,2) NOT NULL,
    "unit" character varying(50) NOT NULL
);


ALTER TABLE "public"."RecipeIngredients" OWNER TO "postgres";

--
-- TOC entry 203 (class 1259 OID 16505)
-- Name: dishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."dishes" (
    "id" integer NOT NULL,
    "name" character varying(50) NOT NULL
);


ALTER TABLE "public"."dishes" OWNER TO "postgres";

--
-- TOC entry 202 (class 1259 OID 16503)
-- Name: dishes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."dishes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."dishes_id_seq" OWNER TO "postgres";

--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 202
-- Name: dishes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."dishes_id_seq" OWNED BY "public"."dishes"."id";


--
-- TOC entry 205 (class 1259 OID 16514)
-- Name: ingredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."ingredients" (
    "id" integer NOT NULL,
    "name" character varying(50) NOT NULL,
    "stock" numeric(6,2) NOT NULL,
    "unit" character varying(50) NOT NULL
);


ALTER TABLE "public"."ingredients" OWNER TO "postgres";

--
-- TOC entry 204 (class 1259 OID 16512)
-- Name: ingredients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."ingredients_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."ingredients_id_seq" OWNER TO "postgres";

--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 204
-- Name: ingredients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."ingredients_id_seq" OWNED BY "public"."ingredients"."id";


--
-- TOC entry 215 (class 1259 OID 16580)
-- Name: menuDishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."menuDishes" (
    "menuId" integer NOT NULL,
    "dishId" integer NOT NULL,
    "price" numeric(6,2) NOT NULL
);


ALTER TABLE "public"."menuDishes" OWNER TO "postgres";

--
-- TOC entry 201 (class 1259 OID 16497)
-- Name: menus; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."menus" (
    "id" integer NOT NULL,
    "name" character varying(50) NOT NULL,
    "startTime" time with time zone,
    "endTime" time with time zone
);


ALTER TABLE "public"."menus" OWNER TO "postgres";

--
-- TOC entry 200 (class 1259 OID 16495)
-- Name: menus_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."menus_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."menus_id_seq" OWNER TO "postgres";

--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 200
-- Name: menus_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."menus_id_seq" OWNED BY "public"."menus"."id";


--
-- TOC entry 217 (class 1259 OID 16610)
-- Name: orderDishes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."orderDishes" (
    "orderId" integer NOT NULL,
    "dishId" integer NOT NULL,
    "menuId" integer NOT NULL,
    "quantity" numeric(4,1)
);


ALTER TABLE "public"."orderDishes" OWNER TO "postgres";

--
-- TOC entry 213 (class 1259 OID 16554)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."orders" (
    "id" integer NOT NULL,
    "tabletopId" integer NOT NULL
);


ALTER TABLE "public"."orders" OWNER TO "postgres";

--
-- TOC entry 212 (class 1259 OID 16552)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."orders_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."orders_id_seq" OWNER TO "postgres";

--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 212
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."orders_id_seq" OWNED BY "public"."orders"."id";


--
-- TOC entry 209 (class 1259 OID 16530)
-- Name: recipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."recipes" (
    "id" integer NOT NULL,
    "name" character varying(50) NOT NULL,
    "dishId" integer NOT NULL,
    "instructions" "text" NOT NULL
);


ALTER TABLE "public"."recipes" OWNER TO "postgres";

--
-- TOC entry 208 (class 1259 OID 16528)
-- Name: recipes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."recipes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."recipes_id_seq" OWNER TO "postgres";

--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 208
-- Name: recipes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."recipes_id_seq" OWNED BY "public"."recipes"."id";


--
-- TOC entry 216 (class 1259 OID 16595)
-- Name: supplierIngredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."supplierIngredients" (
    "supplierId" integer NOT NULL,
    "ingredientId" integer NOT NULL,
    "qtyAvailable" numeric(7,2),
    "unit" character varying(50),
    "unitCost" numeric(6,2) NOT NULL
);


ALTER TABLE "public"."supplierIngredients" OWNER TO "postgres";

--
-- TOC entry 207 (class 1259 OID 16522)
-- Name: suppliers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."suppliers" (
    "id" integer NOT NULL,
    "name" character varying(50) NOT NULL
);


ALTER TABLE "public"."suppliers" OWNER TO "postgres";

--
-- TOC entry 206 (class 1259 OID 16520)
-- Name: suppliers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."suppliers_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."suppliers_id_seq" OWNER TO "postgres";

--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 206
-- Name: suppliers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."suppliers_id_seq" OWNED BY "public"."suppliers"."id";


--
-- TOC entry 211 (class 1259 OID 16546)
-- Name: tabletops; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE "public"."tabletops" (
    "id" integer NOT NULL,
    "seatCount" smallint
);


ALTER TABLE "public"."tabletops" OWNER TO "postgres";

--
-- TOC entry 210 (class 1259 OID 16544)
-- Name: tabletops_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "public"."tabletops_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "public"."tabletops_id_seq" OWNER TO "postgres";

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 210
-- Name: tabletops_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "public"."tabletops_id_seq" OWNED BY "public"."tabletops"."id";


--
-- TOC entry 3169 (class 2604 OID 16508)
-- Name: dishes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."dishes" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."dishes_id_seq"'::"regclass");


--
-- TOC entry 3170 (class 2604 OID 16517)
-- Name: ingredients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."ingredients" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."ingredients_id_seq"'::"regclass");


--
-- TOC entry 3168 (class 2604 OID 16500)
-- Name: menus id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."menus" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."menus_id_seq"'::"regclass");


--
-- TOC entry 3174 (class 2604 OID 16557)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orders" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."orders_id_seq"'::"regclass");


--
-- TOC entry 3172 (class 2604 OID 16533)
-- Name: recipes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."recipes" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."recipes_id_seq"'::"regclass");


--
-- TOC entry 3171 (class 2604 OID 16525)
-- Name: suppliers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."suppliers" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."suppliers_id_seq"'::"regclass");


--
-- TOC entry 3173 (class 2604 OID 16549)
-- Name: tabletops id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."tabletops" ALTER COLUMN "id" SET DEFAULT "nextval"('"public"."tabletops_id_seq"'::"regclass");


--
-- TOC entry 3190 (class 2606 OID 16569)
-- Name: RecipeIngredients RecipeIngredients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."RecipeIngredients"
    ADD CONSTRAINT "RecipeIngredients_pkey" PRIMARY KEY ("recipeId", "ingredientId");


--
-- TOC entry 3178 (class 2606 OID 16510)
-- Name: dishes dishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."dishes"
    ADD CONSTRAINT "dishes_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3180 (class 2606 OID 16519)
-- Name: ingredients ingredients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."ingredients"
    ADD CONSTRAINT "ingredients_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3192 (class 2606 OID 16584)
-- Name: menuDishes menuDishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."menuDishes"
    ADD CONSTRAINT "menuDishes_pkey" PRIMARY KEY ("menuId", "dishId");


--
-- TOC entry 3176 (class 2606 OID 16502)
-- Name: menus menus_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."menus"
    ADD CONSTRAINT "menus_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3196 (class 2606 OID 16614)
-- Name: orderDishes orderDishes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orderDishes"
    ADD CONSTRAINT "orderDishes_pkey" PRIMARY KEY ("orderId", "dishId", "menuId");


--
-- TOC entry 3188 (class 2606 OID 16559)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orders"
    ADD CONSTRAINT "orders_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3184 (class 2606 OID 16538)
-- Name: recipes recipes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."recipes"
    ADD CONSTRAINT "recipes_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3194 (class 2606 OID 16599)
-- Name: supplierIngredients supplierIngredients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."supplierIngredients"
    ADD CONSTRAINT "supplierIngredients_pkey" PRIMARY KEY ("supplierId", "ingredientId");


--
-- TOC entry 3182 (class 2606 OID 16527)
-- Name: suppliers suppliers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."suppliers"
    ADD CONSTRAINT "suppliers_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3186 (class 2606 OID 16551)
-- Name: tabletops tabletops_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."tabletops"
    ADD CONSTRAINT "tabletops_pkey" PRIMARY KEY ("id");


--
-- TOC entry 3200 (class 2606 OID 16575)
-- Name: RecipeIngredients RecipeIngredients_ingredientId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."RecipeIngredients"
    ADD CONSTRAINT "RecipeIngredients_ingredientId_fkey" FOREIGN KEY ("ingredientId") REFERENCES "public"."ingredients"("id");


--
-- TOC entry 3199 (class 2606 OID 16570)
-- Name: RecipeIngredients RecipeIngredients_recipeId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."RecipeIngredients"
    ADD CONSTRAINT "RecipeIngredients_recipeId_fkey" FOREIGN KEY ("recipeId") REFERENCES "public"."recipes"("id");


--
-- TOC entry 3202 (class 2606 OID 16590)
-- Name: menuDishes menuDishes_dishId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."menuDishes"
    ADD CONSTRAINT "menuDishes_dishId_fkey" FOREIGN KEY ("dishId") REFERENCES "public"."dishes"("id");


--
-- TOC entry 3201 (class 2606 OID 16585)
-- Name: menuDishes menuDishes_menuId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."menuDishes"
    ADD CONSTRAINT "menuDishes_menuId_fkey" FOREIGN KEY ("menuId") REFERENCES "public"."menus"("id");


--
-- TOC entry 3206 (class 2606 OID 16620)
-- Name: orderDishes orderDishes_dishId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orderDishes"
    ADD CONSTRAINT "orderDishes_dishId_fkey" FOREIGN KEY ("dishId") REFERENCES "public"."dishes"("id");


--
-- TOC entry 3207 (class 2606 OID 16625)
-- Name: orderDishes orderDishes_menuId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orderDishes"
    ADD CONSTRAINT "orderDishes_menuId_fkey" FOREIGN KEY ("menuId") REFERENCES "public"."menus"("id");


--
-- TOC entry 3205 (class 2606 OID 16615)
-- Name: orderDishes orderDishes_orderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orderDishes"
    ADD CONSTRAINT "orderDishes_orderId_fkey" FOREIGN KEY ("orderId") REFERENCES "public"."orders"("id");


--
-- TOC entry 3198 (class 2606 OID 16560)
-- Name: orders orders_tabletopId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."orders"
    ADD CONSTRAINT "orders_tabletopId_fkey" FOREIGN KEY ("tabletopId") REFERENCES "public"."tabletops"("id");


--
-- TOC entry 3197 (class 2606 OID 16539)
-- Name: recipes recipes_dishId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."recipes"
    ADD CONSTRAINT "recipes_dishId_fkey" FOREIGN KEY ("dishId") REFERENCES "public"."dishes"("id");


--
-- TOC entry 3204 (class 2606 OID 16605)
-- Name: supplierIngredients supplierIngredients_ingredientId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."supplierIngredients"
    ADD CONSTRAINT "supplierIngredients_ingredientId_fkey" FOREIGN KEY ("ingredientId") REFERENCES "public"."ingredients"("id");


--
-- TOC entry 3203 (class 2606 OID 16600)
-- Name: supplierIngredients supplierIngredients_supplierId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "public"."supplierIngredients"
    ADD CONSTRAINT "supplierIngredients_supplierId_fkey" FOREIGN KEY ("supplierId") REFERENCES "public"."suppliers"("id");


-- Completed on 2021-02-04 09:18:06 EST

--
-- PostgreSQL database dump complete
--

