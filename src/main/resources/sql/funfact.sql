--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-03-22 12:33:28 UTC

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
-- TOC entry 217 (class 1259 OID 24671)
-- Name: funfact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funfact (
    date integer NOT NULL,
    fact character varying(150) NOT NULL
);


ALTER TABLE public.funfact OWNER TO postgres;

--
-- TOC entry 3350 (class 0 OID 24671)
-- Dependencies: 217
-- Data for Name: funfact; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.funfact VALUES (101, 'Giraffer har kun syv nakkehvirvler, ligesom mennesker.');
INSERT INTO public.funfact VALUES (102, 'Honning er det eneste fødevareprodukt, der aldrig bliver dårligt.');
INSERT INTO public.funfact VALUES (103, 'Et lyn er seks gange varmere end solen.');
INSERT INTO public.funfact VALUES (104, 'Pingviner kan springe op til seks meter højt.');
INSERT INTO public.funfact VALUES (105, 'Isbjørne er venstrehåndede.');
INSERT INTO public.funfact VALUES (106, 'Mennesker deler 50% af deres gener med bananer.');
INSERT INTO public.funfact VALUES (107, 'En blæksprutte har tre hjerter.');
INSERT INTO public.funfact VALUES (108, 'En ko kan gå op ad en trappe, men ikke ned ad den.');
INSERT INTO public.funfact VALUES (109, 'Kolibrier er de eneste fugle, der kan flyve baglæns.');
INSERT INTO public.funfact VALUES (110, 'Krokodiller kan ikke stikke tungen ud.');
INSERT INTO public.funfact VALUES (111, 'Månen bevæger sig cirka 3,8 cm væk fra Jorden hvert år.');
INSERT INTO public.funfact VALUES (112, 'Flamingoer er faktisk grå, ikke pink.');
INSERT INTO public.funfact VALUES (113, 'Ørkenrotter kan løbe op til 9 km om natten.');
INSERT INTO public.funfact VALUES (114, 'Elefanter er de eneste dyr, der ikke kan hoppe.');
INSERT INTO public.funfact VALUES (115, 'En hveps kan flyve op til 24 km/t.');
INSERT INTO public.funfact VALUES (116, 'Menneskelige knogler er stærkere end beton.');
INSERT INTO public.funfact VALUES (117, 'En voksen giraf kan rense sine ører med tungen.');
INSERT INTO public.funfact VALUES (118, 'Næsehorn kan gå op til fem dage uden at drikke vand.');
INSERT INTO public.funfact VALUES (119, 'En voksen blåhval kan have en tunge så stor som en elefant.');
INSERT INTO public.funfact VALUES (120, 'Regnorme har fem hjerter.');
INSERT INTO public.funfact VALUES (121, 'En guldstruds'' øje er større end dens hjerne.');
INSERT INTO public.funfact VALUES (122, 'Honning er det eneste mad, der aldrig rådner.');
INSERT INTO public.funfact VALUES (123, 'Bananer er bær, men jordbær er ikke.');
INSERT INTO public.funfact VALUES (124, 'En myretue kan rumme op til 500.000 myrer.');
INSERT INTO public.funfact VALUES (125, 'Vandmænd er 95% vand.');
INSERT INTO public.funfact VALUES (126, 'En flodhest kan løbe hurtigere end en menneske.');
INSERT INTO public.funfact VALUES (127, 'En humlebi kan flyve i regnvejr, men ikke i stærk vind.');
INSERT INTO public.funfact VALUES (128, 'Guld kan tyndes ud til et lag, der er kun tre atomer tykt.');
INSERT INTO public.funfact VALUES (129, 'Fingeraftryk af koalabjørne ligner dem af mennesker.');
INSERT INTO public.funfact VALUES (130, 'Din næse kan huske op til 50.000 forskellige dufte.');
INSERT INTO public.funfact VALUES (131, 'En voksen hvalhaj kan veje mere end 36 ton.');
INSERT INTO public.funfact VALUES (201, 'En kaktus er en type saftig plante.');
INSERT INTO public.funfact VALUES (202, 'Træer kommunikerer gennem et underjordisk netværk af rødder.');
INSERT INTO public.funfact VALUES (203, 'En kolibri vejer mindre end et stykke slik.');
INSERT INTO public.funfact VALUES (204, 'Vandmelon er 92% vand.');
INSERT INTO public.funfact VALUES (205, 'Aber kan grine og græde ligesom mennesker.');
INSERT INTO public.funfact VALUES (206, 'En enkelt bivokscelle vejer mindre end et gram.');
INSERT INTO public.funfact VALUES (207, 'En voksen hest har 40 tænder.');
INSERT INTO public.funfact VALUES (208, 'Havet er hjemsted for 94% af alt liv på Jorden.');
INSERT INTO public.funfact VALUES (209, 'En ko kan opløse metal med sin mave.');
INSERT INTO public.funfact VALUES (210, 'En voksen hund har 42 tænder.');
INSERT INTO public.funfact VALUES (211, 'Hjorte kan springe op til otte gange deres længde.');
INSERT INTO public.funfact VALUES (212, 'Vanddampe fra en stor regnskov kan producere mere regn end Amazonfloden.');
INSERT INTO public.funfact VALUES (213, 'En blæksprutte har ikke kun tre hjerter, men også blåt blod.');
INSERT INTO public.funfact VALUES (214, 'En voksen kat har normalt 30 tænder.');
INSERT INTO public.funfact VALUES (215, 'Løver kan sove op til 20 timer om dagen.');
INSERT INTO public.funfact VALUES (216, 'En enkelt regndråbe kan veje op til 50 mg');
INSERT INTO public.funfact VALUES (217, 'En hest kan sove, mens den står.');
INSERT INTO public.funfact VALUES (218, 'En flagermus kan spise op til 1.000 insekter i timen.');
INSERT INTO public.funfact VALUES (219, 'En blæksprutte kan ændre farve for at matche dens omgivelser.');
INSERT INTO public.funfact VALUES (220, 'Et enkelt bjørnehjerte vejer cirka 1% af dens kropsvægt.');
INSERT INTO public.funfact VALUES (221, 'Næsehorn kan lugte vand op til 4 km væk.');
INSERT INTO public.funfact VALUES (222, 'Geder har kvadratiske pupiller, der giver dem et bredere synsfelt.');
INSERT INTO public.funfact VALUES (223, 'En pingvin kan dykke ned til 500 meter for at fange fisk.');
INSERT INTO public.funfact VALUES (224, 'kattes hals kan dreje 180 grader.');
INSERT INTO public.funfact VALUES (225, 'Pindsvin kan svømme.');
INSERT INTO public.funfact VALUES (226, 'En edderkop kan spise op til 80% af dens egen kropsvægt på en dag.');
INSERT INTO public.funfact VALUES (227, 'Et strudsæg kan bære vægten af en voksen mand uden at knække.');
INSERT INTO public.funfact VALUES (228, 'En kolibri har et hjerte, der slår over 1.200 gange i minuttet.');
INSERT INTO public.funfact VALUES (229, 'En myre kan løfte op til 50 gange dens egen kropsvægt.');
INSERT INTO public.funfact VALUES (301, 'Et egerns hjerte slår mere end 300 gange i minuttet.');
INSERT INTO public.funfact VALUES (302, 'Papegøjer kan efterligne menneskelig tale.');
INSERT INTO public.funfact VALUES (303, 'En voksen havskildpadde kan veje op til 700 kg.');
INSERT INTO public.funfact VALUES (304, 'En snegl kan sove i op til tre år.');
INSERT INTO public.funfact VALUES (305, 'Flodheste udskiller en rød væske, der fungerer som solcreme.');
INSERT INTO public.funfact VALUES (306, 'En voksende bambusplante kan vokse op til 91 cm på én dag.');
INSERT INTO public.funfact VALUES (307, 'En delfin sover med én hjernehalvdel ad gangen.');
INSERT INTO public.funfact VALUES (308, 'En kakerlak kan overleve i op til en uge uden sit hoved.');
INSERT INTO public.funfact VALUES (309, 'En gorilla har en stærkere bidstyrke end en haj.');
INSERT INTO public.funfact VALUES (310, 'En voksen hvalros kan veje op til 1,5 tons.');
INSERT INTO public.funfact VALUES (311, 'En rød panda er ikke en panda, men beslægtet med vaskebjørn.');
INSERT INTO public.funfact VALUES (312, 'En voksen flodhest kan spise op til 68 kg mad hver nat.');
INSERT INTO public.funfact VALUES (313, 'En skildpadde kan trække sit hoved ind i dens skal for beskyttelse.');
INSERT INTO public.funfact VALUES (314, 'En flyvende firben kan glide gennem luften op til 200 meter.');
INSERT INTO public.funfact VALUES (315, 'En voksen chimpanse kan være stærkere end seks voksne mennesker.');
INSERT INTO public.funfact VALUES (316, 'En bison kan springe 183 cm op i luften.');
INSERT INTO public.funfact VALUES (317, 'En skorpion kan overleve i op til et år uden mad.');
INSERT INTO public.funfact VALUES (318, 'En voksen gråhaj kan have op til 3.000 tænder på én gang.');
INSERT INTO public.funfact VALUES (319, 'En kvælerslange kan sluge sit bytte helt');
INSERT INTO public.funfact VALUES (320, 'En voksen isbjørn kan veje op til 700 kg.');
INSERT INTO public.funfact VALUES (321, 'En pingvin er en fugl, men den kan ikke flyve.');
INSERT INTO public.funfact VALUES (322, 'En voksen hane kan have op til 6-8 kilo muskler.');
INSERT INTO public.funfact VALUES (323, 'En løve kan brøle så højt, at det kan høres op til 8 km væk.');
INSERT INTO public.funfact VALUES (324, 'Et strudsæg er verdens største fugleæg.');
INSERT INTO public.funfact VALUES (325, 'En sebra har striber for at forvirre sine rovdyr.');
INSERT INTO public.funfact VALUES (326, 'En voksen myre lever normalt mellem 90 dage og 3 år.');
INSERT INTO public.funfact VALUES (327, 'En voksen ged kan hoppe 152 cm op i luften.');
INSERT INTO public.funfact VALUES (328, 'En voksen krokodille kan veje op til 1 ton.');
INSERT INTO public.funfact VALUES (329, 'En voksen gorilla kan spise op til 18 kg mad om dagen.');
INSERT INTO public.funfact VALUES (330, 'En hvalhaj lever normalt mellem 70 og 100 år.');
INSERT INTO public.funfact VALUES (331, 'En voksen giraf kan sove i op til 30 minutter ad gangen.');
INSERT INTO public.funfact VALUES (401, 'En voksen tiger kan hoppe op til 10 meter i længden.');
INSERT INTO public.funfact VALUES (402, 'En voksen koala sover op til 22 timer om dagen.');
INSERT INTO public.funfact VALUES (403, 'En voksen alligator kan leve op til 50 år i naturen.');
INSERT INTO public.funfact VALUES (404, 'En voksen bjørn kan sove i op til 7 måneder om året.');
INSERT INTO public.funfact VALUES (405, 'En voksen gepard kan løbe op til 60 mph på kort afstand.');
INSERT INTO public.funfact VALUES (406, 'En voksen krabbe kan leve i op til 20 år.');
INSERT INTO public.funfact VALUES (407, 'En voksen øgle kan leve i op til 30 år.');
INSERT INTO public.funfact VALUES (408, 'En voksen frø kan leve i op til 15 år.');
INSERT INTO public.funfact VALUES (409, 'En voksen hval kan leve i op til 90 år.');
INSERT INTO public.funfact VALUES (410, 'En voksen mus kan leve i op til 2 år.');
INSERT INTO public.funfact VALUES (411, 'Den afrikanske elefant er verdens største landdyr.');
INSERT INTO public.funfact VALUES (412, 'Grønne skildpadder kan migrere tusindvis af miles over havet.');
INSERT INTO public.funfact VALUES (413, 'En skægagam kan vokse op til en længde på to meter.');
INSERT INTO public.funfact VALUES (414, 'En tordensky indeholder millioner af tons vand.');
INSERT INTO public.funfact VALUES (415, 'En snegl kan have over 14.000 tænder.');
INSERT INTO public.funfact VALUES (416, 'Gnavere kan tygge næsten alt, inklusive beton.');
INSERT INTO public.funfact VALUES (417, 'Flodheste er blandt de farligste dyr i Afrika.');
INSERT INTO public.funfact VALUES (418, 'Kolibrier kan veje mindre end en øre.');
INSERT INTO public.funfact VALUES (419, 'Kolibrier kan veje mindre end en øre.');
INSERT INTO public.funfact VALUES (420, 'Skildpadder kan leve i mere end 100 år.');
INSERT INTO public.funfact VALUES (421, 'En kænguru kan hoppe op til tre gange sin egen længde.');
INSERT INTO public.funfact VALUES (422, 'En struds har de største øjne blandt alle landlevende dyr.');
INSERT INTO public.funfact VALUES (423, 'En voksen tæge kan suge blod, indtil den bliver 200 gange sin egen størrelse.');
INSERT INTO public.funfact VALUES (424, 'En voksen bænkebider kan løfte op til 850 gange sin egen kropsvægt.');
INSERT INTO public.funfact VALUES (425, 'En chimpanses DNA ligner menneskets med 98%');
INSERT INTO public.funfact VALUES (426, 'En kameleon kan bevæge sine øjne uafhængigt af hinanden');
INSERT INTO public.funfact VALUES (427, 'En voksen struds kan løbe med en hastighed på op til 70 km/t.');
INSERT INTO public.funfact VALUES (428, 'En flodhest kan åbne munden op til 180 grader.');
INSERT INTO public.funfact VALUES (429, 'En skildpadde kan holde vejret i op til flere timer.');
INSERT INTO public.funfact VALUES (430, 'En voksen isbjørn kan lugte et bytte op til 30 km væk.');
INSERT INTO public.funfact VALUES (501, 'En voksen chinchilla har mere end 50 hår per hårsæk.');
INSERT INTO public.funfact VALUES (502, 'En voksen hval kan synge i op til 24 timer ad gangen.');
INSERT INTO public.funfact VALUES (503, 'En kattes næseaftryk er unikt, ligesom et menneskes fingeraftryk.');
INSERT INTO public.funfact VALUES (504, 'En voksen blæksprutte har otte arme, men ingen rygrad.');
INSERT INTO public.funfact VALUES (505, 'En skorpion kan gløde i mørket under UV-lys.');
INSERT INTO public.funfact VALUES (506, 'En voksen ko kan drikke op til 50 liter vand om dagen.');
INSERT INTO public.funfact VALUES (507, 'Et pindsvin har omkring 5000-7000 stikkende piggene.');
INSERT INTO public.funfact VALUES (508, 'En voksen hest kan drikke op til 25 liter vand om dagen.');
INSERT INTO public.funfact VALUES (509, 'En voksen elefant kan drikke op til 200 liter vand på én gang.');
INSERT INTO public.funfact VALUES (510, 'En voksen flagermus kan navigere ved hjælp af ekkolokation.');
INSERT INTO public.funfact VALUES (511, 'En voksen krokodille kan løbe med en hastighed på op til 17 km/t på land.');
INSERT INTO public.funfact VALUES (512, 'En voksen bænkebider kan miste et ben som en form for selvforsvar.');
INSERT INTO public.funfact VALUES (513, 'En voksen kamel kan drikke op til 40 liter vand på én gang.');
INSERT INTO public.funfact VALUES (514, 'En voksen elefant kan have en hjerne, der vejer op til 5 kg.');
INSERT INTO public.funfact VALUES (515, 'Bambus er faktisk en type græs, ikke et træ.');
INSERT INTO public.funfact VALUES (516, 'Saturns ringe består hovedsageligt af is og sten.');
INSERT INTO public.funfact VALUES (517, 'Ordet karaoke betyder tomt orkester på japansk');
INSERT INTO public.funfact VALUES (518, 'Ketchup var engang markedsført som medicin.');
INSERT INTO public.funfact VALUES (519, 'En jordbærs egentlige frugter er de små prikker på overfladen.');
INSERT INTO public.funfact VALUES (520, 'USA har den højeste fængselsbefolkning i verden.');
INSERT INTO public.funfact VALUES (521, 'Coca-Cola er verdens næstmest genkendelige ord efter okay.');
INSERT INTO public.funfact VALUES (522, 'Lyset fra Solen tager omkring otte minutter at nå Jorden.');
INSERT INTO public.funfact VALUES (523, 'Vulkansk aske kan være elektrisk ladet og forårsage lyn under udbrud.');
INSERT INTO public.funfact VALUES (524, 'Elvis Presleys første single kostede $4 at indspille.');
INSERT INTO public.funfact VALUES (525, 'Et enkelt lyn kan indeholde nok energi til at belyse 100 lyspærer.');
INSERT INTO public.funfact VALUES (526, 'Spansk er det officielle sprog i 21 lande.');
INSERT INTO public.funfact VALUES (527, 'Solens diameter er ca. 109 gange større end Jorden.');
INSERT INTO public.funfact VALUES (528, 'Mozart begyndte at komponere musik i en alder af fem.');
INSERT INTO public.funfact VALUES (529, 'Den korteste krig i historien varede kun 38 minutter');
INSERT INTO public.funfact VALUES (530, 'Den ældste kat nogensinde blev 38 år gammel.');
INSERT INTO public.funfact VALUES (531, 'Det første computervirus blev skabt i 1983 og hed Elk Cloner.');
INSERT INTO public.funfact VALUES (601, 'Der er mere end 2000 arter af edderkopper i Australien.');
INSERT INTO public.funfact VALUES (602, 'Et lyn kan slå ned på samme sted mere end én gang.');
INSERT INTO public.funfact VALUES (603, 'Gul er den farve, som øjet ser hurtigst.');
INSERT INTO public.funfact VALUES (604, 'Den første e-mail blev sendt i 1971 af Ray Tomlinson.');
INSERT INTO public.funfact VALUES (605, 'En voksen hest har 205 knogler i kroppen.');
INSERT INTO public.funfact VALUES (606, 'Æbletræer er medlemmer af rosenfamilien.');
INSERT INTO public.funfact VALUES (607, 'Hvert menneske har en unik tungeaftryk.');
INSERT INTO public.funfact VALUES (608, 'Chokolade kan være dødelig for hunde ');
INSERT INTO public.funfact VALUES (609, 'En albatros kan flyve i flere timer uden at lande');
INSERT INTO public.funfact VALUES (610, 'Vandmelon er en frugt og en grøntsag.');
INSERT INTO public.funfact VALUES (611, 'En bladlus kan producere op til 900 afkom på en måned.');
INSERT INTO public.funfact VALUES (612, 'En voksen orangutang har en armspændvidde på op til 2,5 meter.');
INSERT INTO public.funfact VALUES (613, 'Den største solsikke nogensinde registreret var over 9 meter høj.');
INSERT INTO public.funfact VALUES (614, 'En kvælerslange kan sluge bytte, der er fem gange større end dens hoved.');
INSERT INTO public.funfact VALUES (615, 'Det første computervirus blev kaldt Creeper og blev opdaget i 1971.');
INSERT INTO public.funfact VALUES (616, 'Tornadoer kan rotere med hastigheder på op til 480 km/t.');
INSERT INTO public.funfact VALUES (617, 'En voksen hvalhaj er verdens største fisk');
INSERT INTO public.funfact VALUES (618, 'En voksen girafs tunge kan være op til 45 cm lang.');
INSERT INTO public.funfact VALUES (619, 'Regn kan indeholde vitamin B12.');
INSERT INTO public.funfact VALUES (620, 'Et atom er 99,9% tomt rum, men stadig indeholder det enorm energi.');
INSERT INTO public.funfact VALUES (621, 'Vincent van Gogh solgte kun ét maleri, mens han var i live.');
INSERT INTO public.funfact VALUES (622, 'En moden jordnød er en del af blomsten.');
INSERT INTO public.funfact VALUES (623, 'Coca-Cola indeholdt oprindeligt kokain.');
INSERT INTO public.funfact VALUES (624, 'Bananer vokser opad mod solen.');
INSERT INTO public.funfact VALUES (625, 'Giraffer har ingen stemmebånd.');
INSERT INTO public.funfact VALUES (626, 'Hver snefnug har en unik krystalstruktur.');
INSERT INTO public.funfact VALUES (627, 'Hængelåse blev opfundet for mere end 2000 år siden i det gamle Rom.');
INSERT INTO public.funfact VALUES (628, 'Aber har en tendens til at nyse, når de føler sig overvældede.');
INSERT INTO public.funfact VALUES (629, 'Et selskab i Japan udlejer venner til mennesker i behov for selskab.');
INSERT INTO public.funfact VALUES (630, 'Coca-Cola var oprindeligt grøn ');
INSERT INTO public.funfact VALUES (701, 'Vægtløshed kan forårsage muskelsvind og tab af knoglemasse.');
INSERT INTO public.funfact VALUES (702, 'Jordens atmosfære indeholder mere end 78% nitrogen.');
INSERT INTO public.funfact VALUES (703, 'Katte har en tendens til at lande på fødderne på grund af deres fleksible rygsøjler.');
INSERT INTO public.funfact VALUES (704, 'Flodheste er blandt de farligste dyr i Afrika');
INSERT INTO public.funfact VALUES (705, 'Mount Everest vokser cirka 4 millimeter højere hvert år ');
INSERT INTO public.funfact VALUES (706, 'Der er mere end 1.000 forskellige typer af æbler');
INSERT INTO public.funfact VALUES (707, 'olf er det eneste spil, der er blevet spillet på Månen');
INSERT INTO public.funfact VALUES (708, 'Den første spillefilm nogensinde blev lavet i 1888');
INSERT INTO public.funfact VALUES (709, 'Den første spillefilm hed Roundhay Garden Scene.');
INSERT INTO public.funfact VALUES (710, 'Mona Lisa har været stjålet to gange');
INSERT INTO public.funfact VALUES (711, 'Der er mere end 100 milliarder neuroner i en menneskelig hjerne.');
INSERT INTO public.funfact VALUES (712, 'De ældste kendte skrevne ordsprog stammer fra det gamle Egypten ');
INSERT INTO public.funfact VALUES (713, 'Den dyreste kaffe i verden er kopi luwak');
INSERT INTO public.funfact VALUES (714, 'kopi luwak, er lavet med bønner, der er spist og derefter udskilt igen.');
INSERT INTO public.funfact VALUES (715, 'Ørkenen i Sahara er større end USA.');
INSERT INTO public.funfact VALUES (716, 'William Shakespeares rigtige fødselsdato er ukendt,');
INSERT INTO public.funfact VALUES (717, 'Verdens første e-mail indeholdt bogstaverne QWERTYUIOP.');
INSERT INTO public.funfact VALUES (718, 'Lyset fra Solen kan nå Mars på omkring 3 til 22 minutter');
INSERT INTO public.funfact VALUES (719, 'Det første Apple-computermus var lavet af træ.');
INSERT INTO public.funfact VALUES (720, 'Jurassic Park (1993) var den første film, der brugte CGI til at skabe realistiske dyr.');
INSERT INTO public.funfact VALUES (721, 'Den længste dokumentarfilm nogensinde er på over 4 timer.');
INSERT INTO public.funfact VALUES (722, 'Kaffe var oprindeligt brugt som mad og blev først brygget som en drink i det 15. århundrede.');
INSERT INTO public.funfact VALUES (723, 'Det første mobiltelefonopkald blev foretaget den 3. april 1973 ');
INSERT INTO public.funfact VALUES (724, 'Hjernen bruger omkring 20% af kroppens samlede energiforbrug.');
INSERT INTO public.funfact VALUES (725, 'Neptun er det yderste af de otte planeter i vores solsystem og er 4.5 mia km væk.');
INSERT INTO public.funfact VALUES (726, 'Et voksent træ kan producere 6-18 kg ilt hvert år.');
INSERT INTO public.funfact VALUES (727, 'Ordet robot kommer fra det tjekkiske robota, som betyder tvangsarbejde eller slavearbejde.');
INSERT INTO public.funfact VALUES (728, 'Den hurtigste bold i tennis blev slået med en hastighed på 263,4 km/t.');
INSERT INTO public.funfact VALUES (729, 'Canada har den længste kystlinje af enhver nation i verden.');
INSERT INTO public.funfact VALUES (730, 'Den første film nogensinde vist i biografen var i  1895.');
INSERT INTO public.funfact VALUES (731, 'Farven rosa blev navngivet efter blomsten pink.');
INSERT INTO public.funfact VALUES (801, 'Verdens første kriminalroman er udgivet i 1841.');
INSERT INTO public.funfact VALUES (802, 'Orkidéfrø er så små, at en enkelt kapsel kan indeholde op til tre millioner frø.');
INSERT INTO public.funfact VALUES (803, 'Den første farvede film blev lavet i 1908');
INSERT INTO public.funfact VALUES (804, 'En voksen panda spiser op til 12 timer om dagen');
INSERT INTO public.funfact VALUES (805, 'En voksen panda  kan forbruge op til 12 kg bambus.');
INSERT INTO public.funfact VALUES (806, 'Den længste krig i historien varede i 335 år');
INSERT INTO public.funfact VALUES (807, 'IBM''s første computervejledning blev udgivet i 1961 ');
INSERT INTO public.funfact VALUES (808, 'Antarktis er den eneste kontinentale landmasse uden et tidszoneregulativ.');
INSERT INTO public.funfact VALUES (809, 'Det første farvede fotografi blev taget i 1861');
INSERT INTO public.funfact VALUES (810, 'Bioluminescerende svampe kan producere lys uden varme');
INSERT INTO public.funfact VALUES (811, 'Hvepse kan genkende ansigter og huske dem i op til en måned.');
INSERT INTO public.funfact VALUES (812, 'Den første animerede film blev skabt  i 1906');
INSERT INTO public.funfact VALUES (813, 'Volleyball blev opfundet i 1895, i USA.');
INSERT INTO public.funfact VALUES (814, 'Salt er den eneste sten, vi spiser.');
INSERT INTO public.funfact VALUES (815, 'Den første SMS blev sendt i 1992');
INSERT INTO public.funfact VALUES (816, 'Det japanske alfabet består af tre sæt tegn: hiragana, katakana og kanji');
INSERT INTO public.funfact VALUES (817, 'Den første masseproducerede bil var Ford Model T,  i 1908.');
INSERT INTO public.funfact VALUES (818, 'En voksen blæksprutte har omkring 500 sugekopper.');
INSERT INTO public.funfact VALUES (819, 'Lysets hastighed er omkring 299,792 kilometer per sekund.');
INSERT INTO public.funfact VALUES (820, 'Den første lydfilm var The Jazz Singer fra 1927.');
INSERT INTO public.funfact VALUES (821, 'Den højeste score i en enkelt basketballkamp blev sat i 1953, med 156 point.');
INSERT INTO public.funfact VALUES (822, 'Det ældste kendte kunstværk er et hulemaleri i Marokko, der dateres til ca. 900.000 år siden.');
INSERT INTO public.funfact VALUES (823, 'Det græske alfabet har 24 bogstaver');
INSERT INTO public.funfact VALUES (824, 'Den mindste knogle i menneskekroppen er stigbøjlen i øret');
INSERT INTO public.funfact VALUES (825, 'Cricket er en af ​​de ældste og mest populære sportsgrene i verden');
INSERT INTO public.funfact VALUES (826, 'Det første digitale kamera blev opfundet  i 1975 og vejede omkring 8 pund.');
INSERT INTO public.funfact VALUES (827, 'En hamburger blev opkaldt efter den tyske by Hamburg,');
INSERT INTO public.funfact VALUES (828, 'Den kinesiske mur er over 13.000 miles lang og blev bygget over flere århundreder.');
INSERT INTO public.funfact VALUES (829, 'Isbjørne er de største landlevende rovdyr på Jorden');
INSERT INTO public.funfact VALUES (830, 'Hvis du spiller amerikansk fodbold, kan bolden være op til 11 tommer lang');
INSERT INTO public.funfact VALUES (831, 'Den første iPhone blev frigivet af Apple Inc. i 2007.');
INSERT INTO public.funfact VALUES (901, 'Chokolade blev først opdaget af Aztekerne i det 15. århundrede.');
INSERT INTO public.funfact VALUES (902, 'Amazonasregnskoven producerer mere end 20% af Jordens ilt.');
INSERT INTO public.funfact VALUES (903, 'Menneskehjernen vejer omkring 1,4 kg ');
INSERT INTO public.funfact VALUES (904, 'Pyramiderne i Egypten blev bygget som grave til de gamle faraoer.');
INSERT INTO public.funfact VALUES (905, 'Den første bærbare computer vejede over 10 kg.');
INSERT INTO public.funfact VALUES (906, 'Rusland har den største overfladeareal af ethvert land i verden.');
INSERT INTO public.funfact VALUES (907, 'Den første flyvning med et luftskib blev foretaget i 1783.');
INSERT INTO public.funfact VALUES (908, 'Honningbier er de eneste insekter, der producerer mad, som mennesker kan spise.');
INSERT INTO public.funfact VALUES (909, 'Giraffer har en op til 50 cm lang tunge.');
INSERT INTO public.funfact VALUES (910, 'Eiffeltårnet blev opført i 1889 til Verdensudstillingen i Paris');
INSERT INTO public.funfact VALUES (911, 'Eiffeltårnet var oprindeligt kun beregnet til at stå i 20 år.');
INSERT INTO public.funfact VALUES (912, 'Basketball blev opfundet af James Naismith i 1891.');
INSERT INTO public.funfact VALUES (913, 'Den første trykte bog blev opfundet af Johannes Gutenberg i 1440.');
INSERT INTO public.funfact VALUES (914, 'En chokoladeplante har brug for ca 5 år at producere sit første chokoladefrø.');
INSERT INTO public.funfact VALUES (915, 'I Japan er der en ø, der er beboet af hundredvis af vilde kaniner, kaldet Okunoshima');
INSERT INTO public.funfact VALUES (916, 'Menneskehjernen fortsætter med at udvikle sig  gennem hele livet');
INSERT INTO public.funfact VALUES (917, 'Lake Baikal i Rusland er verdens dybeste og ældste ferskvandssø.');
INSERT INTO public.funfact VALUES (918, 'Golf blev opfundet i Skotland i det 15. århundrede.');
INSERT INTO public.funfact VALUES (919, 'Det første olympiske marathonløb blev afholdt i 1896 i Athen');
INSERT INTO public.funfact VALUES (920, 'Atacama-ørkenen i Chile anses for at være en af verdens tørreste steder.');
INSERT INTO public.funfact VALUES (921, 'Traditionelle japanske te-ceremonier har rødder tilbage til det 9. århundrede.');
INSERT INTO public.funfact VALUES (922, 'World Wide Web blev opfundet af den britiske fysiker Tim Berners-Lee i 1989.');
INSERT INTO public.funfact VALUES (923, 'De første moderne olympiske lege blev afholdt i Athen, i 1896.');
INSERT INTO public.funfact VALUES (924, 'Brasilien har verdens største regnskov, Amazonas på omkring 5.5 mio kvadratkilometer');
INSERT INTO public.funfact VALUES (925, 'Den kinesiske mur er omkring 21.196 kilometer lang ');
INSERT INTO public.funfact VALUES (926, 'Den første mobiltelefon blev opfundet af Martin Cooper fra Motorola');
INSERT INTO public.funfact VALUES (927, 'Planeten Saturn har mindst 82 kendte måner');
INSERT INTO public.funfact VALUES (928, 'Den antikke by Rom blev grundlagt i år 753 f.Kr. ifølge legenden.');
INSERT INTO public.funfact VALUES (929, 'Manater, også kendt som søkøer, er fredelige havdyr, der kan veje op til 1.300 kg');
INSERT INTO public.funfact VALUES (930, '3D-printere blev først udviklet i 1980''erne');
INSERT INTO public.funfact VALUES (1001, 'Baseball blev officielt anerkendt som en nationalsport i USA i 1869.');
INSERT INTO public.funfact VALUES (1002, 'Den klassiske ballet Svanesøen blev først opført i Moskva i 1877 ');
INSERT INTO public.funfact VALUES (1003, 'Månen er cirka 4,5 milliarder år gammel ');
INSERT INTO public.funfact VALUES (1004, 'Den første moderne computer, ENIAC, blev bygget i 1946');
INSERT INTO public.funfact VALUES (1005, 'Den første moderne computer, ENIAC, fyldte et helt rum.');
INSERT INTO public.funfact VALUES (1006, 'Tennis blev oprindeligt spillet med hænderne i stedet for rackete');
INSERT INTO public.funfact VALUES (1007, 'Coca-Cola blev opfundet i 1886 af apotekeren John Pemberton i Atlanta');
INSERT INTO public.funfact VALUES (1008, 'Den afrikanske elefant er verdens største landlevende dyr og kan veje op til 6 ton.');
INSERT INTO public.funfact VALUES (1009, 'Den længste flod i verden er Nilen, der strækker sig over ca. 6.650 km ');
INSERT INTO public.funfact VALUES (1010, 'Oktoberfest, en årlig ølfestival, der afholdes i München, Tyskland');
INSERT INTO public.funfact VALUES (1011, 'Chokolade blev opdaget for over 3.000 år siden i Mexico');
INSERT INTO public.funfact VALUES (1012, 'Lyn kan faktisk forekomme i forskellige farver, herunder rød, grøn og blå');
INSERT INTO public.funfact VALUES (1013, 'Det første postkort blev sendt i Østrig i 1869');
INSERT INTO public.funfact VALUES (1014, 'En kolibri er den mindste fugl i verden');
INSERT INTO public.funfact VALUES (1015, 'Det højeste punkt på Jorden, Mount Everest, er 8.848 meter over havets overflade.');
INSERT INTO public.funfact VALUES (1016, 'Sushi, en populær japansk ret, blev først introduceret i USA i 1960''erne');
INSERT INTO public.funfact VALUES (1017, 'Den mindste planet i vores solsystem er Merkur');
INSERT INTO public.funfact VALUES (1018, 'Merkur har en diameter på kun ca. 4.880 kilometer.');
INSERT INTO public.funfact VALUES (1019, 'Google blev grundlagt af Larry Page og Sergey Brin i 1998');
INSERT INTO public.funfact VALUES (1020, 'fodbold, er verdens mest populære sport');
INSERT INTO public.funfact VALUES (1021, 'Regnskovene dækker kun omkring 6% af Jorden');
INSERT INTO public.funfact VALUES (1022, 'Regnskovene er hjemsted for over halvdelen af ​​verdens plante- og dyrearter.');
INSERT INTO public.funfact VALUES (1023, 'Den største ø i Middelhavet er Sicilien');
INSERT INTO public.funfact VALUES (1024, 'Det første moderne GPS-satellitsystem blev gjort tilgængeligt for civile i 1980''erne.');
INSERT INTO public.funfact VALUES (1025, 'Den første flyvning med et luftskib blev foretaget  i 1783.');
INSERT INTO public.funfact VALUES (1026, 'Bjergbestigeren Reinhold Messner blev den første mand til at bestige Mount Everest');
INSERT INTO public.funfact VALUES (1027, 'Den første Harry Potter-bog blev udgivet i 1997 ');
INSERT INTO public.funfact VALUES (1028, 'Shakespeare opfandt ordet swagger.');
INSERT INTO public.funfact VALUES (1029, 'Elvis Presley havde en tvillingbror som desværre døde ved fødslen.');
INSERT INTO public.funfact VALUES (1030, 'Under 2. verdenskrig blev Københavns zoologiske have holdt åben og dyrene blev skjult,');
INSERT INTO public.funfact VALUES (1031, 'QR-koden blev opfundet i Japan i 1994 af Masahiro Hara.');
INSERT INTO public.funfact VALUES (1101, 'Der er flere end 10.000 forskellige sorter af æbler i verden.');
INSERT INTO public.funfact VALUES (1102, 'Der er flere tigre i fangeskab i USA end der er i naturen rundt om i verden.');
INSERT INTO public.funfact VALUES (1103, 'Internettet blev opfundet i slutningen af 1960''erne af en gruppe forskere ');
INSERT INTO public.funfact VALUES (1104, 'Shakespeare er kendt for at have opfundet mere end 1.700 engelske ord.');
INSERT INTO public.funfact VALUES (1105, 'Det første fotografi blev taget i 1826 af Joseph Nicéphore Niépce.');
INSERT INTO public.funfact VALUES (1106, 'Det laveste punkt på landjorden er Dødehavet, som ligger ca. 430 meter under havoverfladen.');
INSERT INTO public.funfact VALUES (1107, 'Tidligere amerikansk præsident Barack Obama er en stor basketballfan');
INSERT INTO public.funfact VALUES (1108, 'Der er flere end 10.000 forskellige sorter af tomater verden over.');
INSERT INTO public.funfact VALUES (1109, 'Maleren Vincent van Gogh skabte mere end 2.100 kunstværker');
INSERT INTO public.funfact VALUES (1110, 'Vagtelæg er en af de mindste fugleæg og kan være så små som en druekjerne.');
INSERT INTO public.funfact VALUES (1111, 'Den længste flod i Europa er Volga-floden, der strækker sig over omkring 3.500 kilometer.');
INSERT INTO public.funfact VALUES (1112, 'Den første smartphone blev introduceret af IBM i 1992 og blev kaldt Simon Personal Communicator.');
INSERT INTO public.funfact VALUES (1113, 'Nelson Mandela , tilbragte 27 år i fængsel før han blev løsladt i 1990.');
INSERT INTO public.funfact VALUES (1114, 'Nelson Mandela, Sydafrikas første demokratisk valgte præsident');
INSERT INTO public.funfact VALUES (1115, 'Den største ø i verden er Grønland, der tilhører Kongeriget Danmark.');
INSERT INTO public.funfact VALUES (1116, 'Den første tegnede Mickey Mouse-film var Steamboat Willie fra 1928');
INSERT INTO public.funfact VALUES (1117, 'Spædbørn har flere knogler end voksne');
INSERT INTO public.funfact VALUES (1118, 'Den første rumrejse blev gennemført af Sovjetunionen i 1961 med Yuri Gagarin');
INSERT INTO public.funfact VALUES (1119, 'Den dybeste kløft i verden er Grand Canyon i USA, som når næsten 1,8 kilometer.');
INSERT INTO public.funfact VALUES (1120, 'Tomater, der hører til natskyggefamilien');
INSERT INTO public.funfact VALUES (1121, 'Titanic, som var verdens største passagerskib  i 1912');
INSERT INTO public.funfact VALUES (1122, 'Titanic sank på sin jomfrurejse i 1912 efter at have ramt et isbjerg.');
INSERT INTO public.funfact VALUES (1123, 'kateboarding blev først populært i Californien, USA  i slutningen af 1950''erne.');
INSERT INTO public.funfact VALUES (1124, 'Basketball blev opfundet af James Naismith i 1891 i Springfield, Massachusetts, USA.');
INSERT INTO public.funfact VALUES (1125, 'Menneskehjernen indeholder omkring 86 milliarder neuroner.');
INSERT INTO public.funfact VALUES (1126, 'Menneskehjernen kan behandle op til 100 terabytes data');
INSERT INTO public.funfact VALUES (1127, 'Regnbuer dannes, når sollys brydes og reflekteres gennem regndråber i atmosfæren');
INSERT INTO public.funfact VALUES (1128, 'Den dybeste kløft i verden, Mariana Trench, ligger i Stillehavet og er over 11 kilometer dyb.');
INSERT INTO public.funfact VALUES (1129, 'Den første internetmeddelelse blev sendt i 1969 og bestod blot af bogstaverne LO.');
INSERT INTO public.funfact VALUES (1130, 'Kiwifrugt blev oprindeligt kaldt kinesisk stikkelsbær, indtil det blev omdøbt til kiwi i 1959');
INSERT INTO public.funfact VALUES (1201, 'Chokolade blev først introduceret i Europa af de spanske opdagelsesrejsende i det 16. århundrede.');
INSERT INTO public.funfact VALUES (1202, 'Det mest populære navn i verden er Muhammad');
INSERT INTO public.funfact VALUES (1203, 'Jordens atmosfære består hovedsageligt af kvælstof (ca. 78%) og ilt (ca. 21%).');
INSERT INTO public.funfact VALUES (1204, 'fodbold spilles af mere end 250 millioner mennesker i over 200 lande');
INSERT INTO public.funfact VALUES (1205, 'Den første vellykkede flyvning af et fly blev udført af brødrene Wright i 1903 ');
INSERT INTO public.funfact VALUES (1206, 'Antarktis indeholder omkring 70% af verdens ferskvandsforsyning.');
INSERT INTO public.funfact VALUES (1207, 'Pulp Fiction af Quentin Tarantino blev udgivet i 1994 ');
INSERT INTO public.funfact VALUES (1208, 'Et egerns hjerte slår omkring 300 gange i minuttet');
INSERT INTO public.funfact VALUES (1209, 'Himalaya-bjergkæden, der strækker sig over fem lande');
INSERT INTO public.funfact VALUES (1210, 'Avocado er faktisk en frugt, ikke en grøntsag');
INSERT INTO public.funfact VALUES (1211, 'Great Barrier Reef, beliggende ud for kysten af Queensland er verdens største koralrevssystem.');
INSERT INTO public.funfact VALUES (1212, 'Det mest solgte musikalbum nogensinde er Thriller af Michael Jackson, der blev udgivet i 1982.');
INSERT INTO public.funfact VALUES (1213, 'Den første vellykkede hjerteoperation blev udført i 1893 ');
INSERT INTO public.funfact VALUES (1214, 'Martin Luther King Jr. holdt sin berømte I Have a Dream tale den 28. august 1963');
INSERT INTO public.funfact VALUES (1215, 'Bluetooth-teknologien blev opkaldt efter en dansk konge, Harald Blåtand');
INSERT INTO public.funfact VALUES (1216, 'Isbjørne er faktisk sorte, og deres hvide pels er transparent.');
INSERT INTO public.funfact VALUES (1217, 'Den mest almindelige skade i volleyball er ankelvridning.');
INSERT INTO public.funfact VALUES (1218, 'Nogle planter, som f.eks. broccoli og blomkål, er faktisk blomsterknopper ');
INSERT INTO public.funfact VALUES (1219, 'En bi''s vingeslag er så hurtigt, at de kan slå deres vinger mere end 200 gange i sekundet.');
INSERT INTO public.funfact VALUES (1220, 'Christopher Columbus troede oprindeligt, at han havde nået Asien, da han nåede  i Amerika i 1492.');
INSERT INTO public.funfact VALUES (1221, 'Manater har en forventet levetid på ca. 60 år i naturen.');
INSERT INTO public.funfact VALUES (1222, 'Golfbolde har 336 små fordybninger kaldet dimples');
INSERT INTO public.funfact VALUES (1223, 'Den ældste kendte vinproduktion stammer fra Georgia og går tilbage til omkring 6.000 f.Kr.');
INSERT INTO public.funfact VALUES (1224, 'Den italienske renæssancemaler Leonardo da Vinci var venstrehåndet.');
INSERT INTO public.funfact VALUES (1225, 'Flyvende fisk kan glide i luften i flere hundrede meter');
INSERT INTO public.funfact VALUES (1226, 'Afrikas højeste punkt er Kilimanjaro, der stiger til 5.895 meter over havets overflade.');
INSERT INTO public.funfact VALUES (1227, 'Oktopusser har halvdelen af deres neuroner i deres arme, ikke i hjernen.');
INSERT INTO public.funfact VALUES (1228, 'USB-stikket blev opfundet i midten af 1990''erne');
INSERT INTO public.funfact VALUES (1229, 'Tennisbolde blev oprindeligt lavet af læder og blev brugt i det 16. århundrede');
INSERT INTO public.funfact VALUES (1230, 'Amazonasregnskoven i Sydamerika er hjemsted for omkring 10% af Jordens dyrearter.');
INSERT INTO public.funfact VALUES (1231, 'På trods af sit navn er koalabjørnen faktisk ikke en bjørn, men en pungdyr.');


--
-- TOC entry 3206 (class 2606 OID 24675)
-- Name: funfact funfact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funfact
    ADD CONSTRAINT funfact_pkey PRIMARY KEY (date);


-- Completed on 2024-03-22 12:33:28 UTC

--
-- PostgreSQL database dump complete
--

