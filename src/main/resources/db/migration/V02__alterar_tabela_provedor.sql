ALTER TABLE link_provedor ADD COLUMN data_criacao Date;
ALTER TABLE link_provedor ADD COLUMN data_atualizacao Date;
UPDATE link_provedor SET data_criacao = CURRENT_DATE WHERE id > 0;
ALTER TABLE link_provedor ALTER COLUMN data_criacao SET NOT NULL;