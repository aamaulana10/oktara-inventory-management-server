PGDMP         -                }            oktara    15.2    15.2 7    S           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            T           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            U           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            V           1262    22483    oktara    DATABASE     h   CREATE DATABASE oktara WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C';
    DROP DATABASE oktara;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            W           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            V           1247    22542    transaction_type    TYPE     E   CREATE TYPE public.transaction_type AS ENUM (
    'IN',
    'OUT'
);
 #   DROP TYPE public.transaction_type;
       public          postgres    false    4            �            1259    22588    delivery_order_items    TABLE     5  CREATE TABLE public.delivery_order_items (
    id integer NOT NULL,
    delivery_order_id integer NOT NULL,
    item_id integer NOT NULL,
    quantity integer NOT NULL,
    unit character varying(20) NOT NULL,
    description text,
    CONSTRAINT delivery_order_items_quantity_check CHECK ((quantity > 0))
);
 (   DROP TABLE public.delivery_order_items;
       public         heap    postgres    false    4            �            1259    22587    delivery_order_items_id_seq    SEQUENCE     �   CREATE SEQUENCE public.delivery_order_items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.delivery_order_items_id_seq;
       public          postgres    false    223    4            X           0    0    delivery_order_items_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.delivery_order_items_id_seq OWNED BY public.delivery_order_items.id;
          public          postgres    false    222            �            1259    22570    delivery_orders    TABLE     o  CREATE TABLE public.delivery_orders (
    id integer NOT NULL,
    delivery_number character varying(50) NOT NULL,
    delivery_date date DEFAULT CURRENT_DATE NOT NULL,
    recipient_name character varying(100) NOT NULL,
    recipient_address text,
    description text,
    created_by integer,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
 #   DROP TABLE public.delivery_orders;
       public         heap    postgres    false    4            �            1259    22569    delivery_orders_id_seq    SEQUENCE     �   CREATE SEQUENCE public.delivery_orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.delivery_orders_id_seq;
       public          postgres    false    221    4            Y           0    0    delivery_orders_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.delivery_orders_id_seq OWNED BY public.delivery_orders.id;
          public          postgres    false    220            �            1259    22548    inventory_transactions    TABLE     �  CREATE TABLE public.inventory_transactions (
    id integer NOT NULL,
    item_id integer NOT NULL,
    quantity integer NOT NULL,
    transaction_type character varying NOT NULL,
    transaction_date date DEFAULT CURRENT_DATE NOT NULL,
    description text,
    created_by integer,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    price double precision DEFAULT 0 NOT NULL,
    CONSTRAINT inventory_transactions_quantity_check CHECK ((quantity > 0))
);
 *   DROP TABLE public.inventory_transactions;
       public         heap    postgres    false    4            �            1259    22547    inventory_transactions_id_seq    SEQUENCE     �   CREATE SEQUENCE public.inventory_transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.inventory_transactions_id_seq;
       public          postgres    false    4    219            Z           0    0    inventory_transactions_id_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.inventory_transactions_id_seq OWNED BY public.inventory_transactions.id;
          public          postgres    false    218            �            1259    22516    items    TABLE       CREATE TABLE public.items (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    sku character varying(50),
    unit character varying(20) DEFAULT 'pcs'::character varying NOT NULL,
    stock integer DEFAULT 0 NOT NULL,
    min_stock integer DEFAULT 0,
    location character varying(100),
    description text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    created_by integer,
    updated_by integer
);
    DROP TABLE public.items;
       public         heap    postgres    false    4            �            1259    22515    items_id_seq    SEQUENCE     �   CREATE SEQUENCE public.items_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.items_id_seq;
       public          postgres    false    217    4            [           0    0    items_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id;
          public          postgres    false    216            �            1259    22485    roles    TABLE     v   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    description text
);
    DROP TABLE public.roles;
       public         heap    postgres    false    4            �            1259    22484    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    215    4            \           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    214            �            1259    22651    users    TABLE     �  CREATE TABLE public.users (
    role_id integer NOT NULL,
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    full_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.users;
       public         heap    postgres    false    4            �            1259    22650    users_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    4    225            �           2604    22591    delivery_order_items id    DEFAULT     �   ALTER TABLE ONLY public.delivery_order_items ALTER COLUMN id SET DEFAULT nextval('public.delivery_order_items_id_seq'::regclass);
 F   ALTER TABLE public.delivery_order_items ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222    223            �           2604    22573    delivery_orders id    DEFAULT     x   ALTER TABLE ONLY public.delivery_orders ALTER COLUMN id SET DEFAULT nextval('public.delivery_orders_id_seq'::regclass);
 A   ALTER TABLE public.delivery_orders ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    221    221            �           2604    22551    inventory_transactions id    DEFAULT     �   ALTER TABLE ONLY public.inventory_transactions ALTER COLUMN id SET DEFAULT nextval('public.inventory_transactions_id_seq'::regclass);
 H   ALTER TABLE public.inventory_transactions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    22519    items id    DEFAULT     d   ALTER TABLE ONLY public.items ALTER COLUMN id SET DEFAULT nextval('public.items_id_seq'::regclass);
 7   ALTER TABLE public.items ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            �           2604    22488    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            N          0    22588    delivery_order_items 
   TABLE DATA           k   COPY public.delivery_order_items (id, delivery_order_id, item_id, quantity, unit, description) FROM stdin;
    public          postgres    false    223   �D       L          0    22570    delivery_orders 
   TABLE DATA           �   COPY public.delivery_orders (id, delivery_number, delivery_date, recipient_name, recipient_address, description, created_by, created_at) FROM stdin;
    public          postgres    false    221   �D       J          0    22548    inventory_transactions 
   TABLE DATA           �   COPY public.inventory_transactions (id, item_id, quantity, transaction_type, transaction_date, description, created_by, created_at, price) FROM stdin;
    public          postgres    false    219   }E       H          0    22516    items 
   TABLE DATA           �   COPY public.items (id, name, sku, unit, stock, min_stock, location, description, created_at, updated_at, created_by, updated_by) FROM stdin;
    public          postgres    false    217   1F       F          0    22485    roles 
   TABLE DATA           6   COPY public.roles (id, name, description) FROM stdin;
    public          postgres    false    215   )G       P          0    22651    users 
   TABLE DATA           j   COPY public.users (role_id, id, email, full_name, password, username, created_at, updated_at) FROM stdin;
    public          postgres    false    225   �G       ]           0    0    delivery_order_items_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.delivery_order_items_id_seq', 2, true);
          public          postgres    false    222            ^           0    0    delivery_orders_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.delivery_orders_id_seq', 1, true);
          public          postgres    false    220            _           0    0    inventory_transactions_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.inventory_transactions_id_seq', 3, true);
          public          postgres    false    218            `           0    0    items_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.items_id_seq', 10, true);
          public          postgres    false    216            a           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 3, true);
          public          postgres    false    214            b           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 15, true);
          public          postgres    false    224            �           2606    22596 .   delivery_order_items delivery_order_items_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.delivery_order_items
    ADD CONSTRAINT delivery_order_items_pkey PRIMARY KEY (id);
 X   ALTER TABLE ONLY public.delivery_order_items DROP CONSTRAINT delivery_order_items_pkey;
       public            postgres    false    223            �           2606    22581 3   delivery_orders delivery_orders_delivery_number_key 
   CONSTRAINT     y   ALTER TABLE ONLY public.delivery_orders
    ADD CONSTRAINT delivery_orders_delivery_number_key UNIQUE (delivery_number);
 ]   ALTER TABLE ONLY public.delivery_orders DROP CONSTRAINT delivery_orders_delivery_number_key;
       public            postgres    false    221            �           2606    22579 $   delivery_orders delivery_orders_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.delivery_orders
    ADD CONSTRAINT delivery_orders_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.delivery_orders DROP CONSTRAINT delivery_orders_pkey;
       public            postgres    false    221            �           2606    22558 2   inventory_transactions inventory_transactions_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.inventory_transactions
    ADD CONSTRAINT inventory_transactions_pkey PRIMARY KEY (id);
 \   ALTER TABLE ONLY public.inventory_transactions DROP CONSTRAINT inventory_transactions_pkey;
       public            postgres    false    219            �           2606    22528    items items_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.items DROP CONSTRAINT items_pkey;
       public            postgres    false    217            �           2606    22530    items items_sku_key 
   CONSTRAINT     M   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_sku_key UNIQUE (sku);
 =   ALTER TABLE ONLY public.items DROP CONSTRAINT items_sku_key;
       public            postgres    false    217            �           2606    22494    roles roles_name_key 
   CONSTRAINT     O   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);
 >   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_name_key;
       public            postgres    false    215            �           2606    22492    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    215            �           2606    22659    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    225            �           2606    22657    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    225            �           2606    22661    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    225            �           2606    22597 @   delivery_order_items delivery_order_items_delivery_order_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.delivery_order_items
    ADD CONSTRAINT delivery_order_items_delivery_order_id_fkey FOREIGN KEY (delivery_order_id) REFERENCES public.delivery_orders(id) ON DELETE CASCADE;
 j   ALTER TABLE ONLY public.delivery_order_items DROP CONSTRAINT delivery_order_items_delivery_order_id_fkey;
       public          postgres    false    223    221    3499            �           2606    22602 6   delivery_order_items delivery_order_items_item_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.delivery_order_items
    ADD CONSTRAINT delivery_order_items_item_id_fkey FOREIGN KEY (item_id) REFERENCES public.items(id) ON DELETE CASCADE;
 `   ALTER TABLE ONLY public.delivery_order_items DROP CONSTRAINT delivery_order_items_item_id_fkey;
       public          postgres    false    223    217    3491            �           2606    22559 :   inventory_transactions inventory_transactions_item_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.inventory_transactions
    ADD CONSTRAINT inventory_transactions_item_id_fkey FOREIGN KEY (item_id) REFERENCES public.items(id) ON DELETE CASCADE;
 d   ALTER TABLE ONLY public.inventory_transactions DROP CONSTRAINT inventory_transactions_item_id_fkey;
       public          postgres    false    3491    217    219            N   U   x�3�4AΤĒļtN���L������r�Ҽ��l������lG.#�B# ��,I-�tN,Q(I�M��(-��P02������ ���      L   �   x�%�A
