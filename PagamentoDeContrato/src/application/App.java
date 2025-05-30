package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

public class App {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato: ");
		System.out.print("Numero: ");
		Integer contractNumber = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate data = LocalDate.parse(sc.nextLine(), fmt);
		
		
		System.out.print("Valor do contrato: ");
		Double valorTotal = sc.nextDouble();
		
		Contract contract = new Contract(contractNumber, data, valorTotal);

		System.out.print("Entre com o numero de parcelas: ");
		Integer months = sc.nextInt();
		sc.nextLine();
		
		ContractService service = new ContractService(new PaypalService());
		
		service.processContract(contract, months);
		
		System.out.println("PARCELAS: ");
		for(int i = 0; i < months; i++) {
			System.out.println(contract.getInstallments().get(i).getDueDate() + " - R$"
								+ String.format("%.2f", contract.getInstallments().get(i).getAmount()));
		}
		
	}

}
