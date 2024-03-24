--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-03-22 09:54:40 UTC

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

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 24589)
-- Name: pizza_recipe; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pizza_recipe (
    recipe_id integer NOT NULL,
    quantity integer NOT NULL,
    weight integer NOT NULL,
    hydration integer NOT NULL,
    temperature integer NOT NULL,
    created date NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.pizza_recipe OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24588)
-- Name: pizza_recipe_recipe_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pizza_recipe_recipe_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pizza_recipe_recipe_id_seq OWNER TO postgres;

--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 217
-- Name: pizza_recipe_recipe_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pizza_recipe_recipe_id_seq OWNED BY public.pizza_recipe.recipe_id;


--
-- TOC entry 3206 (class 2604 OID 24592)
-- Name: pizza_recipe recipe_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pizza_recipe ALTER COLUMN recipe_id SET DEFAULT nextval('public.pizza_recipe_recipe_id_seq'::regclass);


--
-- TOC entry 3354 (class 0 OID 24589)
-- Dependencies: 218
-- Data for Name: pizza_recipe; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pizza_recipe VALUES (15, 4, 285, 70, 22, '2024-03-21', 1);
INSERT INTO public.pizza_recipe VALUES (16, 7, 261, 67, 23, '2024-03-21', 1);
INSERT INTO public.pizza_recipe VALUES (21, 6, 285, 70, 22, '2024-03-21', 1);
INSERT INTO public.pizza_recipe VALUES (22, 4, 285, 70, 22, '2024-03-22', 1);


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 217
-- Name: pizza_recipe_recipe_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pizza_recipe_recipe_id_seq', 23, true);


--
-- TOC entry 3208 (class 2606 OID 24594)
-- Name: pizza_recipe pizza_recipe_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pizza_recipe
    ADD CONSTRAINT pizza_recipe_pkey PRIMARY KEY (recipe_id);


--
-- TOC entry 3209 (class 2606 OID 24595)
-- Name: pizza_recipe fk_users_pizza_recipe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pizza_recipe
    ADD CONSTRAINT fk_users_pizza_recipe FOREIGN KEY (user_id) REFERENCES public.users(user_id) NOT VALID;


-- Completed on 2024-03-22 09:54:40 UTC

--
-- PostgreSQL database dump complete
--

