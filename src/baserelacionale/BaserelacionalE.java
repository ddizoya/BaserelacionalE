/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleDriver;

/**
 *
 * @author oracle
 */
public class BaserelacionalE {

    String usuario = "hr";
    String password = "hr";
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    ResultSetMetaData rsmd;
    Statement st;
    ResultSet rs;
    ArrayList<Product> lista = new ArrayList<>();

    public BaserelacionalE() {
        try {
            DriverManager.deregisterDriver(new OracleDriver());
            System.err.println("*Se ha registrado el Driver de Oracle. ");
        } catch (SQLException ex) {
            Logger.getLogger(BaserelacionalE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection conectarse() throws SQLException {
        Connection conn = DriverManager.getConnection(url, usuario, password);
        return conn;
    }

    public void crearArrayProduct() throws SQLException {
        st = conectarse().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //Hacemos que, sobre todo, el resultset sea iterable.
        rs = st.executeQuery("select produtos.* from produtos");
        rsmd = rs.getMetaData();

        int numCol = rsmd.getColumnCount();
        System.out.println("Tamaño columnas: " + numCol);
        int i = 0;
        Product p;
        while (rs.next()) { //Crear igual número de objetos al numero de registros/filas que hay. 
            p = new Product();
            lista.add(p);
        }
    
        rs.beforeFirst(); //Volvemos al inicio del resultset. 
        i = 1; //Lo empleamos para movernos por filas.
        while (rs.next()) {        
      
                if (rsmd.getColumnTypeName(1).equalsIgnoreCase("varchar2")) {
                    String col = rs.getString(1);
                    lista.get(i-1).setCod(col); //Le restamos -1 porque el array empieza en 0 y i en 1.
                }
                if (rsmd.getColumnTypeName(1).equalsIgnoreCase("number")) {
                    int col1 = rs.getInt(1);
                    lista.get(i-1).setCod(col1);
                }

                if (rsmd.getColumnTypeName(2).equalsIgnoreCase("varchar2")) {
                    String col = rs.getString(2);
                    lista.get(i-1).setDesc(col);
                }
                if (rsmd.getColumnTypeName(2).equalsIgnoreCase("number")) {
                    int col = rs.getInt(2);
                    lista.get(i-1).setDesc(col);
                }

                if (rsmd.getColumnTypeName(3).equalsIgnoreCase("varchar2")) {
                    String col = rs.getString(3);
                    lista.get(i-1).setPrezo(col);
                }
                if (rsmd.getColumnTypeName(3).equalsIgnoreCase("number")) {
                    int col1 = rs.getInt(3);
                    lista.get(i-1).setPrezo(col1);
                }
            i++;
        }
        
        imprimirArray();

    }

    
    private void imprimirArray(){
        int i = 0;
        while (i < lista.size()){
            System.out.println(lista.get(i).getCod() +","+ lista.get(i).getDesc() + "," + lista.get(i).prezo);
            i++;
        }
    }
    public static void main(String[] args) throws SQLException {
       new BaserelacionalE().crearArrayProduct();
    }

}
