import sienens.ATM;

import javax.naming.CommunicationException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class OptionMenu extends AtmOperation{
    private List<TitledOperation> operationList;



    public OptionMenu(OperationContext operationContext) {
        super(operationContext);
        operationList = List.of(new WithdrawCash(operationContext), new AccountBalance(operationContext),
                new LastOperations(operationContext), new ChangePassword(operationContext));

    }

    @Override
    public boolean doOperation()  {
        boolean salir=false;
        if (getOperationContext().getServer().comunicationAvaiable()){

        while (salir==false){
        for (int cont = 0; cont < 6; cont++)
            this.getOperationContext().getAtm().setOption(cont, null);
        
        //Estos if seleccionan el idioma de las opciones del cajero mostradas
        //dependiendo del idioma que este puesto en el contexto del cajero
        if(Idioma.Catalan.equalsIdioma(this.getOperationContext().getIdiom())==0){
            this.getOperationContext().getAtm().setTitle("Seleccioneu el que vulgueu");
            this.getOperationContext().getAtm().setOption(0, "Retirar diners");
            this.getOperationContext().getAtm().setOption(1, "Consultar saldo");
            this.getOperationContext().getAtm().setOption(2, "Operacions");
            this.getOperationContext().getAtm().setOption(3, "Canviar contrasenya");
            this.getOperationContext().getAtm().setOption(4, "Idioma");
            this.getOperationContext().getAtm().setOption(5, "Acabar");
            this.getOperationContext().getAtm().setInputAreaText("");
            
        }else if(Idioma.Euskera.equalsIdioma(this.getOperationContext().getIdiom())==0){
            this.getOperationContext().getAtm().setTitle("Hautatu nahi duzun aukera");
            this.getOperationContext().getAtm().setOption(0, "Dirua atera");
            this.getOperationContext().getAtm().setOption(1, "Egiaztatu saldoa");
            this.getOperationContext().getAtm().setOption(2, "Eragiketak");
            this.getOperationContext().getAtm().setOption(3, "Aldatu pasahitza");
            this.getOperationContext().getAtm().setOption(4, "Hizkuntza");
            this.getOperationContext().getAtm().setOption(5, "Atera");
            this.getOperationContext().getAtm().setInputAreaText("");
            
        }else if (Idioma.Ingles.equalsIdioma(this.getOperationContext().getIdiom())==0){
            this.getOperationContext().getAtm().setTitle("Select the option you want");
            this.getOperationContext().getAtm().setOption(0, "Withdraw");
            this.getOperationContext().getAtm().setOption(1, "Account balance");
            this.getOperationContext().getAtm().setOption(2, "Operations");
            this.getOperationContext().getAtm().setOption(3, "Change password");
            this.getOperationContext().getAtm().setOption(4, "Language");
            this.getOperationContext().getAtm().setOption(5, "Exit");
            this.getOperationContext().getAtm().setInputAreaText("");
        }
        else{
            this.getOperationContext().getAtm().setTitle("Seleccione la opción que desee");
            this.getOperationContext().getAtm().setOption(0, "Sacar dinero");
            this.getOperationContext().getAtm().setOption(1, "Consultar saldo");
            this.getOperationContext().getAtm().setOption(2, "Operaciones");
            this.getOperationContext().getAtm().setOption(3, "Cambiar contraseña");
            this.getOperationContext().getAtm().setOption(4, "Idioma");
            this.getOperationContext().getAtm().setOption(5, "Terminar");
            this.getOperationContext().getAtm().setInputAreaText("");
            
        }
        char event = this.getOperationContext().getAtm().waitEvent(30);

        if (event == 'A'){
            WithdrawCash sacar = new WithdrawCash(this.getOperationContext());
            sacar.doOperation();

        }else if(event == 'F'){
            ClientGoodbye cb = new ClientGoodbye(this.getOperationContext());
            cb.doOperation();
            salir = true;

        }else if (event == 'B'){
            AccountBalance saldo = new AccountBalance(this.getOperationContext());
            saldo.doOperation();

        }else if (event == 'C'){
            LastOperations last = new LastOperations(this.getOperationContext());
            last.doOperation();
            
        } else if (event == 'E'){
            IdiomSelection idiom = new IdiomSelection(this.getOperationContext());
            idiom.doOperation();
        }



        }
        } else {
            ErrorExit error = new ErrorExit(this.getOperationContext());
            error.doOperation();
        }
        
        return false;
    }


}