�0 ϛW�Lؤ	���S#�(x�`)�6�� ��Ua3��pҐ�d�K"ߐ��a{�=���C�ilҭ���0*�~�;������M]�q��%M��g�����Emkgjk�3�ZW⪄��#�      J   �   x�}�=�0��9�/�*?"�:� l,)�hh�V��=)RY�`yx��6ۼ�.8��ާ���:��T�&��l����͢ KkKTj�HK�L怆���Kv�0�����0�/�b�?�2%h��3u}����ar8Lg�E��J[�������yةd��98�      H   �   x����N�0���S����nu�h����Q���Ub�}|L7�:}�}:��-�a��U���zV�Q$�y�Ko9�>�C��di�i$���H.5��[j��V�Mɕ�7���BBE�~��&E��R�ڵ�yj�k�����栿�̗P��q�/4y��R�\�n�W��%4��>��u�恙��tu w�4ȟo�+�-H�19t��qJn��Py�E���o�      F   v   x�M�A
1F�ur��@P� ��n����4�2�Wq���������Y�� B�K�!�E��"u]�b��/j�z��Ox���z��j�Q0��4/:�K����2��#�h*_w���1      P   `  x��λ��0 ��:<��H�򎸫���:6�*J	W�~�[a���f�|@@DݘXIp*�LA�V�ŽlA�kQ�Hj��`�:[��ݳ��X�e[^���R��Y���z8��z�$"�.Ju(�vd�A��(J�L��x���Vx5sH�lif!��~e�S�g0#���65���pҏ.�׷��(�~�������-��g�_���h�hi��Z�1�7z�Z���tt܍7#�&V��ӳ?�ڨ���1���R���OI�e��g�M^���.�Aa�Վ�U���f��,�R�h�c`�ع\iSZU���F$߼��J���n7��s{��?���     