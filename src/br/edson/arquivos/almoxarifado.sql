CREATE DATABASE almoxarifado1 set default charset=utf8;

CREATE TABLE produto(
  nome varchar(50) NOT NULL,
  quantidade int(11) NOT NULL,
  codigo SERIAL PRIMARY KEY
 );

CREATE TABLE fornecedor(
	nome varchar(100) NOT NULL,
	fone BIGINT NOT NULL,
	email VARCHAR(100) NOT NULL,
	identificacao_pessoa BIGINT PRIMARY KEY
);

CREATE TABLE responsavel(
	nome VARCHAR(100) NOT NULL,
	codigo SERIAL PRIMARY KEY
);


CREATE TABLE entrada_produto(
	data_entrada DATE NOT NULL,
	numero_nf INT(11) NOT NULL,
	codigo_produto BIGINT UNSIGNED  NOT NULL,
	identificacao_pessoa BIGINT NOT NULL,
	qtd BIGINT NOT NULL,
	CONSTRAINT entrada_PK PRIMARY KEY(numero_nf, codigo_produto, identificacao_pessoa),
	CONSTRAINT codigoFK 
	FOREIGN KEY(codigo_produto) REFERENCES produto(codigo)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
	CONSTRAINT identificacao_pessoa_FK 
	FOREIGN KEY(identificacao_pessoa) REFERENCES fornecedor(identificacao_pessoa)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE retirada_produto(
	data_retirada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	codigo_produto BIGINT UNSIGNED NOT NULL,
	codigo_responsavel BIGINT UNSIGNED NOT NULL,
	quantidade BIGINT NOT NULL,
	CONSTRAINT retirada_PK PRIMARY KEY(data_retirada, codigo_produto, codigo_responsavel),
	CONSTRAINT codigo_produtoFK 
	FOREIGN KEY(codigo_produto) REFERENCES produto(codigo)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	CONSTRAINT codigo_responsavelFK
	FOREIGN KEY(codigo_responsavel) 
	REFERENCES responsavel(codigo)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE devolucao_produto(
	data_devolucao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	codigo_produto BIGINT UNSIGNED NOT NULL,
	codigo_responsavel BIGINT UNSIGNED NOT NULL,
	quantidade BIGINT NOT NULL,
	CONSTRAINT devolucao_PK PRIMARY KEY(data_devolucao, codigo_produto, codigo_responsavel),
	CONSTRAINT codigo_produto_retiradaFK FOREIGN KEY(codigo_produto) REFERENCES produto(codigo)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	CONSTRAINT codigo_responsavel_retiradaFK FOREIGN KEY(codigo_responsavel) REFERENCES responsavel(codigo)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);


/* trigger usado quando registra entrada produto*/
DELIMITER !!
CREATE TRIGGER AFTER_INSERT_ENTRADA_PRODUTO 
AFTER INSERT  ON entrada_produto FOR EACH ROW
BEGIN
	UPDATE produto  SET quantidade= quantidade + new.qtd
	where new.codigo_produto=produto.codigo;
END!!
DELIMITER ;

/* trigger usado quando registra retirada produto*/
DELIMITER !!
CREATE TRIGGER AFTER_INSERT_RETIRADA_PRODUTO
AFTER INSERT ON retirada_produto FOR EACH ROW
BEGIN
	UPDATE produto SET quantidade= quantidade - new.quantidade
	where NEW.codigo_produto=produto.codigo;
END !!
DELIMITER ;

/* trigger usado quando registra devoluçao produto */
DELIMITER !!
CREATE TRIGGER AFTER_INSERT_DEVOLUCAO_PRODUTO 
AFTER INSERT  ON devolucao_produto FOR EACH ROW
BEGIN
	UPDATE produto  SET quantidade= quantidade + new.quantidade
	where new.codigo_produto=produto.codigo;
END!!
DELIMITER ;

/* select para gerar relatorio soma a quantidade dos produtos*/
SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, sum(qtd) quantidade FROM entrada_produto ep 
				INNER JOIN produto p ON p.codigo=ep.codigo_produto
				INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa
				WHERE ep.data_entrada BETWEEN '2018/01/01' AND '2019/01/10'
				GROUP BY p.nome ORDER BY p.nome;
/*selent para gerar relatorio define só data de início e fim*/
SELECT p.nome, data_entrada, numero_nf, f.nome fornecedor, qtd FROM entrada_produto ep 
				INNER JOIN produto p ON p.codigo=ep.codigo_produto
				INNER JOIN fornecedor f ON f.identificacao_pessoa=ep.identificacao_pessoa
				WHERE ep.data_entrada BETWEEN '2018/01/01' AND '2019/01/10'
				AND ep.identificacao_pessoa=52859492000179
				AND p.codigo=35 ORDER BY p.nome;
				

