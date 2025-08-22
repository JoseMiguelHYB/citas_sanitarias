DROP TABLE IF EXISTS pacientes;

CREATE TABLE pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    fecha_nacimiento DATE,
    email VARCHAR(100),
    telefono VARCHAR(20),
    genero VARCHAR(10),
    direccion VARCHAR(255),
    localidad VARCHAR(100),
    codigo_postal VARCHAR(10),
    numero_seguridad_social VARCHAR(50),
    grupo_sanguineo VARCHAR(5),
    alergias VARCHAR(255),
    contacto_emergencia VARCHAR(255),
    seguro_medico_mutua VARCHAR(255)
);

DROP TABLE IF EXISTS medicos;

CREATE TABLE medicos (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    fecha_nacimiento DATE,
    email VARCHAR(100),
    telefono VARCHAR(20),
    genero VARCHAR(10),    especialidad VARCHAR(100) NOT NULL,
    numero_colegiado VARCHAR(50) NOT NULL,
    hora_inicio TIME,
    hora_fin TIME,
    consulta VARCHAR(50),
    centro_salud VARCHAR(150),
    direccion_centro VARCHAR(255),
    codigo_postal_centro VARCHAR(10)
);