
CREATE TABLE T_CAMINHOES_COLETA (
    caminhao_id INT PRIMARY KEY,
    placa VARCHAR(20),
    capacidade INT,
    latitude DECIMAL(10,8),
    longitude DECIMAL(11,8),
    data_atualizacao DATE
);

CREATE TABLE T_ROTAS (
    rota_id INT PRIMARY KEY,
    descricao VARCHAR(100),
    caminhao_id INT,
    data DATE,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    capacidade_atual DECIMAL,
    FOREIGN KEY (caminhao_id) REFERENCES T_CAMINHOES_COLETA(caminhao_id)
);

CREATE TABLE T_AGENDAMENTO_COLETA (
    agendamento_id INT PRIMARY KEY,
    rota_id INT,
    data_agendada DATE,
    status VARCHAR(20),
    FOREIGN KEY (rota_id) REFERENCES T_ROTAS(rota_id)
);

CREATE TABLE T_MORADORES (
    morador_id INT PRIMARY KEY,
    nome VARCHAR(50),
    endereco VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE T_NOTIFICACOES (
    notificacao_id INT PRIMARY KEY,
    morador_id INT,
    agendamento_id INT,
    data_envio DATE,
    mensagem VARCHAR(255),
    FOREIGN KEY (morador_id) REFERENCES T_MORADORES(morador_id),
    FOREIGN KEY (agendamento_id) REFERENCES T_AGENDAMENTO_COLETA(agendamento_id)
);

CREATE TABLE T_RESIDUOS (
    residuo_id INT PRIMARY KEY,
    rota_id INT,
    volume_residuo DECIMAL,
    FOREIGN KEY (rota_id) REFERENCES T_ROTAS(rota_id)
);

INSERT INTO T_CAMINHOES_COLETA (caminhao_id, placa, capacidade, latitude, longitude, data_atualizacao)
VALUES (1, 'ABC1234', 10, 40.7128, -74.0060, TO_DATE('2024-04-21', 'YYYY-MM-DD'));

INSERT INTO T_ROTAS (rota_id, descricao, caminhao_id, data, latitude, longitude, capacidade_atual)
VALUES (1, 'Rota 1', 1, TO_DATE('2024-04-21', 'YYYY-MM-DD'), 40.7128, -74.0060, 8);

INSERT INTO T_AGENDAMENTO_COLETA (agendamento_id, rota_id, data_agendada, status)
VALUES (1, 1, TO_DATE('2024-04-21', 'YYYY-MM-DD'), 'Agendado');

INSERT INTO T_RESIDUOS (residuo_id, rota_id, volume_residuo)
VALUES (1, 1, 5);


UPDATE T_AGENDAMENTO_COLETA SET data_agendada = TO_DATE('2024-04-29', 'YYYY-MM-DD') WHERE agendamento_id = 1;



INSERT INTO T_CAMINHOES_COLETA (caminhao_id, placa, capacidade) VALUES (2, 'DEF5678', 8);
INSERT INTO T_CAMINHOES_COLETA (caminhao_id, placa, capacidade) VALUES (3, 'GHI9012', 12);

INSERT INTO T_ROTAS (rota_id, descricao, caminhao_id, data) VALUES (2, 'Rota B', 2, TO_DATE('2024-04-21', 'YYYY-MM-DD'));
INSERT INTO T_ROTAS (rota_id, descricao, caminhao_id, data) VALUES (3, 'Rota C', 3, TO_DATE('2024-04-22', 'YYYY-MM-DD'));
