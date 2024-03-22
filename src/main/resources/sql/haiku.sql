--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-03-22 16:19:30 UTC

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
-- TOC entry 220 (class 1259 OID 25552)
-- Name: haiku_parts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.haiku_parts (
                                    part_id integer NOT NULL,
                                    text character varying(150) NOT NULL,
                                    is_5_syllables boolean DEFAULT true,
                                    made_by_kevin boolean DEFAULT false,
                                    about_kevin boolean DEFAULT false
);


ALTER TABLE public.haiku_parts OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 25551)
-- Name: haiku_parts_part_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.haiku_parts_part_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.haiku_parts_part_id_seq OWNER TO postgres;

--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 219
-- Name: haiku_parts_part_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.haiku_parts_part_id_seq OWNED BY public.haiku_parts.part_id;


--
-- TOC entry 3210 (class 2604 OID 25555)
-- Name: haiku_parts part_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.haiku_parts ALTER COLUMN part_id SET DEFAULT nextval('public.haiku_parts_part_id_seq'::regclass);


--
-- TOC entry 3360 (class 0 OID 25552)
-- Dependencies: 220
-- Data for Name: haiku_parts; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.haiku_parts VALUES (1, 'Jeg er rødhåret', true, true, false);
INSERT INTO public.haiku_parts VALUES (2, 'Mine pubes er røde', false, true, false);
INSERT INTO public.haiku_parts VALUES (3, 'Rouvis mor er bis', true, true, false);
INSERT INTO public.haiku_parts VALUES (4, 'Skud ud Fred2s far', true, false, false);
INSERT INTO public.haiku_parts VALUES (5, 'Viden er uendelig', false, false, false);
INSERT INTO public.haiku_parts VALUES (6, 'Gustav føler alt', true, false, false);
INSERT INTO public.haiku_parts VALUES (7, 'This ginger horny', true, false, true);
INSERT INTO public.haiku_parts VALUES (8, 'Rouvis mom should watch her step', false, false, true);
INSERT INTO public.haiku_parts VALUES (9, 'The most ginger boy', true, false, true);
INSERT INTO public.haiku_parts VALUES (10, 'Hoved rødt som en tomat', false, false, true);
INSERT INTO public.haiku_parts VALUES (11, 'Flammehår, varmt smil', true, false, true);
INSERT INTO public.haiku_parts VALUES (12, 'Rødhåret skønhed', true, false, true);
INSERT INTO public.haiku_parts VALUES (13, 'Rødhåret moder', true, true, false);
INSERT INTO public.haiku_parts VALUES (14, 'Rouvis mor, en gave skabt', false, true, false);
INSERT INTO public.haiku_parts VALUES (15, 'Som havets bølger', true, false, false);
INSERT INTO public.haiku_parts VALUES (16, 'Rødhåret skønhed,', true, false, true);
INSERT INTO public.haiku_parts VALUES (17, 'Lyset i hendes øjne.', false, true, false);
INSERT INTO public.haiku_parts VALUES (18, 'Rouvis mor, så blid,', true, true, false);
INSERT INTO public.haiku_parts VALUES (19, 'Hun lyser som sol,', true, true, false);
INSERT INTO public.haiku_parts VALUES (20, 'Rouvis mor, en gave skabt,', false, true, false);
INSERT INTO public.haiku_parts VALUES (21, 'Livets evige flamme.', false, false, true);
INSERT INTO public.haiku_parts VALUES (22, 'Som havets bølger.', true, false, false);
INSERT INTO public.haiku_parts VALUES (23, 'I mors arme tryg,', true, true, false);
INSERT INTO public.haiku_parts VALUES (24, 'Rouvis mor, et smil,', true, true, false);
INSERT INTO public.haiku_parts VALUES (25, 'Som bjergenes ro.', true, false, true);
INSERT INTO public.haiku_parts VALUES (26, 'Hun er som en sol,', true, true, false);
INSERT INTO public.haiku_parts VALUES (27, 'Opvarmer, lyser, guider,', false, false, true);
INSERT INTO public.haiku_parts VALUES (28, 'Rouvis mor, minde.', false, true, false);
INSERT INTO public.haiku_parts VALUES (29, 'I mørke, hans grin,', true, false, true);
INSERT INTO public.haiku_parts VALUES (30, 'Kevin drømmer stort,', true, false, true);
INSERT INTO public.haiku_parts VALUES (31, 'En ven, løvebror,', true, false, true);
INSERT INTO public.haiku_parts VALUES (32, 'Tillid, støtte, kærlighed,', false, true, false);
INSERT INTO public.haiku_parts VALUES (33, 'Amperman, vores frelser', false, false, true);
INSERT INTO public.haiku_parts VALUES (34, 'Amper som måltid', true, true, false);
INSERT INTO public.haiku_parts VALUES (35, 'Kevin er alt og mere.', false, false, true);
INSERT INTO public.haiku_parts VALUES (36, 'Kevin is life', true, false, true);
INSERT INTO public.haiku_parts VALUES (37, 'Tacklet af Jonas', true, false, false);
INSERT INTO public.haiku_parts VALUES (38, 'Har det som Fred i byen', false, false, false);
INSERT INTO public.haiku_parts VALUES (39, 'Opgaver done, i am gone', false, false, false);
INSERT INTO public.haiku_parts VALUES (40, 'Get out of my fucking swamp', false, false, false);
INSERT INTO public.haiku_parts VALUES (41, 'Kevin is love', true, false, true);


--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 219
-- Name: haiku_parts_part_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.haiku_parts_part_id_seq', 41, true);


--
-- TOC entry 3215 (class 2606 OID 25558)
-- Name: haiku_parts haiku_parts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.haiku_parts
    ADD CONSTRAINT haiku_parts_pkey PRIMARY KEY (part_id);


-- Completed on 2024-03-22 16:19:30 UTC

--
-- PostgreSQL database dump complete
--

