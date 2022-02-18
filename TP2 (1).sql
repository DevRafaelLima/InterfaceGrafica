-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Tempo de geração: 18-Fev-2022 às 13:07
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `TP2`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `cod` int(11) NOT NULL,
  `rg` int(11) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `telefone` varchar(30) DEFAULT NULL,
  `renda` double DEFAULT NULL,
  `situacao` tinyint(1) DEFAULT NULL,
  `codTipoCliente` int(11) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `rua` varchar(100) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `cep` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cod`, `rg`, `nome`, `telefone`, `renda`, `situacao`, `codTipoCliente`, `cidade`, `rua`, `num`, `cep`) VALUES
(1, 1, 'Rafael lima', '55991090905', 1, 0, 1, 'Cidade 1', 'Rua 1', 1, 665588),
(2, 1254, 'Novo Cliente', '0800-000', 55991090905, 0, 1, 'Maranhão', 'Rua qualquer', 9977, 5896748),
(3, NULL, 'Empresa 1', '967000', 98654.35, 0, 2, '', 'Rua e', 77, 78955);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contas`
--

CREATE TABLE `contas` (
  `cod` int(11) NOT NULL,
  `dataAbertura` date DEFAULT NULL,
  `dataFechamento` date DEFAULT NULL,
  `situacao` tinyint(1) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `codCliente` int(11) DEFAULT NULL,
  `codTipoConta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `contas`
--

INSERT INTO `contas` (`cod`, `dataAbertura`, `dataFechamento`, `situacao`, `saldo`, `senha`, `codCliente`, `codTipoConta`) VALUES
(18, '2022-02-10', '2022-02-26', 0, 511, '123456', 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoCliente`
--

CREATE TABLE `tipoCliente` (
  `cod` int(11) NOT NULL,
  `nome` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tipoCliente`
--

INSERT INTO `tipoCliente` (`cod`, `nome`) VALUES
(1, 'FISICA'),
(2, 'JURIDICA'),
(4, 'novos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoConta`
--

CREATE TABLE `tipoConta` (
  `cod` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `tipoConta`
--

INSERT INTO `tipoConta` (`cod`, `nome`) VALUES
(1, 'CORRENTE'),
(2, 'ESPECIAL'),
(3, 'POUPANÇA');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `codTipoCliente` (`codTipoCliente`);

--
-- Índices para tabela `contas`
--
ALTER TABLE `contas`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `codCliente` (`codCliente`),
  ADD KEY `codTipoConta` (`codTipoConta`);

--
-- Índices para tabela `tipoCliente`
--
ALTER TABLE `tipoCliente`
  ADD PRIMARY KEY (`cod`);

--
-- Índices para tabela `tipoConta`
--
ALTER TABLE `tipoConta`
  ADD PRIMARY KEY (`cod`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=984382209;

--
-- AUTO_INCREMENT de tabela `contas`
--
ALTER TABLE `contas`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de tabela `tipoCliente`
--
ALTER TABLE `tipoCliente`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `tipoConta`
--
ALTER TABLE `tipoConta`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`codTipoCliente`) REFERENCES `tipoCliente` (`cod`);

--
-- Limitadores para a tabela `contas`
--
ALTER TABLE `contas`
  ADD CONSTRAINT `contas_ibfk_1` FOREIGN KEY (`codCliente`) REFERENCES `clientes` (`cod`),
  ADD CONSTRAINT `contas_ibfk_2` FOREIGN KEY (`codTipoConta`) REFERENCES `tipoConta` (`cod`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
