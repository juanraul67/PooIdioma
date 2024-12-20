import sienens.ATM;
import urjc.UrjcBankServer;

import java.io.BufferedReader;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.CommunicationException;


public class WithdrawCash extends TitledOperation{

    public WithdrawCash(OperationContext operationContext) {
        super(operationContext);
    }
    @Override
    public String getTitle() {
        return "Retirada en efectivo";
    }

    @Override
    public boolean doOperation() {
        
        for (int cont = 0; cont < 6; cont++)
            this.getOperationContext().getAtm().setOption(cont, null);
        
        this.getOperationContext().getAtm().setInputAreaText(null);
        this.getOperationContext().getAtm().setTitle("Teclee la cantidad que desea");
        ATMNumberCapturer a = new ATMNumberCapturer(this.getOperationContext().getAtm());
        int cant = a.captureAmount();
        String retirada="\tRETIRADA \n ================== \n";
        try {
            if( this.getOperationContext().getServer().avaiable(this.getOperationContext().getAtm().getCardNumber()) >=cant){
                this.getOperationContext().getAtm().setTitle("Cantidad");
                this.getOperationContext().getAtm().expelAmount(cant, 30);
                this.getOperationContext().getAtm().print(List.of(retirada + cant + "€"));
               this.getOperationContext().getServer().doOperation(this.getOperationContext().getAtm().getCardNumber(), cant);
            } else {
                this.getOperationContext().getAtm().setTitle("Saldo insuficiente");
                this.getOperationContext().getAtm().setInputAreaText(null);
                getOperationContext().getAtm().setOption(4,"Terminar");
                char event = this.getOperationContext().getAtm().waitEvent(30);
                if (event == 'E'){
                    ClientGoodbye salida = new ClientGoodbye(getOperationContext());
                    salida.doOperation();
                }
            }
            this.getOperationContext().getAtm().setTitle("Desea hacer más operaciones?");
            this.getOperationContext().getAtm().setOption(4, "Si");
            this.getOperationContext().getAtm().setOption(1, "No");

            char event =this.getOperationContext().getAtm().waitEvent(30);
            if (event == 'E'){
                getOperationContext().getAtm().retainCreditCard(false);
                OptionMenu inicio = new OptionMenu(this.getOperationContext());
                inicio.doOperation();
            } else if (event == 'B'){
                ClientGoodbye salida = new ClientGoodbye(getOperationContext());
                salida.doOperation();
            }

            
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return false;

    }
}
