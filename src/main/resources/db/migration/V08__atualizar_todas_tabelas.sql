-- Escola x Instituição
ALTER TABLE escola RENAME TO instituicao;
ALTER TABLE instituicao RENAME CONSTRAINT escola_pkey TO instituicao_pkey;
ALTER TABLE instituicao RENAME CONSTRAINT escola_inep_key TO instituicao_inep_key;
UPDATE instituicao SET nome = CONCAT(prefixo,' ',nome);
ALTER TABLE instituicao DROP COLUMN prefixo;
-- link_provedor x provedor
ALTER TABLE link_provedor RENAME TO provedor;
ALTER TABLE provedor RENAME CONSTRAINT link_provedor_pkey TO provedor_pkey;
-- escola_link x instituicao_link
ALTER TABLE escola_link RENAME TO instituicao_link;
ALTER TABLE instituicao_link RENAME CONSTRAINT escola_link_pkey TO link_pkey;
ALTER TABLE instituicao_link RENAME COLUMN id_escola TO id_instituicao;
ALTER TABLE instituicao_link DROP CONSTRAINT escola_link_id_escola_fkey;
ALTER TABLE instituicao_link ADD CONSTRAINT link_id_instituicao_fkey FOREIGN KEY (id_instituicao) REFERENCES instituicao(id);
ALTER TABLE instituicao_link RENAME CONSTRAINT escola_link_id_provedor_fkey TO link_id_provedor_fkey;
-- funcionario com novo fk referenciando instituicao
ALTER TABLE funcionario RENAME COLUMN id_escola TO id_instituicao;
ALTER TABLE funcionario DROP CONSTRAINT funcionario_id_escola_fkey;
ALTER TABLE funcionario ADD CONSTRAINT funcionario_id_instituicao_fkey FOREIGN KEY (id_instituicao) REFERENCES instituicao(id);

-- ambiente/local
CREATE TABLE instituicao_ambiente(
	id BIGINT PRIMARY KEY,
	nome VARCHAR(250) NOT NULL,
	id_instituicao BIGINT REFERENCES instituicao(id)
);

-- equipamento com novo fk pro setor da instituicao
ALTER TABLE equipamento DROP CONSTRAINT  equipamento_id_escola_fkey;
ALTER TABLE equipamento RENAME COLUMN id_escola TO id_instituicao;
ALTER TABLE equipamento ADD CONSTRAINT equipamento_id_instituicao_fkey FOREIGN KEY (id_instituicao) REFERENCES instituicao(id);