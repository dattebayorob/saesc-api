CREATE TABLE equipamento_status(
	id BIGSERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL
);
INSERT INTO equipamento_status(nome) VALUES('RECEBIDO'),('EM MANUTENÇÃO'),('EM HOMOLOGAÇÃO'),('AGUARDANDO ENTREGA'),('ENTREGUE');
ALTER TABLE equipamento ADD COLUMN id_status BIGINT;
pdate equipamento SET  id_status = '1' WHERE equipamento.id > 0;
ALTER TABLE equipamento ADD CONSTRAINT equipamento_id_status_fkey FOREIGN KEY (id_status)
	REFERENCES equipamento_status(id), ALTER COLUMN id_status SET NOT NULL;
