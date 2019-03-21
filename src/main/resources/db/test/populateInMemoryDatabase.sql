INSERT INTO provedor(nome, cnpj, data_criacao, data_atualizacao) VALUES('Provedor Fake','00000000000000',CURRENT_DATE,CURRENT_DATE);
INSERT INTO instituicao(nome,crede,inep,data_criacao,data_atualizacao)
 VALUES('FAKE','SEDUC','00000000',CURRENT_DATE,CURRENT_DATE);
INSERT INTO instituicao_link(ip,id_provedor,id_instituicao)
 VALUES('200.217.200.xxx','1','1');
INSERT INTO funcionario(nome,cpf,email,senha,perfil,id_instituicao,data_criacao,data_atualizacao) 
 VALUES('Fake','00000000000','fake@fake',
 	'$2a$10$iqpnKvFQvKlBWghd5iw6X.v50j9QY7AgCceSEKB0hbvmA83IAofCS','ROLE_TECNICO','1',CURRENT_DATE,CURRENT_DATE);
 INSERT INTO equipamento_marca(nome)VALUES('DELL');
 INSERT INTO equipamento_modelo(nome,id_marca,tipo)
	VALUES('OPTIPLEX 780','1','COMPUTADOR'),('OPTIPLEX 7010','1','COMPUTADOR');
 INSERT INTO equipamento_status(nome) VALUES('RECEBIDO'),('EM MANUTENÇÃO'),('EM HOMOLOGAÇÃO'),('AGUARDANDO ENTREGA'),('ENTREGUE');
	VALUES('OPTIPLEX 780','1','COMPUTADOR');
INSERT INTO equipamento(id_modelo,id_instituicao,id_status,descricao,data_criacao)
	VALUES('1','1','1','Equipamento de Testes 1',CURRENT_DATE);
INSERT INTO equipamento_historico(id_equipamento,id_funcionario,data)
	VALUES('1','1',CURRENT_DATE);
