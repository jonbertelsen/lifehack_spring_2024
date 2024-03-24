--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-03-22 09:54:44 UTC

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
-- TOC entry 215 (class 1259 OID 57359)
-- Name: wardrobe_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wardrobe_category (
    category_id integer NOT NULL,
    name character varying(25) NOT NULL
);


ALTER TABLE public.wardrobe_category OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 57362)
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_category_id_seq OWNER TO postgres;

--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 216
-- Name: category_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_category_id_seq OWNED BY public.wardrobe_category.category_id;


--
-- TOC entry 217 (class 1259 OID 57363)
-- Name: wardrobe_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wardrobe_item (
    item_id integer NOT NULL,
    brand character varying(30) NOT NULL,
    color character varying(20) NOT NULL,
    price integer NOT NULL,
    size character varying(20),
    description character varying(100),
    category_id integer NOT NULL,
    users_id integer NOT NULL
);


ALTER TABLE public.wardrobe_item OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 57366)
-- Name: item_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.item_item_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.item_item_id_seq OWNER TO postgres;

--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 218
-- Name: item_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.item_item_id_seq OWNED BY public.wardrobe_item.item_id;


--
-- TOC entry 3210 (class 2604 OID 57373)
-- Name: wardrobe_category category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wardrobe_category ALTER COLUMN category_id SET DEFAULT nextval('public.category_category_id_seq'::regclass);


--
-- TOC entry 3211 (class 2604 OID 57374)
-- Name: wardrobe_item item_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wardrobe_item ALTER COLUMN item_id SET DEFAULT nextval('public.item_item_id_seq'::regclass);


--
-- TOC entry 3361 (class 0 OID 57359)
-- Dependencies: 215
-- Data for Name: wardrobe_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.wardrobe_category VALUES (1, 'Pants/Trousers');
INSERT INTO public.wardrobe_category VALUES (2, 'Jeans');
INSERT INTO public.wardrobe_category VALUES (3, 'Dress');
INSERT INTO public.wardrobe_category VALUES (4, 'Skirt');
INSERT INTO public.wardrobe_category VALUES (5, 'Shorts');
INSERT INTO public.wardrobe_category VALUES (6, 'Jacket/Coat');
INSERT INTO public.wardrobe_category VALUES (7, 'Sweater');
INSERT INTO public.wardrobe_category VALUES (8, 'Hoodie/Sweatshirt');
INSERT INTO public.wardrobe_category VALUES (9, 'Blazer');
INSERT INTO public.wardrobe_category VALUES (10, 'Dress pants');
INSERT INTO public.wardrobe_category VALUES (11, 'Activewear');
INSERT INTO public.wardrobe_category VALUES (12, 'Sleepwear');
INSERT INTO public.wardrobe_category VALUES (13, 'Underwear');
INSERT INTO public.wardrobe_category VALUES (14, 'Socks');
INSERT INTO public.wardrobe_category VALUES (15, 'Swimwear');
INSERT INTO public.wardrobe_category VALUES (16, 'Outfit');
INSERT INTO public.wardrobe_category VALUES (17, 'Accessory');
INSERT INTO public.wardrobe_category VALUES (18, 'Shoes/Boots/Sandals');
INSERT INTO public.wardrobe_category VALUES (19, 'T-shirt');
INSERT INTO public.wardrobe_category VALUES (20, 'Shirt');


--
-- TOC entry 3363 (class 0 OID 57363)
-- Dependencies: 217
-- Data for Name: wardrobe_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.wardrobe_item VALUES (3, 'prada', 'red', 1000, '32', 'srgsdgf', 1, 1);
INSERT INTO public.wardrobe_item VALUES (4, 'hm', 'green', 234, '45', 'nooo', 2, 1);
INSERT INTO public.wardrobe_item VALUES (5, 'casio', 'gold', 2345, '1', 'yooy', 5, 1);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 216
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_category_id_seq', 20, true);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 218
-- Name: item_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.item_item_id_seq', 5, true);


--
-- TOC entry 3213 (class 2606 OID 57376)
-- Name: wardrobe_category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wardrobe_category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- TOC entry 3215 (class 2606 OID 57378)
-- Name: wardrobe_item item_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wardrobe_item
    ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);


--
-- TOC entry 3216 (class 2606 OID 57383)
-- Name: wardrobe_item fk_item_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wardrobe_item
    ADD CONSTRAINT fk_item_category FOREIGN KEY (category_id) REFERENCES public.wardrobe_category(category_id) NOT VALID;


--
-- TOC entry 3217 (class 2606 OID 57388)
-- Name: wardrobe_item fk_item_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wardrobe_item
    ADD CONSTRAINT fk_item_users FOREIGN KEY (users_id) REFERENCES public.users(user_id) NOT VALID;


-- Completed on 2024-03-22 09:54:44 UTC

--
-- PostgreSQL database dump complete
--

