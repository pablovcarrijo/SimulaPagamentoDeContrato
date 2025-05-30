package model.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.Contract;
import model.entities.Installments;

public class ContractService {

	private OnlinePaymentService paymentService;
	private List<Installments> tempList = new ArrayList<>();
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		Double parcelas = contract.getTotalValue() / (double)months;
		
		for(int i = 1; i <= months; i++) {
			Double parcelaSimples = paymentService.interest(parcelas, i);
			Double parcelaFinal = paymentService.paymentFree(parcelaSimples);
			
			LocalDate proximoMes = contract.getDate().plusMonths(i);
			
			tempList.add(new Installments(proximoMes, parcelaFinal));	
		}
		contract.setInstallments(tempList);
		
	}
	
}
