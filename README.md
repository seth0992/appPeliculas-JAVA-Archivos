# Laboratorio Programado - Manejo Archivos - POO

## Objetivo

Aplicar los conceptos de manejo de archivos para almacenar y recuperar información utilizando GUI en JAVA.

## Creación del Proyecto

1. Abre NetBeans y selecciona "Nuevo Proyecto".
2. Elige "Java With Ant" → "Aplicación Java" y haz clic en "Siguiente".
3. Ingresa el nombre del proyecto (por ejemplo, "**appPeliculasUsoArchivos**") y selecciona la ubicación. Haz clic en "Finalizar".
4. Se deberá agregar dos paquetes al proyecto uno para almacenar las clases de los modelos (Models) y el otro que almacenara los formularios o GUI de la aplicación (Views):
   ![paquetes](./recursos/img/CreacionProyecto.png)

5. En el paquete de Modelos (Models) se deberán crear las siguientes clases **Pelicula** y **ManejoArchivo** y en el paquete de Vistas (Views) se deberá crear un nuevo JFrame Forms con el nombre **frmPeliculas**:
   ![Clases](./recursos/img/ClasesProyecto.png)

### Modelo de la clase Película

En este caso el código que se utilizara para la clase de Pelicula.java es el siguiente:

```java
package Models;

/**
 *
 * @author seth
 */
public class Pelicula {

    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;

    public Pelicula(String codigo, String nombre, String categoria, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double calculoIVA(){
        return getPrecio() * 0.13;
    }

    public double precioFinal(){
        return (getPrecio() + calculoIVA());
    }

}
```

### Estructura de la clase ManejoArchivo

En este caso el código que se utilizara para la clase de ManejoArchivo.java es el siguiente:

```java
/*
 * Clase ManejoArchivo
 *
 * Esta clase proporciona métodos para manejar el almacenamiento y recuperación de objetos Pelicula
 * en un archivo de texto. Utiliza operaciones de lectura y escritura de archivos para mantener una
 * lista de películas que se puede actualizar y recuperar de forma persistente.
 */

package Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase para gestionar el archivo que contiene datos de películas.
 * Permite agregar nuevas películas al archivo y leer la lista de películas existentes.
 */
public class ManejoArchivo {
    // Ruta del archivo donde se almacenan los datos de las películas.
    private final String ruta = "C:/datos/listaPelicula.txt";

    // Lista para almacenar las películas que se leen del archivo.
    private ArrayList<Pelicula> lista = new ArrayList<>();

    /**
     * Agrega una nueva película al archivo.
     *
     * @param pelicula El objeto Pelicula a agregar.
     * Este método escribe los datos de la película al final del archivo especificado en la ruta.
     * Los datos de la película se escriben en el formato: codigo:nombre:categoria:precio
     */
    public void agregar(Pelicula pelicula) {
        try (FileWriter fw = new FileWriter(ruta, true)) {
            String cadena = pelicula.getCodigo() + ":" + pelicula.getNombre() + ":" + pelicula.getCategoria() + ":" + pelicula.getPrecio() + System.lineSeparator();
            fw.write(cadena);
        } catch (IOException ex) {
            System.err.println("Error al agregar película: " + ex.getMessage());
        }
    }

    /**
     * Lee los datos de las películas desde el archivo y los almacena en una lista.
     *
     * @return Una lista de objetos Pelicula que representan las películas almacenadas en el archivo.
     * Este método limpia la lista existente de películas y lee el archivo línea por línea,
     * creando un nuevo objeto Pelicula por cada línea válida y agregándolo a la lista.
     */
    public ArrayList<Pelicula> leer() {
        lista.clear(); // Limpia la lista existente de películas
        try (BufferedReader fr = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = fr.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 4) { // Asegura que la línea tiene exactamente cuatro partes
                    Pelicula pelicula = new Pelicula(partes[0], partes[1], partes[2], Double.parseDouble(partes[3]));
                    lista.add(pelicula);
                } else {
                    System.err.println("Error de formato en línea: " + linea);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error al leer películas: " + ex.getMessage());
        }
        return lista;
    }
}

```

**Nota:** recuerde que la carpeta y el archivo debe de existir.

### GUI para frmPeliculas.java

A continuación se muestra la interfaz gráfica de usuario

![GUI](./recursos/img/gui.png)

Detalle de los controles utilizados:

