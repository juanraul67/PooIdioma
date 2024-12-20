
import sienens.ATM;
import urjc.UrjcBankServer;



public class UrjcAtm {
    public static void main(String[] args) {
        ATM atm = new ATM();
        UrjcBankServer server = new UrjcBankServer();
        Idioma idioma = Idioma.Espanol;
        OperationContext c = new OperationContext(atm,server,idioma.getIdioma());
        ClientManagement management = new ClientManagement(c);
        management.doOperation();



    }
}