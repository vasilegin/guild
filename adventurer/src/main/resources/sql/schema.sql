--
-- PostgreSQL database dump
--

-- Dumped from database version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.12 (Ubuntu 12.12-0ubuntu0.20.04.1)

-- Started on 2022-12-11 21:25:56 MSK

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
-- TOC entry 202 (class 1259 OID 70267)
-- Name: adventurer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.adventurer (
    id bigint NOT NULL,
    "Rank" character varying,
    "Role" character varying,
    "Weapon" character varying
);

--
-- TOC entry 205 (class 1259 OID 71117)
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.groups (
    id bigint NOT NULL,
    "Name" character varying,
    "Active" boolean
);


--
-- TOC entry 203 (class 1259 OID 70290)
-- Name: guild_staff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.guild_staff (
    id bigint NOT NULL,
    "Position" character varying
);

--
-- TOC entry 207 (class 1259 OID 71533)
-- Name: jobs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.jobs (
    id bigint NOT NULL,
    "Customer_id" bigint,
    "Adventurer_id" bigint,
    "Group_id" bigint,
    "Title" character varying,
    "Description" text,
    "Rank" character varying,
    "Reward" numeric,
    "Location" character varying,
    "Status" character varying,
    "Date_created" timestamp with time zone,
    "Date_posted" timestamp with time zone,
    "Date_accepted" timestamp with time zone,
    "Date_resolved" timestamp with time zone
);

--
-- TOC entry 206 (class 1259 OID 71326)
-- Name: participates; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.participates (
    "Group_id" bigint,
    "Adventurer_id" bigint,
    "Role" character varying,
    "Date_joined" date,
    "Date_left" date
);
--
-- TOC entry 210 (class 1259 OID 71766)
-- Name: photos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.photos (
    id bigint NOT NULL,
    "Location" character varying,
    "Job_id" bigint,
    "Report_id" bigint,
    "Review_id" bigint,
    "Group_id" bigint,
    "User_id" bigint
);

--
-- TOC entry 208 (class 1259 OID 71583)
-- Name: reports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.reports (
    id bigint NOT NULL,
    "Job_id" bigint,
    "Text" text
);

--
-- TOC entry 209 (class 1259 OID 71660)
-- Name: reviews; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.reviews (
    id bigint NOT NULL,
    "Job_id" bigint,
    "Author_id" bigint,
    "Text" text,
    "Score" bigint
);

--
-- TOC entry 204 (class 1259 OID 70875)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE IF NOT EXISTS public.users (
    id bigint NOT NULL,
    "Login" character varying,
    "Password" character varying,
    "Birthday" date,
    "Gender" boolean,
    "Surname" character varying,
    "Firstname" character varying,
    "Email" character varying,
    "Phone_number" character varying,
    "Adventurer_id" bigint,
    "Guild_staff_id" bigint,
    "Balance" numeric
);

--
-- TOC entry 2872 (class 2606 OID 70274)
-- Name: adventurer adventurer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE public.adventurer
    ADD CONSTRAINT adventurer_pkey PRIMARY KEY (id);


--
-- TOC entry 2878 (class 2606 OID 71124)
-- Name: groups groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- TOC entry 2874 (class 2606 OID 70297)
-- Name: guild_staff guild_staff_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.guild_staff
    ADD CONSTRAINT guild_staff_pkey PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 71540)
-- Name: jobs jobs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobs
    ADD CONSTRAINT jobs_pkey PRIMARY KEY (id);


--
-- TOC entry 2886 (class 2606 OID 71773)
-- Name: photos photos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT photos_pkey PRIMARY KEY (id);


--
-- TOC entry 2882 (class 2606 OID 71590)
-- Name: reports reports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT reports_pkey PRIMARY KEY (id);


--
-- TOC entry 2884 (class 2606 OID 71667)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 2876 (class 2606 OID 70882)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2887 (class 2606 OID 70883)
-- Name: users Adventurer_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "Adventurer_FK" FOREIGN KEY ("Adventurer_id") REFERENCES public.adventurer(id);


--
-- TOC entry 2890 (class 2606 OID 71337)
-- Name: participates Adventurer_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participates
    ADD CONSTRAINT "Adventurer_FK" FOREIGN KEY ("Adventurer_id") REFERENCES public.users(id);


--
-- TOC entry 2892 (class 2606 OID 71546)
-- Name: jobs Adventurer_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobs
    ADD CONSTRAINT "Adventurer_FK" FOREIGN KEY ("Adventurer_id") REFERENCES public.users(id);


--
-- TOC entry 2896 (class 2606 OID 71673)
-- Name: reviews Author_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT "Author_FK" FOREIGN KEY ("Author_id") REFERENCES public.users(id);


--
-- TOC entry 2891 (class 2606 OID 71541)
-- Name: jobs Customer_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobs
    ADD CONSTRAINT "Customer_FK" FOREIGN KEY ("Customer_id") REFERENCES public.users(id);


--
-- TOC entry 2889 (class 2606 OID 71332)
-- Name: participates Group_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.participates
    ADD CONSTRAINT "Group_FK" FOREIGN KEY ("Group_id") REFERENCES public.groups(id);


--
-- TOC entry 2893 (class 2606 OID 71551)
-- Name: jobs Group_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.jobs
    ADD CONSTRAINT "Group_FK" FOREIGN KEY ("Group_id") REFERENCES public.groups(id);


--
-- TOC entry 2900 (class 2606 OID 72053)
-- Name: photos Group_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT "Group_FK" FOREIGN KEY ("Group_id") REFERENCES public.groups(id) NOT VALID;


--
-- TOC entry 2888 (class 2606 OID 70888)
-- Name: users Guild_staff_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "Guild_staff_FK" FOREIGN KEY ("Guild_staff_id") REFERENCES public.guild_staff(id);


--
-- TOC entry 2894 (class 2606 OID 71591)
-- Name: reports Job_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reports
    ADD CONSTRAINT "Job_FK" FOREIGN KEY ("Job_id") REFERENCES public.jobs(id);


--
-- TOC entry 2895 (class 2606 OID 71668)
-- Name: reviews Job_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT "Job_FK" FOREIGN KEY ("Job_id") REFERENCES public.jobs(id);


--
-- TOC entry 2897 (class 2606 OID 72038)
-- Name: photos Job_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT "Job_FK" FOREIGN KEY ("Job_id") REFERENCES public.jobs(id) NOT VALID;


--
-- TOC entry 2898 (class 2606 OID 72043)
-- Name: photos Report_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT "Report_FK" FOREIGN KEY ("Report_id") REFERENCES public.reports(id) NOT VALID;


--
-- TOC entry 2899 (class 2606 OID 72048)
-- Name: photos Review_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT "Review_FK" FOREIGN KEY ("Review_id") REFERENCES public.reviews(id) NOT VALID;


--
-- TOC entry 2901 (class 2606 OID 72058)
-- Name: photos User_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.photos
    ADD CONSTRAINT "User_FK" FOREIGN KEY ("User_id") REFERENCES public.users(id) NOT VALID;


-- Completed on 2022-12-11 21:25:56 MSK

--
-- PostgreSQL database dump complete
--