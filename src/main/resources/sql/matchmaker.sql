--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-03-22 09:54:42 UTC

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
-- TOC entry 222 (class 1259 OID 17287)
-- Name: fugitives; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fugitives (
    fugitives_id integer NOT NULL,
    category character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    haircolor character varying(25) NOT NULL,
    eyecolor character varying(25) NOT NULL,
    sex character varying(50) NOT NULL,
    race character varying(30) NOT NULL,
    occupation character varying(30) NOT NULL,
    description character varying(400),
    photo_url character varying(200)
);


ALTER TABLE public.fugitives OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17286)
-- Name: fugitives_fugitivesid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fugitives_fugitivesid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.fugitives_fugitivesid_seq OWNER TO postgres;

--
-- TOC entry 3391 (class 0 OID 0)
-- Dependencies: 221
-- Name: fugitives_fugitivesid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fugitives_fugitivesid_seq OWNED BY public.fugitives.fugitives_id;


--
-- TOC entry 220 (class 1259 OID 17278)
-- Name: liked; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.liked (
    liked_id integer NOT NULL,
    fk_user_id integer NOT NULL,
    fk_fugitives integer NOT NULL
);


ALTER TABLE public.liked OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17277)
-- Name: liked_likedID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."liked_likedID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."liked_likedID_seq" OWNER TO postgres;

--
-- TOC entry 3392 (class 0 OID 0)
-- Dependencies: 219
-- Name: liked_likedID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."liked_likedID_seq" OWNED BY public.liked.liked_id;


--
-- TOC entry 218 (class 1259 OID 17271)
-- Name: matchmaker_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.matchmaker_user (
    user_id integer NOT NULL,
    username character varying(100) NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    age integer NOT NULL,
    gender "char" NOT NULL,
    userpassword character varying(100) NOT NULL
);


ALTER TABLE public.matchmaker_user OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17270)
-- Name: matchmaker_user_UserID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."matchmaker_user_UserID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."matchmaker_user_UserID_seq" OWNER TO postgres;

--
-- TOC entry 3393 (class 0 OID 0)
-- Dependencies: 217
-- Name: matchmaker_user_UserID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."matchmaker_user_UserID_seq" OWNED BY public.matchmaker_user.user_id;


--
-- TOC entry 224 (class 1259 OID 17296)
-- Name: preference; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.preference (
    preference_id integer NOT NULL,
    user_id integer NOT NULL,
    haircolor character varying(50),
    eyecolor character varying(50),
    height integer,
    sex "char" NOT NULL,
    race character varying(25)
);


ALTER TABLE public.preference OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17295)
-- Name: preference_PreferenceID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."preference_PreferenceID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."preference_PreferenceID_seq" OWNER TO postgres;

--
-- TOC entry 3394 (class 0 OID 0)
-- Dependencies: 223
-- Name: preference_PreferenceID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."preference_PreferenceID_seq" OWNED BY public.preference.preference_id;


--
-- TOC entry 3220 (class 2604 OID 17290)
-- Name: fugitives fugitives_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fugitives ALTER COLUMN fugitives_id SET DEFAULT nextval('public.fugitives_fugitivesid_seq'::regclass);


--
-- TOC entry 3219 (class 2604 OID 17281)
-- Name: liked liked_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked ALTER COLUMN liked_id SET DEFAULT nextval('public."liked_likedID_seq"'::regclass);


--
-- TOC entry 3218 (class 2604 OID 17274)
-- Name: matchmaker_user user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matchmaker_user ALTER COLUMN user_id SET DEFAULT nextval('public."matchmaker_user_UserID_seq"'::regclass);


--
-- TOC entry 3221 (class 2604 OID 17299)
-- Name: preference preference_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preference ALTER COLUMN preference_id SET DEFAULT nextval('public."preference_PreferenceID_seq"'::regclass);


