--CRIA��O DAS TABELAS
CREATE TABLE C##LIV_LES.VENDAS
(
	"VND_ID" NUMBER(6,0),
	"VND_STATUS" NUMBER(2,0),
	"VND_VALOR_FINAL" NUMBER(8,2),
	"VND_FORMA_PAGAMENTO" NUMBER(2,0),
	"VND_CLI_ID" NUMBER(6,0)
);

CREATE TABLE C##LIV_LES.CUPONS
(
	"CUP_ID" NUMBER(6,0),
	"CUP_CODIGO" VARCHAR2(10),
	"CUP_PORCENTAGEM_DESCONTO" NUMBER(3,2),
	"CUP_STATUS" NUMBER(1,0)
);

CREATE TABLE C##LIV_LES.ITENS_VENDA
(
	"IVD_ID" NUMBER(6,0),
	"IVD_QUANTIDADE" NUMBER(8,0),
	"IVD_VALOR_VENDA" NUMBER(8,2),
	"IVD_STATUS" VARCHAR2(9),
	"IVD_CLI_ID" NUMBER(6,0),
	"IVD_LIV_ID" NUMBER(6,0),
	"IVD_VND_ID" NUMBER(6,0),
	"IVD_CUP_ID" NUMBER(6,0)
);

CREATE TABLE C##LIV_LES.CATEGORIA_STATUS
(
	"CST_ID" NUMBER(6,0),
	"CST_NOME" VARCHAR2(50),
	"CST_DESCRICAO" VARCHAR2(155),
	"CST_TIPO" NUMBER(1,0)
);

CREATE TABLE C##LIV_LES.SITUACOES_CADASTRO
(
	"SCD_ID" NUMBER(6,0),
	"SCD_JUSTIFICATIVA" VARCHAR2(255),
	"SCD_DATA_MUDANCA" DATE,
	"SCD_LIV_ID" NUMBER(6,0),
	"SCD_CST_ID" NUMBER(6,0)
);

--PRIMARY KEY
		
ALTER TABLE C##LIV_LES.VENDAS
	ADD CONSTRAINT PK_VND PRIMARY KEY (VND_ID);
	
ALTER TABLE C##LIV_LES.CUPONS
	ADD CONSTRAINT PK_CUP PRIMARY KEY (CUP_ID);	

ALTER TABLE C##LIV_LES.ITENS_VENDA
	ADD CONSTRAINT PK_IVD PRIMARY KEY (IVD_ID);

ALTER TABLE C##LIV_LES.CATEGORIA_STATUS
	ADD CONSTRAINT PK_CST PRIMARY KEY (CST_ID);
	
ALTER TABLE C##LIV_LES.SITUACOES_CADASTRO
	ADD CONSTRAINT PK_SCD PRIMARY KEY (SCD_ID);
		
--FOREIGN KEY - VENDAS
ALTER TABLE C##LIV_LES.VENDAS -- RELACIONAMENTO N-1 (CLI - VND)
	ADD CONSTRAINT FK_VND_CLI FOREIGN KEY (VND_CLI_ID) REFERENCES C##LIV_LES.CLIENTES(CLI_ID);

--FOREIGN KEY - ITENS_VENDA
ALTER TABLE C##LIV_LES.ITENS_VENDA -- RELACIONAMENTO N-1 (LIV - IVD)
	ADD CONSTRAINT FK_IVD_LIV FOREIGN KEY (IVD_LIV_ID) REFERENCES C##LIV_LES.LIVROS(LIV_ID);
	
ALTER TABLE C##LIV_LES.ITENS_VENDA -- RELACIONAMENTO N-1 (VND - IVD)
	ADD CONSTRAINT FK_IVD_VND FOREIGN KEY (IVD_VND_ID) REFERENCES C##LIV_LES.VENDAS(VND_ID);
	
ALTER TABLE C##LIV_LES.ITENS_VENDA -- RELACIONAMENTO N-1 (CUP - IVD)
	ADD CONSTRAINT FK_IVD_CUP FOREIGN KEY (IVD_CUP_ID) REFERENCES C##LIV_LES.CUPONS(CUP_ID);
	
--FOREIGN KEY - SITUACOES_CADASTRO
ALTER TABLE C##LIV_LES.SITUACOES_CADASTRO -- RELACIONAMENTO N-1 (LIV - SCD)
	ADD CONSTRAINT FK_SCD_LIV FOREIGN KEY (SCD_LIV_ID) REFERENCES C##LIV_LES.LIVROS(LIV_ID);
	
ALTER TABLE C##LIV_LES.SITUACOES_CADASTRO -- RELACIONAMENTO N-1 (CST - SCD)
	ADD CONSTRAINT FK_SCD_CST FOREIGN KEY (SCD_CST_ID) REFERENCES C##LIV_LES.CATEGORIA_STATUS(CST_ID);
	
-- CRIA��O DA SEQUENCE
CREATE SEQUENCE C##LIV_LES.SQ_VND 
INCREMENT BY 1 
START WITH 1 
NOCACHE
;

CREATE SEQUENCE C##LIV_LES.SQ_CUP
INCREMENT BY 1 
START WITH 1 
NOCACHE
;

CREATE SEQUENCE C##LIV_LES.SQ_IVD
INCREMENT BY 1 
START WITH 1 
NOCACHE
;

CREATE SEQUENCE C##LIV_LES.SQ_CST
INCREMENT BY 1 
START WITH 1 
NOCACHE
;

CREATE SEQUENCE C##LIV_LES.SQ_SCD
INCREMENT BY 1 
START WITH 1 
NOCACHE
;

-- TRIGGERS PARA PEGAR VALOR DA SEQUENCE
CREATE OR REPLACE
TRIGGER C##LIV_LES.TG_SQ_VND
BEFORE INSERT ON C##LIV_LES.VENDAS
FOR EACH ROW
WHEN (NEW.VND_ID IS NULL)
BEGIN
  :NEW.VND_ID := C##LIV_LES.SQ_VND.NEXTVAL;
END;
/

CREATE OR REPLACE
TRIGGER C##LIV_LES.TG_SQ_CUP
BEFORE INSERT ON C##LIV_LES.CUPONS
FOR EACH ROW
WHEN (NEW.CUP_ID IS NULL)
BEGIN
  :NEW.CUP_ID := C##LIV_LES.SQ_CUP.NEXTVAL;
END;
/

CREATE OR REPLACE
TRIGGER C##LIV_LES.TG_SQ_IVD
BEFORE INSERT ON C##LIV_LES.ITENS_VENDA
FOR EACH ROW
WHEN (NEW.IVD_ID IS NULL)
BEGIN
  :NEW.IVD_ID := C##LIV_LES.SQ_IVD.NEXTVAL;
END;
/

CREATE OR REPLACE
TRIGGER C##LIV_LES.TG_SQ_CST
BEFORE INSERT ON C##LIV_LES.CATEGORIA_STATUS
FOR EACH ROW
WHEN (NEW.CST_ID IS NULL)
BEGIN
  :NEW.CST_ID := C##LIV_LES.SQ_CST.NEXTVAL;
END;
/

CREATE OR REPLACE
TRIGGER C##LIV_LES.TG_SQ_SCD
BEFORE INSERT ON C##LIV_LES.SITUACOES_CADASTRO
FOR EACH ROW
WHEN (NEW.SCD_ID IS NULL)
BEGIN
  :NEW.SCD_ID := C##LIV_LES.SQ_SCD.NEXTVAL;
END;
/