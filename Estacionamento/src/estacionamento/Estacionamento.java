package estacionamento;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexandre
 */
public class Estacionamento {

    /**
     * @param args the command line arguments
     */
private static final ArrayList<Veiculo> vetVeiculo = new ArrayList<Veiculo>();

private static String ExibeMenu() {
    return JOptionPane.showInputDialog(null, " ##Menu Estacionamento##\n"
            + "1- Registrar entrada de veiculo!\n"
            + "2- Listar Veiculo no estacionamento!\n"
            + "3- Consultar veiculo!\n"
            + "4- Realizar pagamento!\n"
            + "5- Realizar saida do veiculo!\n"
            + "0- Sair do Sistema!");
}

public static void main(String[] args) {
    carregadadosVeiculos();
    int op;
    do {
        op = Integer.parseInt(ExibeMenu());
        switch (op) {
            case 0:
                salvaDadosVeiculo();
                JOptionPane.showMessageDialog(null, "Saindo...");

                break;
            case 1:
                registraCarro();
                break;
            case 2:
                listarVeiculo();

                break;
            case 3:
                consultaVeiculo();

                break;
            case 4:
                realizaPagamento();

                break;
            case 5:
                saidaVeiculo();

                break;

            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
        }

    } while (op != 0);


}

private static void registraCarro() {
    Veiculo veiculo = new Veiculo();
    veiculo.setPlaca(JOptionPane.showInputDialog(null, "Informe a placa do veiculo"));
    veiculo.setCor(JOptionPane.showInputDialog(null, "Informe a Cor do veiculo"));
    veiculo.setModelo(JOptionPane.showInputDialog(null, "Informe o Modelo do Veiculo"));
    veiculo.setFabricante(JOptionPane.showInputDialog(null, "Informe  o Fabricante do Veiculo"));
    veiculo.sethEntrada(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a Hora de entrada do veiculo")));
    veiculo.setmEntrada(Integer.parseInt(JOptionPane.showInputDialog(null, "Informe os Minutos de entrda do veiculo")));
    vetVeiculo.add(veiculo);

    JOptionPane.showMessageDialog(null, "Cliente Cadastrado com Sucesso!");

}

private static void listarVeiculo() {
    Iterator<Veiculo> it = vetVeiculo.iterator();
    Veiculo temp;
    String dados = "";
    while (it.hasNext()) {
        temp = it.next();
        dados = dados + temp.getPlaca() + " - " + temp.getModelo() + "\n";
    }
    JOptionPane.showMessageDialog(null, dados);
}

private static void consultaVeiculo() {

    String placaConsultar = JOptionPane.showInputDialog(null, "Entre com a placa do veiculo que deseja consultar");

    boolean achou = false;
    //varremos o vetor em busca do elemento procurado
    Iterator<Veiculo> it = vetVeiculo.iterator();
    Veiculo temp;
    while (it.hasNext()) {
        temp = it.next();
        //se encontrar o codigo que procuramos, mostramos os dados deste elemento!
        if (temp.getPlaca().equals(placaConsultar)) {
            achou = true;
            String pago = "";
            if (temp.getPagamento()) {
                pago = "Carro Pagou Estacionamento";
            } else {
                pago = "Carro Nao Pagou Estacionamento";
            }

            JOptionPane.showMessageDialog(null, "placa >" + temp.getPlaca()
                    + "\n Cor> " + temp.getCor()
                    + "\nmodelo> " + temp.getModelo()
                    + "\nfabricante> " + temp.getFabricante()
                    + "\nHora de entrada> " + temp.gethEntrada()
                    + "\nminuto entrada> " + temp.getmEntrada()
                    + "\n" + pago);


        }

    }
    //Se nao encontrar...
    if (!achou) {
        JOptionPane.showMessageDialog(null, "Cliente nao localizado!");
    }


}
// realiza o pagamento

private static void realizaPagamento() {

    String placa = JOptionPane.showInputDialog(null, "Entre com Placa");
    Iterator<Veiculo> it = vetVeiculo.iterator();
    Veiculo temp;
    while (it.hasNext()) {
        temp = it.next();
        if (temp.getPlaca().equals(placa)) {
            int hAtual = (Integer.parseInt(JOptionPane.showInputDialog(null, "entre com ahora atual")));
            int mAtual = (Integer.parseInt(JOptionPane.showInputDialog(null, " entra com os minutos atual")));
            int mTotal = hAtual * 60 + mAtual;
            int mEntrada = temp.gethEntrada() * 60 + temp.gethEntrada();
            int mEstadia = mTotal - mEntrada;
            int hEstadia = mEstadia / 60;
            
            JOptionPane.showMessageDialog(null, "voce deve Pagar" + (hEstadia * 1.5));
            temp.setPagamento(true);
            JOptionPane.showMessageDialog(null, "Pagamento com sucesso");
            return;
        }
    }
    JOptionPane.showMessageDialog(null, "Nao foi Possivel realizar pagamento");

}
// na veriaficação se o veiculo esta pago//

private static void saidaVeiculo() {
       String codigoConsultar = JOptionPane.showInputDialog(null, "Entre com a placa do veiculo: ");
    boolean achou = false;
    Iterator<Veiculo> it = vetVeiculo.iterator();
    Veiculo temp;

    while (it.hasNext()) {
        temp = it.next();
        if (temp.getPlaca().equals(codigoConsultar)) {
            achou = true;
            if (temp.getPagamento()) {
                int hAtual = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe as Horas:"));
                int mAtual = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe os minutos: "));
                int mAtualTotal = (temp.gethEntrada() * 60 + temp.getmEntrada());
                int mSaidaTotal = (hAtual * 60 + mAtual);
                int mTotal = mSaidaTotal - mAtualTotal;
                vetVeiculo.remove(temp);

        if (mTotal <= 20) {
                         JOptionPane.showMessageDialog(null, "Placa > " + temp.getPlaca()
                        + "\nHora de Entrada > " + temp.gethEntrada() + ":" + temp.getmEntrada()
                        + "\nHora de Saida > " + temp.gethEntrada() + ":" + temp.getmEntrada()
                        + "\n\nSAIDA LIBERADA!");





                } else {
                     JOptionPane.showMessageDialog(null, "Multa! voce ultrapassou o tempo permitido, recebera multa de."
                            + "\nTotal da Multa: R$" + 0.5);
                    return;


                }


            }
        }
    }
}

private static void salvaDadosVeiculo() {
    try ( //Salva os veiculo
            FileOutputStream f = new FileOutputStream("veiculo.arq")) {
        ObjectOutputStream saida = new ObjectOutputStream(f);
        Iterator<Veiculo> it = vetVeiculo.iterator();
        while (it.hasNext()) {
            saida.writeObject(it.next());
        }
        saida.flush();
        saida.close();


    } catch (Exception ex) {
    }

 
}

private static void carregadadosVeiculos() {
    try {
        //carrega os veuculo
        FileInputStream f = new FileInputStream("veiculo.arq");
        ObjectInputStream entrada = new ObjectInputStream(f);
        Veiculo temp;
        while ((temp = (Veiculo) entrada.readObject()) != null) {
            vetVeiculo.add(temp);
        }
        entrada.close();
        f.close();

    } catch (Exception ex) {
    }
}

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class Veiculo {

        public Veiculo() {
        }

        private void setPlaca(String showInputDialog) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setCor(String showInputDialog) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setModelo(String showInputDialog) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setFabricante(String showInputDialog) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void sethEntrada(int parseInt) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setmEntrada(int parseInt) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getPlaca() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getModelo() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private boolean getPagamento() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getCor() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getFabricante() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String gethEntrada() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private String getmEntrada() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private void setPagamento(boolean b) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}

