PGDMP         ;                y            GrupoDot    13.2    13.2 	    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    24780    GrupoDot    DATABASE     f   CREATE DATABASE "GrupoDot" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "GrupoDot";
                postgres    false            ?            1259    24791    socio    TABLE     ?   CREATE TABLE public.socio (
    id integer NOT NULL,
    nombre character varying(50) NOT NULL,
    tasa real NOT NULL,
    monto_maximo bigint NOT NULL
);
    DROP TABLE public.socio;
       public         heap    postgres    false            ?            1259    24796    socio_id_seq    SEQUENCE     ?   ALTER TABLE public.socio ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.socio_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    200            ?          0    24791    socio 
   TABLE DATA           ?   COPY public.socio (id, nombre, tasa, monto_maximo) FROM stdin;
    public          postgres    false    200   i       ?           0    0    socio_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.socio_id_seq', 5, true);
          public          postgres    false    201            #           2606    24795    socio socio_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.socio
    ADD CONSTRAINT socio_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.socio DROP CONSTRAINT socio_pkey;
       public            postgres    false    200            ?   F   x?3?t?K):???ӈ??? ??9}??M?4?3?46??????Ō?9?J??SNS??=...  ??     