--CLEAN UP
DROP TABLE IF EXISTS test.escola,test.escola_link ,test.link_provedor,test.equipamento,
 test.equipamento_historico,test.equipamento_modelo,test.equipamento_marca,
 test.equipamento_status,test.funcionario CASCADE;
 
CREATE TABLE test.escola(
	id BIGSERIAL PRIMARY KEY,
	prefixo VARCHAR(10),
	nome VARCHAR(200) NOT NULL,
	inep VARCHAR(8) UNIQUE,
	crede VARCHAR(10),
	rua VARCHAR(255),
	bairro VARCHAR(255),
	telefone VARCHAR(20),
	data_criacao DATE NOT NULL,
	data_atualizacao DATE NOT NULL
);
-- CNPJ pode ser nulo por enquanto
CREATE TABLE test.link_provedor(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	cnpj VARCHAR(30),
	data_criacao Date NOT NULL,
	data_atualizacao Date
);
CREATE TABLE test.escola_link(
	id BIGSERIAL PRIMARY KEY,
	ip VARCHAR(15) NOT NULL,
	circuito VARCHAR(50),
	status VARCHAR(50),
	id_provedor BIGINT NOT NULL REFERENCES link_provedor(id),
	id_escola BIGINT NOT NULL REFERENCES escola(id)
);
CREATE TABLE test.funcionario(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	cpf VARCHAR(11),
	email VARCHAR(255) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	perfil VARCHAR(50) NOT NULL,
	id_escola BIGINT NOT NULL REFERENCES escola(id),
	data_criacao DATE NOT NULL,
	data_atualizacao DATE NOT NULL
);
INSERT INTO test.link_provedor(nome,data_criacao) VALUES('Oi',CURRENT_DATE);
INSERT INTO test.link_provedor(nome,data_criacao) VALUES('ETICE',CURRENT_DATE);
INSERT INTO test.escola(nome,crede,inep,data_criacao,data_atualizacao)
 VALUES('FAKE','SEFOR_1','fakeinep',CURRENT_DATE,CURRENT_DATE);
INSERT INTO test.escola_link(ip,id_provedor,id_escola)
 VALUES('172.24.42.xx','2','1');
INSERT INTO test.funcionario(nome,cpf,email,senha,perfil,id_escola,data_criacao,data_atualizacao) 
 VALUES('FAKE','fakecpf','fake@fake',
 	'fake','ROLE_USUARIO','1',CURRENT_DATE,CURRENT_DATE);
CREATE TABLE test.equipamento_marca(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(30) UNIQUE NOT NULL
);
CREATE TABLE test.equipamento_modelo(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	id_marca BIGINT NOT NULL REFERENCES equipamento_marca(id),
	tipo VARCHAR(30) NOT NULL
);
CREATE TABLE test.equipamento_status(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL
);
INSERT INTO test.equipamento_status(nome) VALUES('FAKE STATUS');

CREATE TABLE test.equipamento(
	id BIGSERIAL PRIMARY KEY,
	id_modelo BIGINT NOT NULL REFERENCES equipamento_modelo(id),
	serial VARCHAR(255),
	tombamento VARCHAR(20),
	descricao VARCHAR(255),
	id_escola BIGINT NOT NULL REFERENCES escola(id),
	id_status BIGINT NOT NULL REFERENCES equipamento_status(id),
	data_criacao DATE NOT NULL,
	data_atualizacao DATE

);
CREATE TABLE test.equipamento_historico(
	id BIGSERIAL PRIMARY KEY,
	id_equipamento BIGINT NOT NULL REFERENCES equipamento(id),
	id_funcionario BIGINT NOT NULL REFERENCES funcionario(id),
	comentario TEXT,
	data DATE NOT NULL
);
INSERT INTO test.equipamento_marca(nome)VALUES('FAKE MARCA');
INSERT INTO test.equipamento_modelo(nome,id_marca,tipo)
	VALUES('FAKE 20','1','COMPUTADOR');
INSERT INTO test.equipamento(id_modelo,id_escola,descricao,data_criacao,id_status)
	VALUES('1','1','FAKE EQUIPAMENTO',CURRENT_DATE,1);
INSERT INTO test.equipamento_historico(id_equipamento,id_funcionario,data)
	VALUES('1','1',CURRENT_DATE);