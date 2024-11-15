/*------------------------------------
Tema: Gestão de uma Padaria
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: ProducaoVisao.java
Data: 13.06.2024
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.util.*;

public class ProducaoVisao extends JFrame
{
    private PainelNorte painelNorte;
    private PainelSul painelSul;
    private PainelCentro painelCentro;
    private PainelCentro painelCentro2;
    private PainelCentro painelCentro3;

    boolean editar;
    
    public ProducaoVisao(boolean alterar, ProducaoModelo modelo)
    {
        super("Producao");
        definirTema();
        setLayout(new BorderLayout());

        editar = alterar;

        getContentPane().add(painelNorte = new PainelNorte(), BorderLayout.NORTH);

        if (!alterar)
            getContentPane().add(painelCentro = new PainelCentro(), BorderLayout.CENTER);
        else
            getContentPane().add(painelCentro = new PainelCentro(modelo), BorderLayout.CENTER);


        getContentPane().add(painelSul = new PainelSul(), BorderLayout.SOUTH);


        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public class PainelNorte extends JPanel
    {
        private JLabel titulo;
        private JSeparator separator;


        public PainelNorte()
        {     
            separator = new JSeparator(SwingConstants.HORIZONTAL);
            titulo = new JLabel("Tela de Produção");
            titulo.setFont(new Font("Monospaced", Font.BOLD,40));
            setBackground(Color.decode("#CBCBCB"));
            add(titulo);   
        }
    }

    public class PainelCentro extends JPanel implements KeyListener
    {
        private JComboBox produtoJCB;

        private JTextField idEstoque,qtdProducao,idJTF, precoUni,custoTotal;

        private JLabel lblIngredientes,lblProduto,lblPreco,lblIng,lblCustoTot, lblQtdEn, lblDataEnt, lblCustou;
        private JPanel painelData, painelData2;
        private JTextFieldData dataProdu;

        private StringVector vectorIngredientes;
        private JCheckBox checkboxIng;
        private JPanel painelJChb;
        private String [][] ingValores;
        private JTextField [] inputs;

        public PainelCentro()
        {   
            setLayout(new GridLayout(7,2));  
			idJTF = new JTextField();

            ProducaoFile producaofile = new ProducaoFile();
			idJTF.setText( "" + producaofile.getProximoCodigo() );

            idEstoque = new JTextField();
            EstoqueFile entradafile2 = new EstoqueFile();
			idEstoque.setText( "" + entradafile2.getProximoCodigo() );

            lblProduto = new JLabel("Produto");
            produtoJCB = UInterfaceBox.createJComboBoxsTabela2("Produto.tab");
            
            lblQtdEn = new JLabel("Quantidade");
            qtdProducao = new JTextField();

            lblDataEnt = new JLabel("Data de Producao");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataProdu = new JTextFieldData("Data?");
            painelData.add( dataProdu.getDTestField() );
			painelData.add( dataProdu.getDButton() );

            lblPreco = new JLabel("Preco/unidade");
            precoUni = new JTextField("0");

            lblCustoTot = new JLabel("Custo Total");
            custoTotal = new JTextField("0");   

            lblIngredientes = new JLabel("Ingredientes usados");     


            add(lblProduto);
            add(produtoJCB);

            add(lblQtdEn);
            add(qtdProducao);

            add(lblDataEnt);
            add(painelData);

            add(lblPreco);
            add(precoUni);

             add(lblIngredientes);

            painelJChb = new JPanel();

            EstoqueFile estoque = new EstoqueFile();
            vectorIngredientes = estoque.getAllIngredientes();

            inputs = new JTextField[vectorIngredientes.size()];

            ingValores = new String[vectorIngredientes.size()][2];
            for(int i = 0; i<vectorIngredientes.size(); i++)
            {
               inputs[i] = new JTextField(5);
                inputs[i].addKeyListener(this);
               inputs[i].setText(""+0);
               EstoqueModelo unidadeMed = estoque.pesquisarIngredienteEstoque(""+vectorIngredientes.get(i));
               if(unidadeMed.getNivelAtual() > 0)
               {
                    painelJChb.add(new JLabel(""+vectorIngredientes.get(i) + " (" + unidadeMed.getUnidadeMedida() + ") "));
                    painelJChb.add(inputs[i]);    
               }          
            }
            add(painelJChb);

            add(lblCustoTot);
            add(custoTotal);
            custoTotal.setFocusable(false);


            qtdProducao.addKeyListener(this);
            custoTotal.addKeyListener(this);
           
        }

        public PainelCentro(ProducaoModelo modelo)
        {
            setLayout(new GridLayout(7,2));  
            

			idJTF = new JTextField();
            ProducaoFile producaofile = new ProducaoFile();
			idJTF.setText( "" + modelo.getId() );

            lblProduto = new JLabel("Produto");
            produtoJCB = UInterfaceBox.createJComboBoxsTabela2("Produto.tab");
            produtoJCB.setSelectedItem(modelo.getProduto());


            lblQtdEn = new JLabel("Quantidade");
            qtdProducao = new JTextField();
            qtdProducao.setText(""+modelo.getQtdProducao());

            lblDataEnt = new JLabel("Data de Producao");

            painelData = new JPanel( new GridLayout(1, 1) );
            dataProdu = new JTextFieldData("Data?");
            dataProdu.getDTestField().setText(modelo.getDataProd());

            painelData.add( dataProdu.getDTestField() );
			painelData.add( dataProdu.getDButton() );

            lblPreco = new JLabel("Preco/unidade");
            precoUni = new JTextField("0");
            precoUni.setText(""+modelo.getPrecoUni());

            lblCustoTot = new JLabel("Custo Total");
            custoTotal = new JTextField("0");   
            custoTotal.setText(""+modelo.getCustoTotal());

            lblIngredientes = new JLabel("Ingredientes usados");     

            add(lblProduto);
            add(produtoJCB);

            add(lblQtdEn);
            add(qtdProducao);

            add(lblDataEnt);
            add(painelData);

            add(lblPreco);
            add(precoUni);

            add(lblCustoTot);
            add(custoTotal);

        }

        public void keyTyped(KeyEvent evt)
        {
           
        }

        public void keyPressed(KeyEvent evt)
        {
           
        }
        public void keyReleased(KeyEvent evt)
        {
            if(! editar)
            {
                EstoqueFile estoque = new EstoqueFile();
                vectorIngredientes = estoque.getAllIngredientes();

                for(int k = 0; k<vectorIngredientes.size(); k++)
                {
                    if((evt.getSource() == inputs[k] || 
                evt.getSource() == qtdProducao) && (!inputs[k].getText().isEmpty() && !qtdProducao.getText().isEmpty()))
                {
                    double soma=0.0;
                    double custoTot=0.0;
                    EntradaFile entradafile = new EntradaFile();

                    String [][] ingredientes = getValoresIng();
                    for(int i =0; i<ingredientes.length; i++)
                    {
                        soma+=(Double.parseDouble(ingredientes[i][1])*entradafile.getCustoUnidade(""+ingredientes[i][0]));
                    }

                    custoTot = soma * Double.parseDouble(""+getQtdProducao());
                    custoTotal.setText(""+custoTot);
                }
                }
            }
            
             
        }

         public int getId2()
        {
            return Integer.parseInt( idEstoque.getText().trim());
        }
        
        public void setEstoqueId(int id)
        {
            idEstoque.setText(""+id);
        }
        
        public int getId()
        {
            return Integer.parseInt( idJTF.getText().trim());
        }

        public int getQtdProducao()
        {
            if(! String.valueOf(qtdProducao.getText()).isEmpty())
                return Integer.parseInt( qtdProducao.getText().trim() );
            else
                return 0;
        }

        public String [][] getValoresIng()
        {
            String [][] valores = new String[vectorIngredientes.size()][2];

            for(int i = 0; i<vectorIngredientes.size(); i++)
            {
                valores[i][0] = ""+vectorIngredientes.get(i);

                if(inputs[i].getText().trim().equals(""))
                    valores[i][1] = "0";
                else
                    valores[i][1] = ""+inputs[i].getText().trim();
            }

            return valores;
        }

        public String getProduto()
        {
            return String.valueOf(produtoJCB.getSelectedItem());
        }

        public String getDataProd()
        {
            return dataProdu.getDTestField().getText();
        }

        public Double getPrecoUni()
        {
            return Double.parseDouble(precoUni.getText().trim());
        }

        public Double getCustoTotal()
        {
            return Double.parseDouble(custoTotal.getText().trim());
        }

        public void setId(int id)
        {
            idJTF.setText(""+id);
        }

        public void setQtdProducao(int qtdPro)
        {
            qtdProducao.setText("" + qtdPro);
        }

        public void setValoresIng(String [][] valores)
        {
            for (int i = 0; i < vectorIngredientes.size(); i++)
            {
                String ingrediente = valores[i][0];
                String valor = valores[i][1];
                // Procura o índice do ingrediente no vectorIngredientes
                int index = vectorIngredientes.indexOf(ingrediente);
                inputs[index].setText(valor);
            }
        }

     
        public void setProd(String prod)
        {
            produtoJCB.setSelectedItem(prod);
        }

        public void setDataProd(String dataPro)
        {
            dataProdu.getDTestField().setText(dataPro);
        }

        public void setPrecoUni(double precoUn)
        {
            precoUni.setText("" + precoUn);
        }

        public void setCustoTotal(double custoTot)
        {
            custoTotal.setText("" + custoTot);
        }

         public boolean verificarCamposEditar()
       {
            int contador = 0;
            if((String.valueOf(getQtdProducao())).equals("")  || (String.valueOf(getQtdProducao())).equals("0")
            || getDataProd().isEmpty() || getDataProd().isEmpty() ||  (String.valueOf(getPrecoUni())).isEmpty() || (getPrecoUni()+"").equals("0.0")
            || (String.valueOf(getCustoTotal())).isEmpty() || (getCustoTotal()+"").equals("0.0"))
                return false;

            return true;
       }

        public boolean verificarCampos()
       {
            int contador = 0;
            if((String.valueOf(getQtdProducao())).equals("")  || (String.valueOf(getQtdProducao())).equals("0")
            || getDataProd().isEmpty() || getDataProd().isEmpty() ||  (String.valueOf(getPrecoUni())).isEmpty() || (getPrecoUni()+"").equals("0.0")
            || (String.valueOf(getCustoTotal())).isEmpty() || (getCustoTotal()+"").equals("0.0"))
                return false;

            String [][] valoresIng = getValoresIng();

            for(int i =0; i<valoresIng.length; i++)
            {
                if(! (valoresIng[i][1]).isEmpty() && ! (valoresIng[i][1]).equals("0"))
                    contador++;
                
                if(contador == 0 && i == (valoresIng.length - 1))
                {   
                    return false;
                }
            }

            return true;
       }

        public void alterar()
        {
            ProducaoModelo modelo = new ProducaoModelo(
                            getId(), getQtdProducao(), 
                            getPrecoUni(), getCustoTotal(),
                            getProduto(), getDataProd(), true
                        );
                        modelo.editarDados();
                        dispose();
        }
        

        public void salvar() 
        { 
            EstoqueFile fileEstoque = new EstoqueFile();
            int count = 0; // Contador para verificar quantos ingredientes têm níveis adequados
            String[][] ingredientesUsados = getValoresIng();

            // Primeiro, verifica se todos os ingredientes têm quantidade suficiente
            for (int g = 0; g < ingredientesUsados.length; g++) {
                EstoqueModelo modeloEstoque = fileEstoque.pesquisarIngredienteEstoque(ingredientesUsados[g][0]);
                int novoNivel = modeloEstoque.getNivelAtual() - Integer.parseInt(ingredientesUsados[g][1]);
                
                if (novoNivel < 0) {
                    JOptionPane.showMessageDialog(null, "O Ingrediente: " + ingredientesUsados[g][0] + " não possui essa quantidade em estoque.", "Quantidade de material inválida", JOptionPane.ERROR_MESSAGE);
                    return; // Interrompe a execução se encontrar um ingrediente com quantidade insuficiente
                } else {
                    count++; // Incrementa o contador de ingredientes com quantidade suficiente
                }
            }

            // Se todos os ingredientes têm quantidade suficiente, podemos salvar os dados
            if (count == ingredientesUsados.length) 
            {
                // Atualiza o estoque do produto final
                EstoqueModelo modeloEstoque2 = fileEstoque.getProdutoPesquisa(getProduto());
                if (modeloEstoque2.getProdutoAcabado().equals(getProduto())) {
                    int novoNivelEstoque2 = modeloEstoque2.getNivelAtual() + getQtdProducao();
                    modeloEstoque2.setNivelAtual(novoNivelEstoque2);
                    modeloEstoque2.actualizar();
                } else {
                    EstoqueModelo novoEstoque = new EstoqueModelo(
                        getId2(), 1, 
                        (int)getQtdProducao(), 
                        getPrecoUni(),
                        0.0, 
                        "0", 
                        "unidade", 
                        getProduto(),
                        getDataProd(), true);
                    novoEstoque.salvar();
                }

                // Salva o modelo de produção
                ProducaoModelo modelo = new ProducaoModelo(
                    getId(), getQtdProducao(), 
                    getPrecoUni(), getCustoTotal(),
                    getProduto(), getDataProd(), true
                );
                modelo.salvar();
                dispose();

                // Atualiza o nível de estoque para cada ingrediente
                for (int i = 0; i < ingredientesUsados.length; i++) {
                    if(! (ingredientesUsados[i][1]).equals("0"))
                    {
                        EstoqueModelo estoque = fileEstoque.pesquisarIngredienteEstoque(ingredientesUsados[i][0]);
                        int novoNivelEstoque = estoque.getNivelAtual() - Integer.parseInt(ingredientesUsados[i][1]);
                        estoque.setNivelAtual(novoNivelEstoque);
                        estoque.actualizar();
                    }
                }
            }
        }


    }


    public class PainelSul extends JPanel implements ActionListener
    {

        private JButton salvarJB, cancelarJB;
		
		public PainelSul()
		{
			add( salvarJB = new JButton("Salvar") );
			add( cancelarJB = new JButton("Cancelar") );

            salvarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
		}

            public void actionPerformed(ActionEvent evt)
            {
                
                 if(evt.getSource() == salvarJB)
                 {
                    if(editar)
                        if(painelCentro.verificarCamposEditar())
                            painelCentro.alterar();
                        else
                            JOptionPane.showMessageDialog(null, "Campo vazios", "Verificador de campos", JOptionPane.ERROR_MESSAGE);

                    else
                        if(painelCentro.verificarCampos())
                            painelCentro.salvar();
                        else
                            JOptionPane.showMessageDialog(null, "Campo vazios", "Verificador de campos", JOptionPane.ERROR_MESSAGE);

                 }
                else
                    dispose();
            }
    }

    public void definirTema() 
	{
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }
    }
}