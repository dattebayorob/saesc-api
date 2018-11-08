CREATE TABLE escola(
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
CREATE TABLE link_provedor(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	cnpj VARCHAR(30)
);
CREATE TABLE escola_link(
	id BIGSERIAL PRIMARY KEY,
	ip VARCHAR(15) NOT NULL,
	circuito VARCHAR(50),
	status VARCHAR(50),
	id_provedor BIGINT NOT NULL REFERENCES link_provedor(id),
	id_escola BIGINT NOT NULL REFERENCES escola(id)
);
CREATE TABLE funcionario(
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
INSERT INTO link_provedor(nome) VALUES('Oi');
INSERT INTO link_provedor(nome) VALUES('ETICE');
INSERT INTO escola(nome,crede,data_criacao,data_atualizacao)
 VALUES('SEDUC','SEDUC',CURRENT_DATE,CURRENT_DATE);
INSERT INTO escola_link(ip,id_provedor,id_escola)
 VALUES('200.217.200.xxx','1','1');
INSERT INTO escola_link(ip,id_provedor,id_escola)
 VALUES('172.24.42.xx','2','1');
INSERT INTO funcionario(nome,cpf,email,senha,perfil,id_escola,data_criacao,data_atualizacao) 
 VALUES('Robson William','00000000000','robson.william65@gmail.com',
 	'$2a$10$iqpnKvFQvKlBWghd5iw6X.v50j9QY7AgCceSEKB0hbvmA83IAofCS','ROLE_TECNICO','1',CURRENT_DATE,CURRENT_DATE);
