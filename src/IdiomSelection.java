public class IdiomSelection extends AtmOperation{

    public IdiomSelection(OperationContext operationContext) {
        super(operationContext);
    }
    @Override
    public boolean doOperation() {
        for (int cont = 0; cont < 6; cont++)
            this.getOperationContext().getAtm().setOption(cont, null);
        this.getOperationContext().getAtm().setInputAreaText(null);
        
        this.getOperationContext().getAtm().setTitle("Seleccione el idioma: ");
        this.getOperationContext().getAtm().setOption(0, "Español");
        this.getOperationContext().getAtm().setOption(1, "Inglés");
        this.getOperationContext().getAtm().setOption(2, "Catalán");
        this.getOperationContext().getAtm().setOption(3, "Euskera");
        this.getOperationContext().getAtm().setOption(4,"Salir");
        this.getOperationContext().getAtm().setInputAreaText("");
        
        char event = this.getOperationContext().getAtm().waitEvent(30);
        
        if(event == 'A'){
            Idioma espanol = Idioma.Espanol;
            this.getOperationContext().setIdiom(espanol.getIdioma());
        }
        if(event == 'B'){
            Idioma ingles = Idioma.Ingles;
            this.getOperationContext().setIdiom(ingles.getIdioma());
        }
        if(event == 'C'){
            Idioma catalan = Idioma.Catalan;
            this.getOperationContext().setIdiom(catalan.getIdioma());
        }
        if(event == 'D'){
            Idioma euskera = Idioma.Euskera;
            this.getOperationContext().setIdiom(euskera.getIdioma());
        }
        
        return false;
    }
}
