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
-- TOC entry 218 (class 1259 OID 16491)
-- Name: plante; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.plante (
    plante_id integer NOT NULL,
    navn character varying(255) NOT NULL,
    placering character varying(255),
    user_id integer NOT NULL
);


ALTER TABLE public.plante OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16490)
-- Name: plante_plante_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.plante_plante_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.plante_plante_id_seq OWNER TO postgres;

--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 217
-- Name: plante_plante_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.plante_plante_id_seq OWNED BY public.plante.plante_id;


--
-- TOC entry 220 (class 1259 OID 16505)
-- Name: vandtid; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vandtid (
    vandtid_id integer NOT NULL,
    dato date,
    plante_id integer NOT NULL
);


ALTER TABLE public.vandtid OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16504)
-- Name: vandtid_vandtid_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.vandtid_vandtid_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vandtid_vandtid_id_seq OWNER TO postgres;

--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 219
-- Name: vandtid_vandtid_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.vandtid_vandtid_id_seq OWNED BY public.vandtid.vandtid_id;


--
-- TOC entry 3210 (class 2604 OID 16494)
-- Name: plante plante_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plante ALTER COLUMN plante_id SET DEFAULT nextval('public.plante_plante_id_seq'::regclass);


--
-- TOC entry 3211 (class 2604 OID 16508)
-- Name: vandtid vandtid_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vandtid ALTER COLUMN vandtid_id SET DEFAULT nextval('public.vandtid_vandtid_id_seq'::regclass);


--
-- TOC entry 3362 (class 0 OID 16491)
-- Dependencies: 218
-- Data for Name: plante; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.plante VALUES (1, 'Monstera', 'Ved siden af kommoden', 6);
INSERT INTO public.plante VALUES (2, 'Violinfigen', 'i stuen', 7);
INSERT INTO public.plante VALUES (4, 'Egeføj', 'på lampen', 6);
INSERT INTO public.plante VALUES (5, 'bananplante', 'junglen', 6);
INSERT INTO public.plante VALUES (6, 'Egeføj2', 'væg2', 6);


--
-- TOC entry 3364 (class 0 OID 16505)
-- Dependencies: 220
-- Data for Name: vandtid; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.vandtid VALUES (1, '2024-03-22', 1);
INSERT INTO public.vandtid VALUES (5, '2024-03-22', 5);
INSERT INTO public.vandtid VALUES (4, '2024-03-22', 4);
INSERT INTO public.vandtid VALUES (6, '2024-03-22', 6);
INSERT INTO public.vandtid VALUES (2, '2024-03-22', 2);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 217
-- Name: plante_plante_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.plante_plante_id_seq', 6, true);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 219
-- Name: vandtid_vandtid_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.vandtid_vandtid_id_seq', 6, true);


--
-- TOC entry 3213 (class 2606 OID 16498)
-- Name: plante plante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plante
    ADD CONSTRAINT plante_pkey PRIMARY KEY (plante_id);


--
-- TOC entry 3215 (class 2606 OID 16510)
-- Name: vandtid vandtid_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vandtid
    ADD CONSTRAINT vandtid_pkey PRIMARY KEY (vandtid_id);


--
-- TOC entry 3217 (class 2606 OID 16511)
-- Name: vandtid fk_plante_vandtid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vandtid
    ADD CONSTRAINT fk_plante_vandtid FOREIGN KEY (plante_id) REFERENCES public.plante(plante_id);


--
-- TOC entry 3216 (class 2606 OID 16499)
-- Name: plante fk_users_plante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.plante
    ADD CONSTRAINT fk_users_plante FOREIGN KEY (user_id) REFERENCES public.users(user_id);


-- Completed on 2024-03-22 09:54:40 UTC

--
-- PostgreSQL database dump complete
--

