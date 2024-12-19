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
        try {
            if( this.getOperationContext().getServer().balance(this.getOperationContext().getAtm().getCardNumber()) >=cant){
                this.getOperationContext().getAtm().expelAmount(cant, 30);
                this.getOperationContext().getAtm().print(List.of(cant + "€"));
               this.getOperationContext().getServer().doOperation(this.getOperationContext().getAtm().getCardNumber(), cant);
            }
            
        } catch (CommunicationException ex) {
            Logger.getLogger(WithdrawCash.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return false;

    }
}
