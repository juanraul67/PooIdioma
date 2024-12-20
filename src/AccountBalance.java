import javax.naming.CommunicationException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountBalance extends TitledOperation{
    public AccountBalance(OperationContext operationContext) {
        super(operationContext);
    }

    @Override
    public String getTitle(){
        return "Account Balance";
    }
    @Override
    public boolean doOperation()  {
        if (getOperationContext().getServer().comunicationAvaiable()){
        for (int cont = 0; cont < 6; cont++)
            this.getOperationContext().getAtm().setOption(cont, null);

        String ticket="        SALDO DISPONIBLE: \n ******************************* \n";


        this.getOperationContext().getAtm().setTitle("Balance");
        try {
            this.getOperationContext().getAtm().setInputAreaText("Tu balance es "
                    +(this.getOperationContext().getServer().balance(this.getOperationContext().getAtm().getCardNumber()))+"€");
            this.getOperationContext().getAtm().print(List.of( ticket +
                    (this.getOperationContext().getServer().balance(this.getOperationContext().getAtm().getCardNumber()))
                    + "€"));
        } catch (CommunicationException ex) {
            Logger.getLogger(AccountBalance.class.getName()).log(Level.SEVERE, null, ex);
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
    } else {
            ErrorExit errorExit = new ErrorExit(this.getOperationContext());
            errorExit.doOperation();
            return false;
        }
        return false;
    }

}
