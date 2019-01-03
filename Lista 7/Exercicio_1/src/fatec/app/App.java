package fatec.app;
import java.io.*;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;
import fatec.autenticacao.*;
import fatec.loja.*;
import fatec.banco.*;
import fatec.caixa.*;
import fatec.imprimir.*;
public class App {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {
		System.out.print("Iniciando o caixa ");
		for(int i = 0; i < 5; i++){
			System.out.print(".");
			Thread.sleep(300);
		}
		Authentication autentico = null;
		String entrada;
		String caminho;
		String nomeEstabelecimento;
		int opcao;
		nomeEstabelecimento = JOptionPane.showInputDialog("Nome do Estabelecimento: ");
		caminho = JOptionPane.showInputDialog("Selecione um caminho para guardar os dados ou para recuperar um salvo: ");
		BancoPagamento pagamentos = new BancoPagamento(caminho);
		BancoRecebimento recebimentos = new BancoRecebimento(caminho);
		BancoUser usuarios= new BancoUser(caminho);
		Caixa caixa = new Caixa(caminho);
		entrada = JOptionPane.showInputDialog(
				"Selecione uma opcao:\n" +
				"      (1)...Login\n" +
				"      (0)...Entrar sem login\n"
		);
		opcao = Integer.parseInt(entrada);
		if(opcao == 1) {
			Usuario usr = null;
			String usuario = null;
			String senha = null;
			do {
				usuario = JOptionPane.showInputDialog("Usuário:");
				JLabel rotulo = new JLabel("Senha:");
				JPasswordField password = new JPasswordField(20);  
	            JPanel entSenha = new JPanel();
	            entSenha.add(rotulo);
	            entSenha.add(password);
	            JOptionPane.showMessageDialog(null, entSenha, "Acesso restrito", JOptionPane.WARNING_MESSAGE);
	            senha = password.getText();
				usr = new Usuario(usuario,senha);
				autentico = new Authentication(usr);
				if(usuarios.recuperarUsuarios().contains(usr)) {
					break;
				}else if(usuario.equals("admin") && senha.equals("admin")){
					break;
				}else{
					JOptionPane.showMessageDialog(null, "Digite um usuário válido!!!", "Caixa", 1);
				}
			}while(!(usuario.equals("admin") && senha.equals("admin")));
		}else {
			Usuario usr = null;
			autentico = new Authentication(usr);
		}
		JOptionPane.showMessageDialog(null, "Bem Vindo!!!", "Caixa", 1);
		do{
			if(autentico.isAuthenticated()){
				entrada = JOptionPane.showInputDialog(
						"Selecione uma opção:\n"+
						"      (7)...Salvar Dados para txt\n" +
						"      (6)...Ver Total do Caixa\n" +
						"      (5)...Ver Recebimentos\n" +
						"      (4)...Ver Pagamentos\n" +
						"      (3)...Cadastrar Novo Secretário\n" +
						"      (2)...Registrar Pagamento\n" +
						"      (1)...Registrar Recebimento\n" +
						"      (0)...Sair\n" +
						"  => Valor Total do caixa: " + NumberFormat.getCurrencyInstance().format(caixa.getTotalCaixa())
				);
			}else{
				entrada = JOptionPane.showInputDialog(
						"Selecione uma opção:\n" +
						"      (5)...Ver Recebimentos\n" +
						"      (4)...Ver Pagamentos\n" +
						"      (0)...Sair\n"
				);
			}
			opcao = Integer.parseInt(entrada);
			switch(opcao){
				case 1:{
					String nomeCliente = JOptionPane.showInputDialog("Nome do Cliente: ");
					String cpf = JOptionPane.showInputDialog("CPF do Cliente: ");
					List<Produto> produtos = new ArrayList<Produto>();
					Produto produto;
					String prod = JOptionPane.showInputDialog("Nome do produto: ");
					String preco = JOptionPane.showInputDialog("Preço (R$) do produto: ");
					String quant = JOptionPane.showInputDialog("Quantidade: ");
					produto = new Produto(prod, Double.parseDouble(preco), Integer.parseInt(quant));
					produtos.add(produto);
					opcao = 1;
					do {
						entrada = JOptionPane.showInputDialog(
								"Inserir mais um produto\n" +
										"      (1)...Sim\n" +
										"      (0)...Não\n"
								);
						opcao = Integer.parseInt(entrada);
						if(opcao == 1) {
							prod = JOptionPane.showInputDialog("Nome do produto: ");
							preco = JOptionPane.showInputDialog("Preço (R$) do produto: ");
							quant = JOptionPane.showInputDialog("Quantidade: ");
							produto = new Produto(prod, Double.parseDouble(preco), Integer.parseInt(quant));
							produtos.add(produto);
						}
					}while(opcao == 1);
					opcao = 1;
					CupomFiscal cupom = new CupomFiscal(nomeEstabelecimento,cpf,nomeCliente,produtos);
					Recebimento recebimento = new Recebimento(cupom,caixa);
					recebimentos.salvarRecebimento(recebimento);
					break;
				}
				case 2:{
					String nomeEmpresa = JOptionPane.showInputDialog("Nome da Empresa: ");
					String cnpj = JOptionPane.showInputDialog("CNPJ da Empresa: ");
					List<Produto> produtos = new ArrayList<>();
					Produto produto;
					String prod = JOptionPane.showInputDialog("Nome do produto: ");
					String preco = JOptionPane.showInputDialog("Preço (R$) do produto: ");
					String quant = JOptionPane.showInputDialog("Quantidade: ");
					produto = new Produto(prod, Double.parseDouble(preco), Integer.parseInt(quant));
					produtos.add(produto);
					opcao = 1;
					do {
						entrada = JOptionPane.showInputDialog(
								"Inserir mais um produto\n" +
										"      (1)...Sim\n" +
										"      (0)...Não\n"
								);
						opcao = Integer.parseInt(entrada);
						if(opcao == 1) {
							prod = JOptionPane.showInputDialog("Nome do produto: ");
							preco = JOptionPane.showInputDialog("Preço (R$) do produto: ");
							quant = JOptionPane.showInputDialog("Quantidade: ");
							double p = Double.parseDouble(preco);
							int q = Integer.parseInt(quant);
							produto = new Produto(prod, p, q);
							produtos.add(produto);
						}
					}while(opcao == 1);
					opcao = 2;
					CupomFiscal cupom = new CupomFiscal(nomeEmpresa,cnpj,nomeEstabelecimento,produtos);
					Pagamento pagamento = new Pagamento(cupom,caixa);
					pagamentos.salvarPagamento(pagamento);
					break;
				}
				case 3:{
					String usuario = JOptionPane.showInputDialog("Novo usuário: ");
					String senha = JOptionPane.showInputDialog("Nova senha: ");
					Usuario user = new Usuario(usuario,senha);
					usuarios.salvarUsuario(user);
					JOptionPane.showMessageDialog(null,"Usuário salvo com sucesso!!!","Caixa", 1);
					break;
				}
				case 4:{
					if(pagamentos.recuperarPagamentos().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Não há pagamentos para exibir!!!","Caixa", 1);
						break;
					}else {
						for(Pagamento pagamento : pagamentos.recuperarPagamentos()) {
							pagamento.getPagamento().imprimirCupomFiscal();
						}
						break;
					}
				}
				case 5:{
					if(recebimentos.recuperarRecebimentos().isEmpty()) {
						JOptionPane.showMessageDialog(null,"Não há recebimentos para exibir!!!","Caixa", 1);
						break;
					}else {
						for(Recebimento recebimento : recebimentos.recuperarRecebimentos()) {
							recebimento.getRecebimento().imprimirCupomFiscal();
						}
						break;
					}
				}
				case 6:{
					JOptionPane.showMessageDialog(null, 
							"Saldo Total do caixa: " +
							NumberFormat.getCurrencyInstance().format(caixa.getTotalCaixa())
					, "Caixa", 1);
				}
				case 7:{
					String end = JOptionPane.showInputDialog("Para qual caminho deseja salvar os dados: ");
					Impressao impressao = new Impressao(end);
					impressao.imprimirDados(nomeEstabelecimento, caixa, recebimentos, pagamentos);
					JOptionPane.showMessageDialog(null,"Arquivo texto salvo com sucesso!!!","Caixa", 1);
					break;
				}
				default:{
					if(opcao == 0){
						JOptionPane.showMessageDialog(null,"Você escolheu sair!!!","Caixa",1);
					}else{
						JOptionPane.showMessageDialog(null,"Digite uma opção válida!!!","Caixa",1);
					}
					break;
				}
			}
		}while(opcao != 0);
	}
}
