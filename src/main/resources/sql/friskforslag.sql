--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-03-22 10:57:30 UTC

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
-- TOC entry 218 (class 1259 OID 16870)
-- Name: friskforslag_opskrift; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.friskforslag_opskrift (
    id integer NOT NULL,
    name character varying NOT NULL,
    description character varying NOT NULL,
    procedure character varying NOT NULL,
    ingredients character varying[] NOT NULL,
    quantities bigint[] NOT NULL,
    units character varying[] NOT NULL,
    source character varying,
    author character varying,
    img character varying
);


ALTER TABLE public.friskforslag_opskrift OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16869)
-- Name: friskforslag_opskrift_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.friskforslag_opskrift_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.friskforslag_opskrift_id_seq OWNER TO postgres;

--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 217
-- Name: friskforslag_opskrift_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.friskforslag_opskrift_id_seq OWNED BY public.friskforslag_opskrift.id;


--
-- TOC entry 3206 (class 2604 OID 16873)
-- Name: friskforslag_opskrift id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friskforslag_opskrift ALTER COLUMN id SET DEFAULT nextval('public.friskforslag_opskrift_id_seq'::regclass);


--
-- TOC entry 3355 (class 0 OID 16870)
-- Dependencies: 218
-- Data for Name: friskforslag_opskrift; Type: TABLE DATA; Schema: public; Owner: postgres
--

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 217
-- Name: friskforslag_opskrift_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.friskforslag_opskrift_id_seq', 1, false);


--
-- TOC entry 3208 (class 2606 OID 16881)
-- Name: friskforslag_opskrift friskforslag_opskrift_name_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friskforslag_opskrift
    ADD CONSTRAINT friskforslag_opskrift_name_unique UNIQUE (name);


--
-- TOC entry 3210 (class 2606 OID 16877)
-- Name: friskforslag_opskrift friskforslag_opskrift_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.friskforslag_opskrift
    ADD CONSTRAINT friskforslag_opskrift_pkey PRIMARY KEY (id);


-- Completed on 2024-03-22 10:57:30 UTC

--
-- PostgreSQL database dump complete
--
