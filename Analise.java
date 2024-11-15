/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: Analise.java
Data: 22.05.2024
--------------------------------------*

/*
1. Objectivo
Este projeto tem o objetivo gerenciar as entradas, saidas, processo de
producao e estoque de uma padaria;

2. Visao [Interfaces Graficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipal

- EntradaVisao
- EstoqueVisao
- ProducaoVisao
- VendaVisao
- EditarMateriaPEstoque
- EditarProdutoEstoque
- EstoqueProdutoVisao
- VendaRegistroVisao

3. Entidades Fortes e Seus Atributos (Modelo)

- EntradaModelo
	int id
	String ingrediente
    String unidadeMedida
    int qtdEntrada
    String dataEntrada
    String dataValidade
    double custoUnit
    double custoTotal

- EstoqueModelo
    int id
    int nivelMinimo
    int nivelAtual
    double precoTotal
    EntradaModelo ingrediente
    ProducaoModelo produtoAcabado

- ProducaoModelo
	int id
    String produto
	int qtdProd
    String dataProd
    double precoUnit
    double custoProd

- VendaModelo
	int id
	ProducaoModelo produto
    int qtd
    double precoTotal
    String cliente
    String dataVenda
    double valorPago
    double troco
    String tipoPagamento
	
4. Ficheiro
- EntradaFile.dat
- EstoqueFile.dat
- ProducaoFile.dat
- VendaFile.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
- Produto.tab
- MateriaPrima.tab
- UnidadeMedida.tab
- NivelMinimo.tab

6. Diversos
6.1 - Implementação: Java Swing
6.2 - IDE: VScode
*/


















