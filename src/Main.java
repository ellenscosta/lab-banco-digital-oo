import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int resposta = 0;
		double valor;

		Banco banco = new Banco();
		banco.setNome("Santander");

		List<Conta> listaContas = new ArrayList<Conta>();

		System.out.println("Crie sua conta!");
		criarConta(banco, listaContas);

		Scanner ler = new Scanner(System.in);

		do {

			valor = 0;

			System.out.println("\n" + "O que deseja fazer a seguir? \n" + "1 - Ver extrato da conta \n"
					+ "2 - Fazer saque \n" + "3 - Fazer depósito \n" + "4 - Sair");

			resposta = ler.nextInt();

			if (resposta == 1) {

				banco.getContas().get(0).imprimirExtrato();

			} else if (resposta == 2) {

				System.out.println("Quanto deseja sacar?");

				valor = ler.nextDouble();

				if (valor > banco.getContas().get(0).saldo) {
					System.out.println("Não há saldo suficiente na conta.");
				} else {
					banco.getContas().get(0).sacar(valor);
				}

			} else if (resposta == 3) {

				System.out.println("Quanto deseja depositar?");

				valor = ler.nextDouble();

				if (valor <= 0) {
					System.out.println("O valor a ser depositado deve ser maior que 0.");
				} else {
					banco.getContas().get(0).depositar(valor);
				}

			} else {
				System.out.println("Saindo da conta...");
				resposta++;
			}

		} while (resposta < 5);

	}

	public static void criarConta(Banco banco, List<Conta> listaContas) {

		Scanner ler = new Scanner(System.in);

		int tipoConta = 0;

		Cliente cliente = new Cliente();

		System.out.print("Insira seu primeiro nome: ");
		cliente.setNome(ler.nextLine());

		System.out.print("Insira seu sobrenome: ");
		cliente.setSobrenome(ler.nextLine());

		System.out.println("\n" + "Bem vindo(a), " + cliente.getNome() + " " + cliente.getSobrenome() + "!"
				+ " Que tipo de conta deseja criar? \n" + "1 - Corrente \n" + "2 - Poupança");

		tipoConta = ler.nextInt();

		if (tipoConta == 1) {

			Conta cc = new ContaCorrente(cliente);

			listaContas.add(cc);
			banco.setContas(listaContas);

			System.out.println("\n" + "Dados da nova conta corrente: ");
			cc.imprimirInfosComuns();

		} else if (tipoConta == 2) {

			Conta poupanca = new ContaPoupanca(cliente);

			listaContas.add(poupanca);
			banco.setContas(listaContas);

			System.out.println("\n" + "Dados da nova conta poupança: ");
			poupanca.imprimirInfosComuns();
		}

	}
}