--
-- TOC entry 3383 (class 0 OID 17287)
-- Dependencies: 222
-- Data for Name: fugitives; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.fugitives VALUES (3, 'CounterIntelligence', 'Natalia', 'Brown', 'Blue', 'F', 'White', 'Confidential', 'Natalia Burlinova is wanted for allegedly acting as an illegal agent of a foreign government, Russia, within the United States. Burlinova allegedly conspired with
an officer of Russia’s Federal Security Service (FSB) to recruit United States citizens to travel to Moscow to participate in a program called Meeting Russia run
by the organization that she led', 'https://www.fbi.gov/wanted/counterintelligence/natalia-burlinova/@@images/image/preview');
INSERT INTO public.fugitives VALUES (4, 'CounterIntelligence', 'YEVGENIY', 'Black', 'Brown', 'M', 'White', 'Spy', 'Yevgeniy Alexandrovich Grinin is wanted for his alleged involvement in the Russian-backed procurement network Serniya/Sertal from 2017 to 2022. Grinin and his
co-conspirators allegedly unlawfully sourced, purchased, and shipped military and sensitive dual-use technologies from United States manufacturers to Russian
end users. ', 'https://www.fbi.gov/wanted/counterintelligence/yevgeniy-alexandrovich-grinin/@@images/image/preview');
INSERT INTO public.fugitives VALUES (5, 'Espionage', 'Ming', 'Black', 'Brown', 'M', 'Asian', 'Intel', 'Ming Chunde are wanted for allegedly acting in a conspiracy to send government officials from the
People''s Republic of China (PRC) to the United States, and utilize assets located in the United States without notice to the United States government to surveil,
harass, and threaten alleged fugitive and family members to coerce the target’s repatriation to the PRC. ', 'https://www.fbi.gov/wanted/counterintelligence/ming-chunde/@@images/image/preview');
INSERT INTO public.fugitives VALUES (6, 'Identity Theft', 'Irina', 'Brown', 'Brown', 'F', 'White', 'Confidential', 'Irina Viktorovna Kaverzina is wanted by the FBI for her alleged involvement in a conspiracy to defraud the United States by impairing,
obstructing, and defeating the lawful functions of the Federal Election Commission, the United States Department of Justice, and the United
States Department of State.', 'https://www.fbi.gov/wanted/counterintelligence/irina-viktorovna-kaverzina/@@images/image/preview');
INSERT INTO public.fugitives VALUES (7, 'Espionage', 'ALEKSANDRA', 'Blonde', 'Green', 'F', 'White', 'Spy', 'Aleksandra Yuryevna Krylova is wanted by the FBI for her alleged involvement in a conspiracy to defraud the United States by impairing,
obstructing, and defeating the lawful functions of the Federal Election Commission, the United States Department of Justice, and the United
States Department of State.', 'https://www.fbi.gov/wanted/counterintelligence/aleksandra-yuryevna-krylova/@@images/image/preview');
INSERT INTO public.fugitives VALUES (8, 'Finance', 'Yury', 'Brown', 'Brown', 'M', 'White', 'Financebro', 'Yury Savin and his co-conspirator, Sergey Klinov, are wanted for their involvement in several white collar crimes. Both men did business with ARC Electronics, a
Russian procurement company', 'https://www.fbi.gov/wanted/counterintelligence/yury-yevgenyevich-savin/@@images/image/preview');
INSERT INTO public.fugitives VALUES (9, 'Finance', 'Vladislav', 'Gray', 'Brown', 'M', 'White', 'Investor', 'Vladislav Osipov is wanted for his alleged involvement in criminal activities to include bank fraud, money laundering, conspiracy to defraud the United States, and
International Emergency Economic Powers Act violations. ', 'https://www.fbi.gov/wanted/wcc/vladislav-osipov/@@images/image/preview');
INSERT INTO public.fugitives VALUES (10, 'Finance', 'Christopher', 'Brown', 'Green', 'M', 'White', 'Finance', 'Christopher W. Burns is wanted for his alleged involvement in a mail fraud scheme in Georgia. It is alleged that Burns defrauded a number of victims out of
hundreds of thousands of dollars', 'https://www.fbi.gov/wanted/wcc/christopher-w-burns/@@images/image/preview');
INSERT INTO public.fugitives VALUES (11, 'Finance', 'Leonid', 'Brown', 'Blue', 'M', 'White', 'Real Estate', 'Leonid Doubinski and his brother, Volodymyr Dubinsky, are wanted for their alleged involvement in a mortgage fraud scheme in California. The brothers built,
developed, and sold real estate in California from August 2006 through May 2008.', 'https://www.fbi.gov/wanted/wcc/leonid-doubinski/@@images/image/preview');
INSERT INTO public.fugitives VALUES (12, 'Money', 'Ayitey', 'Brown', 'Brown', 'M', 'Black', 'Nurse', 'Ayitey Ayayee-Amim worked in 
Health in Arlington, Texas, from 2010 to 2011. During that time, Amim allegedly submitted false and fraudulent Medicare claims for health services that were
medically unnecessary and for services that were not provided. Additionally, Amim allegedly recruited new patients and paid cash kickbacks to established
patients. ', 'https://www.fbi.gov/wanted/wcc/ayitey-ayayee-amim/@@images/image/preview');
INSERT INTO public.fugitives VALUES (13, 'Government', 'Nakhle', 'Grey', 'Green', 'M', 'Middleeastern', 'Tax', 'Nakhle Nader is wanted on four counts of income tax evasion. F', 'https://www.fbi.gov/wanted/wcc/nakhle-nader/@@images/image/preview');
INSERT INTO public.fugitives VALUES (14, 'House', 'Julieanne', 'Brown', 'Green', 'F', 'Latin', 'Real Estate', 'Julieanne Dimitrion and her husband, John, were indicted in February of 2009 for mortgage fraud.', 'https://www.fbi.gov/wanted/wcc/julieanne-baldueza-dimitrion/@@images/image/preview');
INSERT INTO public.fugitives VALUES (15, 'Murder', 'Cesar', 'Brown', 'Brown', 'M', 'Latin', 'Cleaning', 'Cesar Soltero Herrera is wanted for his alleged involvement in a murder and attempted murder in 2007. ', 'https://www.fbi.gov/wanted/murders/cesar-soltero-herrera/@@images/image/preview');
INSERT INTO public.fugitives VALUES (17, 'Murder', 'Danny', 'Blonde', 'Blue', 'M', 'White', 'Butcher', 'Danny Liggett is wanted for the murder of a man in New York City (Manhattan) on May 7, 1987.  During that meeting, Liggett allegedly used a knife to attack both the man, who was physically impaired
and in a wheelchair, as well as the female acquaintance.', 'https://www.fbi.gov/wanted/murders/danny-liggett/@@images/image/preview');
INSERT INTO public.fugitives VALUES (18, 'Kill', 'FRANCISCO', 'Blonde', 'Brown', 'M', 'Latin', 'Excecutioner', 'Francisco Martinez is wanted for allegedly shooting to death his employer in Passaic, New Jersey, on September 14, 2001. The victim was shot execution-style
in the basement of the building in which they both worked. ', 'https://www.fbi.gov/wanted/murders/francisco-martinez/@@images/image/preview');
INSERT INTO public.fugitives VALUES (19, 'Kill', 'Caris', 'Brown', 'Brown', 'F', 'Latin', 'Babysitter', 'Caris E. Ayala is wanted for the murder of a five-month-old boy in Sandy Springs, Georgia. On October 2, 2012, Ayala was babysitting for the boy in her apartment
when she called authorities to report that the boy was unresponsive. ', 'https://www.fbi.gov/wanted/murders/caris-e.-ayala/@@images/image/preview');
INSERT INTO public.fugitives VALUES (2, 'Traffic', 'Teresita', 'Black', 'Brown', 'F', 'Asian', 'Import/Export', 'Teresita Tolibas Dandan is wanted for her alleged participation in a labor trafficking scheme that brought members of a Philippines-based church to the United
States, via fraudulently obtained visas, and forced the members to solicit donations for a bogus charity, donations that actually were used to finance church
operations and the lavish lifestyles of its leaders.', 'https://www.fbi.gov/wanted/human-trafficking/teresita-tolibas-dandan/@@images/image/preview');
INSERT INTO public.fugitives VALUES (1, 'Traffic', 'WeiLi', 'Black
', 'Brown', 'F', 'Asian', 'Massage', 'Wei Li Pang plead guilty to a two-count information in the United States District Court, Western District of Missouri, Kansas City, Missouri, in December of 2008.
The information alleges that Pang aided and abetted others in the transportation of females in interstate commerce with the intent that these females engage in
prostitution inside massage parlors and elsewhere.', 'https://www.fbi.gov/wanted/human-trafficking/weili-pang/@@images/image/preview');


--
-- TOC entry 3381 (class 0 OID 17278)
-- Dependencies: 220
-- Data for Name: liked; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3379 (class 0 OID 17271)
-- Dependencies: 218
-- Data for Name: matchmaker_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.matchmaker_user VALUES (1, 'jeppe', 'jeppe', 'jeppe', 20, 'M', 'jj');
INSERT INTO public.matchmaker_user VALUES (2, 'Jeppe', 'Jeppe', 'Remoulade', 20, 'M', '123');
INSERT INTO public.matchmaker_user VALUES (3, 'jj', 'jj', 'jj', 30, 'M', 'jj');
INSERT INTO public.matchmaker_user VALUES (4, 'jj', 'jj', 'jj', 24, 'M', 'jj');


--
-- TOC entry 3385 (class 0 OID 17296)
-- Dependencies: 224
-- Data for Name: preference; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.preference VALUES (1, 1, 'Blonde', 'Brown', NULL, 'F', NULL);
INSERT INTO public.preference VALUES (2, 2, 'Black', 'Brown', NULL, 'M', NULL);
INSERT INTO public.preference VALUES (3, 3, 'Blonde', 'Brown', NULL, 'M', NULL);
INSERT INTO public.preference VALUES (4, 4, 'Blonde', 'Brown', NULL, 'M', NULL);


--
-- TOC entry 3395 (class 0 OID 0)
-- Dependencies: 221
-- Name: fugitives_fugitivesid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fugitives_fugitivesid_seq', 22, true);


--
-- TOC entry 3396 (class 0 OID 0)
-- Dependencies: 219
-- Name: liked_likedID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."liked_likedID_seq"', 1, false);


--
-- TOC entry 3397 (class 0 OID 0)
-- Dependencies: 217
-- Name: matchmaker_user_UserID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."matchmaker_user_UserID_seq"', 4, true);


--
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 223
-- Name: preference_PreferenceID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."preference_PreferenceID_seq"', 4, true);


--
-- TOC entry 3229 (class 2606 OID 17294)
-- Name: fugitives fugitives_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fugitives
    ADD CONSTRAINT fugitives_pkey PRIMARY KEY (fugitives_id);


--
-- TOC entry 3225 (class 2606 OID 17283)
-- Name: liked liked_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT liked_pkey PRIMARY KEY (liked_id);


--
-- TOC entry 3223 (class 2606 OID 17276)
-- Name: matchmaker_user matchmaker_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.matchmaker_user
    ADD CONSTRAINT matchmaker_user_pkey PRIMARY KEY (user_id);


--
-- TOC entry 3231 (class 2606 OID 17301)
-- Name: preference preference_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preference
    ADD CONSTRAINT preference_pkey PRIMARY KEY (preference_id);


--
-- TOC entry 3227 (class 2606 OID 17285)
-- Name: liked user_fugitive_composite_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT user_fugitive_composite_key UNIQUE (fk_user_id, fk_fugitives);


--
-- TOC entry 3234 (class 2606 OID 17317)
-- Name: preference fk_userid_preference; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preference
    ADD CONSTRAINT fk_userid_preference FOREIGN KEY (user_id) REFERENCES public.matchmaker_user(user_id) NOT VALID;


--
-- TOC entry 3232 (class 2606 OID 17307)
-- Name: liked likedfugitivesID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT "likedfugitivesID" FOREIGN KEY (fk_fugitives) REFERENCES public.fugitives(fugitives_id) NOT VALID;


--
-- TOC entry 3233 (class 2606 OID 17302)
-- Name: liked likeduserID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.liked
    ADD CONSTRAINT "likeduserID" FOREIGN KEY (fk_user_id) REFERENCES public.matchmaker_user(user_id) NOT VALID;


-- Completed on 2024-03-22 09:54:42 UTC

--
-- PostgreSQL database dump complete
--

