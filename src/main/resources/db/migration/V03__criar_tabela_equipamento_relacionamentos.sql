CREATE TABLE equipamento_marca(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(30) UNIQUE NOT NULL
);
CREATE TABLE equipamento_modelo(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	id_marca BIGINT NOT NULL REFERENCES equipamento_marca(id),
	tipo VARCHAR(30) NOT NULL
);
CREATE TABLE equipamento(
	id BIGSERIAL PRIMARY KEY,
	id_modelo BIGINT NOT NULL REFERENCES equipamento_modelo(id),
	serial VARCHAR(255),
	tombamento VARCHAR(20),
	descricao VARCHAR(255),
	id_escola BIGINT NOT NULL REFERENCES escola(id),
	data_criacao DATE NOT NULL,
	data_atualizacao DATE

);
CREATE TABLE equipamento_historico(
	id BIGSERIAL PRIMARY KEY,
	id_equipamento BIGINT NOT NULL REFERENCES equipamento(id),
	id_funcionario BIGINT NOT NULL REFERENCES funcionario(id),
	comentario TEXT,
	data DATE NOT NULL
);
INSERT INTO equipamento_marca(nome)VALUES('DELL');
INSERT INTO equipamento_marca(nome)VALUES('LENOVO');
INSERT INTO equipamento_marca(nome)VALUES('HP');
INSERT INTO equipamento_marca(nome)VALUES('POSITIVO');
INSERT INTO equipamento_modelo(nome,id_marca,tipo)
	VALUES('OPTIPLEX 780','1','COMPUTADOR');
INSERT INTO equipamento(id_modelo,id_escola,descricao,data_criacao)
	VALUES('1','1','Equipamento de Testes 1',CURRENT_DATE);
INSERT INTO equipamento_historico(id_equipamento,id_funcionario,data)
	VALUES('1','1',CURRENT_DATE);