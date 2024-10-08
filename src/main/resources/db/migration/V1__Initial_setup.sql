CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TYPE ride_status AS ENUM ('REQUESTED', 'DISPATCHED', 'IN_TRANSIT', 'COMPLETED', 'CANCELLED');
CREATE TYPE gender AS ENUM ('Male', 'Female', 'Others');
CREATE TYPE preferred_payment_method AS ENUM ('Credit Card', 'PayPal', 'Cash');

CREATE TABLE IF NOT EXISTS vehicle (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INTEGER,
    license_plate VARCHAR(255) NOT NULL UNIQUE,
    color VARCHAR(255),
    car_type VARCHAR(255),
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS driver (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255),
    dob VARCHAR(255),
    gender gender,
    avatar_resource_path VARCHAR(255),
    is_active VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    license_number VARCHAR(255) NOT NULL,
    vehicle_id UUID UNIQUE,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS rider (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(255),
    dob VARCHAR(255),
    gender gender,
    avatar_resource_path VARCHAR(255),
    is_active VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    preferred_payment_method preferred_payment_method,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW()
    );

CREATE TABLE IF NOT EXISTS ride (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    rider_id UUID NOT NULL,
    driver_id UUID,
    vehicle_id UUID,
    start_location VARCHAR(255) NOT NULL,
    end_location VARCHAR(255) NOT NULL,
    pickup_time TIMESTAMP WITHOUT TIME ZONE,
    dropoff_time TIMESTAMP WITHOUT TIME ZONE,
    fare DOUBLE PRECISION,
    distance DOUBLE PRECISION,
    status ride_status,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    last_modified_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
    FOREIGN KEY (rider_id) REFERENCES rider(id),
    FOREIGN KEY (driver_id) REFERENCES driver(id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
    );
