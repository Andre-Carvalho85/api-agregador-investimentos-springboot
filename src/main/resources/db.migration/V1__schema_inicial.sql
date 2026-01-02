-- Schema inicial gerado a partir do Hibernate

CREATE TABLE tb_acao_investimento (
    cd_ticker VARCHAR(255) NOT NULL,
    ds_acao VARCHAR(255),
    PRIMARY KEY (cd_ticker)
) ENGINE=InnoDB;

CREATE TABLE tb_usuario (
    cd_usuario BINARY(16) NOT NULL,
    nm_usuario VARCHAR(255),
    ds_email_usuario VARCHAR(255),
    ds_senha VARCHAR(255),
    fl_status BIT,
    dt_criacao_usuario DATETIME(6),
    dt_atualizacao_usuario DATETIME(6),
    PRIMARY KEY (cd_usuario)
) ENGINE=InnoDB;

CREATE TABLE tb_conta (
    cd_tabela BINARY(16) NOT NULL,
    usuario_id BINARY(16),
    ds_descricao_conta VARCHAR(255),
    PRIMARY KEY (cd_tabela)
) ENGINE=InnoDB;

CREATE TABLE tb_conta_acao_investimento (
    cd_conta BINARY(16) NOT NULL,
    cd_acao_investimento VARCHAR(255) NOT NULL,
    qtd_acoes INT,
    qtde_total DECIMAL(15,2),
    PRIMARY KEY (cd_conta, cd_acao_investimento)
) ENGINE=InnoDB;

CREATE TABLE tb_endereco_cobranca (
    cd_tabela BINARY(16) NOT NULL,
    ds_rua VARCHAR(255),
    cd_numero_casa INT,
    PRIMARY KEY (cd_tabela)
) ENGINE=InnoDB;

-- Foreign Keys

ALTER TABLE tb_conta
ADD CONSTRAINT fk_conta_usuario
FOREIGN KEY (usuario_id)
REFERENCES tb_usuario (cd_usuario);

ALTER TABLE tb_conta_acao_investimento
ADD CONSTRAINT fk_conta_acao_acao
FOREIGN KEY (cd_acao_investimento)
REFERENCES tb_acao_investimento (cd_ticker);

ALTER TABLE tb_conta_acao_investimento
ADD CONSTRAINT fk_conta_acao_conta
FOREIGN KEY (cd_conta)
REFERENCES tb_conta (cd_tabela);

ALTER TABLE tb_endereco_cobranca
ADD CONSTRAINT fk_endereco_conta
FOREIGN KEY (cd_tabela)
REFERENCES tb_conta (cd_tabela);