<table>
 <thead>
    <tr>
        <th>Control </th>
        <th>Propiedad </th>
        <th>Valor</th>
    </tr>
  </thead>
  <tbody>
    <tr>
    <td>JFrame</td>
    <td>title</td>
    <td>Películas</td>
  </tr>
  <tr>
    <td>JLabel</td>
    <td>text</td>
    <td>Código de la Película</td>
  </tr>
  <tr>
    <td rowspan=2>Text Field</td>
    <td>text</td>
    <td>"  " → Vació</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>txtCodigo</td>
  </tr>
  <tr>
    <td>JLabel</td>
    <td>text</td>
    <td>Nombre de la Película</td>
  </tr>
  <tr>
    <td rowspan=2>Text Field</td>
    <td>text</td>
    <td>"  " →  Vació</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>txtNombre</td>
  </tr>
    <tr>
    <td>JLabel</td>
    <td>text</td>
    <td>Categoría de la Película</td>
  </tr>
  <tr>
    <td rowspan=2>Text Field</td>
    <td>text</td>
    <td>"  " →  Vació</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>txtCategoria</td>
  </tr>
   <tr>
    <td>JLabel</td>
    <td>text</td>
    <td>Precio de la Película</td>
  </tr>
  <tr>
    <td rowspan=2>Text Field</td>
    <td>text</td>
    <td>"  " →  Vació</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>txtPrecio</td>
  </tr>
  <tr>
    <td rowspan=2>Button</td>
    <td>text</td>
    <td>Registrar</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>btnNuevo</td>
  </tr>
    <tr>
    <td rowspan=2>Button</td>
    <td>text</td>
    <td>Guardar</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>btnGuardar</td>
  </tr>
    <tr>
    <td rowspan=2>Button</td>
    <td>text</td>
    <td>Listar</td>
  </tr>
  <tr>
    <td>Variable Name</td>
    <td>btnListar</td>
  </tr>
  <tr>
    <td>TextArea</td>
    <td>Variable Name</td>
    <td>txaArchivo</td>
  </tr>
  </tbody>
</table>

!!! Importante 
    Se debe de considerar eliminar el bloque de arranque del JFrame, para que la aplicación solo tenga un punto de iniciación.
    Para ello elimine el método *public static void main(String args[]) { .. }* del **JFrame**

En este punto se deberá de importar algunos librerías a como se muestra en el código siguiente:

```java
import Models.ManejoArchivo;
import Models.Pelicula;
import java.util.ArrayList;
import javax.swing.JOptionPane;

```

Luego a nivel de clase se deberán crear los siguientes atributos o variables:
```java
ManejoArchivo manejoArc = new ManejoArchivo();
```

Ahora en el evento ActionPerformed del botón Nuevo deberemos colocar el siguiente codigo:
```java
    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                         
       txtCodigo.setText("");
       txtNombre.setText("");
       txtCategoria.setText("");
       txtPrecio.setText("");
    } 
```

Ahora en el evento ActionPerformed del botón Guardar deberemos colocar el siguiente codigo:
```java
   private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Pelicula p = new Pelicula(txtCodigo.getText(), txtNombre.getText(), txtCategoria.getText(), Double.parseDouble(txtCodigo.getText()));
        try{
        manejoArc.agregar(p);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }    
```

Ahora en el evento ActionPerformed del botón Listar deberemos colocar el siguiente codigo:
```java
    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        txaArchivo.setText("Codigo\t Nombre \t Categoria \t Precio \t IVA \t Precio Final \n");
        try{
           ArrayList<Pelicula> lista = manejoArc.leer();
            for(Pelicula p : lista){
                txaArchivo.append(p.getCodigo() + "\t" + p.getNombre() + "\t" + p.getCategoria() + "\t" +p.getPrecio() + "\t" + p.calculoIVA() +"\t"+p.precioFinal()+"\n");            
            }
        } catch(Exception ex){
          JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }    
```
!!! tip el código Completo de JFrame es el siguiente: 
```java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Models.ManejoArchivo;
import Models.Pelicula;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author seth
 */
public class frmPeliculas extends javax.swing.JFrame {

    
    ManejoArchivo manejoArc = new ManejoArchivo();
    
    
    /**
     * Creates new form frmPeliculas
     */
    public frmPeliculas() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaArchivo = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Código de la Película");
        jLabel1.setToolTipText("");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtNombre.setToolTipText("");

        jLabel2.setText("Nombre de la Película");

        jLabel3.setText("Categoría de la Película");

        jLabel4.setText("Precio de la Película");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        txaArchivo.setColumns(20);
        txaArchivo.setRows(5);
        jScrollPane1.setViewportView(txaArchivo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnGuardar)
                    .addComponent(btnListar)
                    .addComponent(btnNuevo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
    }                                         

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        Pelicula p = new Pelicula(txtCodigo.getText(), txtNombre.getText(), txtCategoria.getText(), Double.parseDouble(txtCodigo.getText()));
        try{
        manejoArc.agregar(p);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }                                          

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                         
       txtCodigo.setText("");
       txtNombre.setText("");
       txtCategoria.setText("");
       txtPrecio.setText("");
    }                                        

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        txaArchivo.setText("Codigo\t Nombre \t Categoria \t Precio \t IVA \t Precio Final \n");
        try{
           ArrayList<Pelicula> lista = manejoArc.leer();
            for(Pelicula p : lista){
                txaArchivo.append(p.getCodigo() + "\t" + p.getNombre() + "\t" + p.getCategoria() + "\t" +p.getPrecio() + "\t" + p.calculoIVA() +"\t"+p.precioFinal()+"\n");            
            }
        } catch(Exception ex){
          JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }                                         

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPeliculas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txaArchivo;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration                   
}

```



Por ultimo en la clase principal en el método **main** se realiza el código para iniciar el formulario:

```java
  public static void main(String[] args) {
         frmPeliculas p = new frmPeliculas();
        p.setVisible(true);
    }
```