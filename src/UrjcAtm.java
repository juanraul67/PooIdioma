
import sienens.ATM;
import urjc.UrjcBankServer;



public class UrjcAtm {
    public static void main(String[] args) {
        ATM atm = new ATM();
        UrjcBankServer server = new UrjcBankServer();
        Idioma espanol = Idioma.Espanol;
        OperationContext c = new OperationContext(atm,server,espanol.getIdioma());
        ClientManagement management = new ClientManagement(c);
        management.doOperation();



    }
}