import javax.naming.CommunicationException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LastOperations extends TitledOperation{
    public LastOperations(OperationContext operationContext) {
        super(operationContext);

    }

    @Override
    public boolean doOperation()  {

        if (getOperationContext().getServer().comunicationAvaiable()){
            String ticket="    Ultimas Operaciones: \n ======================= \n";
            long cn=getOperationContext().getAtm().getCardNumber();
            OperationContext opContext = getOperationContext();



            for (int cont = 0; cont < 6; cont++)
                this.getOperationContext().getAtm().setOption(cont, null);
            this.getOperationContext().getAtm().setInputAreaText(null);
            this.getOperationContext().getAtm().setTitle("Imprimiendo ticket...");


            try {
                String operaciones = opContext.getServer().getLastOperations(cn).toString();
                this.getOperationContext().getAtm().print(Collections.singletonList(ticket + operaciones + "\n"));

            } catch (CommunicationException exception){
                Logger.getLogger(LastOperations.class.getName()).log(Level.SEVERE, null, exception);

            }
            this.getOperationContext().getAtm().setOption(4, "Volver");
            char event =this.getOperationContext().getAtm().waitEvent(30);
            if (event == 'E'){
                getOperationContext().getAtm().retainCreditCard(false);
                OptionMenu inicio = new OptionMenu(this.getOperationContext());
                inicio.doOperation();
            }
        } else {
            ErrorExit errorExit = new ErrorExit(this.getOperationContext());
            errorExit.doOperation();

        }
        return true;

    }
    public String getTitle() {
        return "Last Operations";
    }
}