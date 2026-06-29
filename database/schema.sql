CREATE DATABASE IF NOT EXISTS hospital
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE hospital;

CREATE TABLE IF NOT EXISTS funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    telefone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS medicos (
    funcionario_id INT PRIMARY KEY,
    crm VARCHAR(30) NOT NULL UNIQUE,
    especialidade VARCHAR(80) NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);

CREATE TABLE IF NOT EXISTS enfermeiros (
    funcionario_id INT PRIMARY KEY,
    coren VARCHAR(30) NOT NULL UNIQUE,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    funcionario_id INT UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha_hash VARCHAR(128) NOT NULL,
    perfil VARCHAR(20) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    deve_trocar_senha BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS pacientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(200),
    email VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS diagnosticos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT,
    descricao TEXT NOT NULL,
    observacoes TEXT,
    data_diagnostico DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (medico_id) REFERENCES medicos(funcionario_id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS receitas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    medico_id INT,
    prescricao TEXT NOT NULL,
    aplicar_no_hospital BOOLEAN NOT NULL DEFAULT FALSE,
    data_emissao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (medico_id) REFERENCES medicos(funcionario_id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS aplicacoes_medicamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    receita_id INT NOT NULL,
    paciente_id INT NOT NULL,
    enfermeiro_id INT,
    dosagem_aplicada VARCHAR(100) NOT NULL,
    via_aplicacao VARCHAR(100) NOT NULL,
    observacoes TEXT,
    data_aplicacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (receita_id) REFERENCES receitas(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id),
    FOREIGN KEY (enfermeiro_id) REFERENCES enfermeiros(funcionario_id)
        ON DELETE SET NULL
);
