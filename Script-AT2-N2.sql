	CREATE database gerenciadorDeEstoque;

	CREATE TABLE Categoria (
		IDcategoria INT AUTO_INCREMENT PRIMARY KEY,
		nomeCategoria VARCHAR(50) NOT NULL,
		descricaoCategoria VARCHAR(150)
	);

	CREATE TABLE Produto (
		IDproduto INT AUTO_INCREMENT PRIMARY KEY,
		nomeProduto VARCHAR(50) NOT NULL,
		descricao VARCHAR(150),
		qttdEstoque INT UNSIGNED DEFAULT 0,
		precoDeCompra DOUBLE NOT NULL,
		precoDeVenda DOUBLE NOT NULL,
		IDcategoria INT NOT NULL,
		FOREIGN KEY (IDcategoria) REFERENCES Categoria(IDcategoria)
	);

	CREATE TABLE MovimentacaoEstoque (
		IDmovimentacao INT AUTO_INCREMENT PRIMARY KEY,
		IDproduto INT NOT NULL,
		tipoMovimentacao ENUM('entrada', 'saida') NOT NULL,
		quantidade INT UNSIGNED NOT NULL,
		dataMovimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		FOREIGN KEY (IDproduto) REFERENCES Produto(IDproduto)
	);

	DELIMITER //
	CREATE PROCEDURE inserirProduto(
		IN P_nomeProduto VARCHAR(50),
		IN P_descricao VARCHAR(150),
		IN P_qttdEstoque INT,
		IN P_precoDeCompra DOUBLE,
		IN P_precoDeVenda DOUBLE,
		IN P_IDcategoria INT
	)
	BEGIN
		INSERT INTO Produto (nomeProduto, descricao, qttdEstoque, precoDeCompra, precoDeVenda, IDcategoria)
		VALUES (P_nomeProduto, P_descricao, P_qttdEstoque, P_precoDeCompra, P_precoDeVenda, P_IDcategoria);
	END //
	DELIMITER ;

	DELIMITER //
	CREATE PROCEDURE atualizarProduto(
		IN P_IDproduto INT,
		IN P_nomeProduto VARCHAR(50),
		IN P_descricao VARCHAR(150),
		IN P_qttdEstoque INT,
		IN P_precoDeCompra DOUBLE,
		IN P_precoDeVenda DOUBLE,
		IN P_IDcategoria INT
	)
	BEGIN
		UPDATE Produto
		SET nomeProduto = P_nomeProduto,
			descricao = P_descricao,
			qttdEstoque = P_qttdEstoque,
			precoDeCompra = P_precoDeCompra,
			precoDeVenda = P_precoDeVenda,
			IDcategoria = P_IDcategoria
		WHERE IDproduto = P_IDproduto;
	END //
	DELIMITER ;

	DELIMITER //
	CREATE PROCEDURE excluirProduto(
		IN P_IDproduto INT
	)
	BEGIN
		DELETE FROM Produto
		WHERE IDproduto = P_IDproduto;
	END //
	DELIMITER ;


	DELIMITER //
	CREATE PROCEDURE inserirCategoria(
		IN P_nomeCategoria VARCHAR(50),
		IN P_descricaoCategoria VARCHAR(150)
	)
	BEGIN
		INSERT INTO Categoria (nomeCategoria, descricaoCategoria)
		VALUES (P_nomeCategoria, P_descricaoCategoria);
	END //
	DELIMITER ;

	DELIMITER //
	CREATE PROCEDURE atualizarCategoria(
		IN P_IDcategoria INT,
		IN P_nomeCategoria VARCHAR(50),
		IN P_descricaoCategoria VARCHAR(150)
	)
	BEGIN
		UPDATE Categoria
		SET nomeCategoria = P_nomeCategoria,
			descricaoCategoria = P_descricaoCategoria
		WHERE IDcategoria = P_IDcategoria;
	END //
	DELIMITER ;

	DELIMITER //
	CREATE PROCEDURE excluirCategoria(
		IN P_IDcategoria INT
	)
	BEGIN

		DELETE FROM Produto WHERE IDcategoria = P_IDcategoria;

	  
		DELETE FROM Categoria
		WHERE IDcategoria = P_IDcategoria;
	END //
	DELIMITER ;



	DELIMITER //
	CREATE TRIGGER verificarEstoqueBaixo
	AFTER UPDATE ON Produto
	FOR EACH ROW
	BEGIN
		IF NEW.qttdEstoque < 10 THEN
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Alerta: Estoque baixo para o produto.';
		END IF;
	END //
	DELIMITER ;

	DELIMITER //

	CREATE TRIGGER atualizarMovimentacaoEstoque
	AFTER UPDATE ON Produto
	FOR EACH ROW
	BEGIN
		DECLARE tipoMovimentacao ENUM('entrada', 'saida');
		DECLARE quantidadeMovimentada INT;

	   
		IF NEW.qttdEstoque > OLD.qttdEstoque THEN
			SET tipoMovimentacao = 'entrada';
			SET quantidadeMovimentada = NEW.qttdEstoque - OLD.qttdEstoque;
		ELSEIF NEW.qttdEstoque < OLD.qttdEstoque THEN
			SET tipoMovimentacao = 'saida';
			SET quantidadeMovimentada = OLD.qttdEstoque - NEW.qttdEstoque;
		END IF;

	   
		IF quantidadeMovimentada > 0 THEN
			INSERT INTO MovimentacaoEstoque (IDproduto, tipoMovimentacao, quantidade)
			VALUES (NEW.IDproduto, tipoMovimentacao, quantidadeMovimentada);
		END IF;
	END //

	DELIMITER ;

CALL inserirCategoria('Eletrônicos', 'Produtos eletrônicos, como celulares e computadores');
CALL inserirCategoria('Calçados', 'Sapatos, tênis e chinelos');
CALL inserirCategoria('Alimentos', 'Produtos perecíveis e não perecíveis');
CALL inserirCategoria('Móveis', 'Cadeiras, mesas e outros móveis');

CALL inserirProduto('Smartphone', 'Smartphone de última geração', 50, 1500.00, 2000.00, 1);
CALL inserirProduto('Notebook', 'Notebook com alta performance', 30, 3000.00, 4000.00, 1);
CALL inserirProduto('Tênis Esportivo', 'Tênis confortável para corridas', 100, 150.00, 250.00, 2);
CALL inserirProduto('Mesa de Escritório', 'Mesa ergonômica de escritório', 20, 500.00, 700.00, 4);
CALL inserirProduto('Cadeira Gamer', 'Cadeira ajustável e confortável', 15, 800.00, 1200.00, 4);
CALL inserirProduto('Arroz 5kg', 'Pacote de arroz de 5kg', 200, 10.00, 15.00, 3);
CALL inserirProduto('Chinelo', 'Chinelo de borracha', 150, 20.00, 40.00, 2);

select * from produto;
select * from categoria;








