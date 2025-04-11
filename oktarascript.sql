-- roles table
CREATE TABLE roles (
 id SERIAL PRIMARY KEY,
 name VARCHAR(50) NOT NULL UNIQUE,
 description TEXT
);

-- users rable
create table users (
 id SERIAL PRIMARY KEY,
 full_name VARCHAR(100) NOT NULL,
 username VARCHAR(50) NOT NULL UNIQUE,
 email VARCHAR(100) UNIQUE,
 password TEXT NOT NULL,
 role_id INTEGER REFERENCES roles(id) ON DELETE SET NULL,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- seed roles
INSERT INTO roles (name, description) VALUES
('admin', 'Full access to the system'),
('staff', 'Limited access to manage inventory and delivery orders'),
('viewer', 'Can only view reports and data');

-- seed users
INSERT INTO users (full_name, username, email, password, role_id) VALUES
('Admin PabrikX', 'admin', 'admin@pabrikx.com', 'admin123', 1),
('Staff Gudang', 'staff', 'staff@pabrikx.com', 'staff123', 2),
('Viewer 1', 'viewer1', 'viewer@pabrikx.com', 'viewer123', 3);

-- items table
CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    sku VARCHAR(50) UNIQUE, -- kode barang unik
    unit VARCHAR(20) NOT NULL DEFAULT 'pcs', -- satuan (pcs, kg, liter, dll)
    stock INTEGER NOT NULL DEFAULT 0, -- akan update otomatis lewat transaksi / pengiriman
    min_stock INTEGER DEFAULT 0, -- buat reminder kalau stok menipis
    location VARCHAR(100), -- lokasi penyimpanan (rak, gudang, dll)
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- items table tambah update by
ALTER TABLE items
ADD COLUMN created_by INTEGER REFERENCES users(id) ON DELETE SET NULL,
ADD COLUMN updated_by INTEGER REFERENCES users(id) ON DELETE SET NULL;

-- seed items
INSERT INTO items (name, sku, unit, stock, min_stock, location, description, created_by, updated_by) VALUES
('Besi Hollow 4x4', 'BH-4X4', 'batang', 100, 10, 'Gudang A - Rak 1', 'Material utama rangka',1,1 ),
('Cat Tembok Putih 25L', 'CAT-PUTIH-25L', 'liter', 50, 5, 'Gudang B - Rak 3', 'Cat tembok warna putih ukuran 25 liter',1,1),
('Paku Beton 5cm', 'PK-BTN-5CM', 'kg', 20, 2, 'Gudang A - Rak 5', 'Paku beton untuk konstruksi',1,1);

-- inventory_transactions table
CREATE TYPE transaction_type AS ENUM ('IN', 'OUT'); -- IN bertambah, OUT berkurang

CREATE TABLE inventory_transactions (
    id SERIAL PRIMARY KEY,
    item_id INTEGER NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    transaction_type transaction_type NOT NULL, -- IN atau OUT
    transaction_date DATE NOT NULL DEFAULT CURRENT_DATE,
    description TEXT,
    created_by INTEGER REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- items table tambah update by
ALTER TABLE inventory_transactions
ADD COLUMN price DOUBLE PRECISION NOT NULL DEFAULT 0;

-- seed inventory_transactions
-- Tambah stok (barang masuk)
INSERT INTO inventory_transactions (item_id, quantity, transaction_type, description, created_by)
VALUES
(1, 20, 'IN', 'Pembelian tambahan besi hollow', 1);

-- Kurangi stok (barang keluar)
INSERT INTO inventory_transactions (item_id, quantity, transaction_type, description, created_by)
VALUES
(1, 5, 'OUT', 'Pengiriman ke proyek A', 2);

-- delivery_orders table (surat jalan)
CREATE TABLE delivery_orders (
    id SERIAL PRIMARY KEY,
    delivery_number VARCHAR(50) UNIQUE NOT NULL, -- nomor surat jalan
    delivery_date DATE NOT NULL DEFAULT CURRENT_DATE,
    recipient_name VARCHAR(100) NOT NULL,
    recipient_address TEXT,
    description TEXT,
    created_by INTEGER REFERENCES users(id) ON DELETE SET NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- delivery_order_items table (item di surat jalan) , karena satu order bisa banyak items
CREATE TABLE delivery_order_items (
    id SERIAL PRIMARY KEY,
    delivery_order_id INTEGER NOT NULL REFERENCES delivery_orders(id) ON DELETE CASCADE,
    item_id INTEGER NOT NULL REFERENCES items(id) ON DELETE CASCADE,
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    unit VARCHAR(20) NOT NULL, -- bisa ambil default dari items.unit
    description TEXT
);

-- seed delivery_orders dan delivery_order_items
-- Buat surat jalan
INSERT INTO delivery_orders (delivery_number, delivery_date, recipient_name, recipient_address, description, created_by)
VALUES
('SJ-20240409-001', '2024-04-09', 'CV Maju Jaya', 'Jl. Raya Industri No. 99, Karawang', 'Pengiriman untuk proyek A', 1);

-- Tambahkan item ke surat jalan
INSERT INTO delivery_order_items (delivery_order_id, item_id, quantity, unit, description)
VALUES
(1, 1, 10, 'batang', 'Besi hollow untuk proyek A'),
(1, 2, 2, 'liter', 'Cat tembok putih 25L');







