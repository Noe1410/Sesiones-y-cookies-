package clases;

import java.util.ArrayList;

public class Carrito {

  private ArrayList<ElementoDeCarrito> elementos = new ArrayList<ElementoDeCarrito>();

  public Carrito() {
  }
  
  public Carrito(String carrito){
      
      if(!carrito.equals("")){
        String[] carro = carrito.split(">");
        for(int i = 0; i < carro.length; i+=5){
            int cantidad = Integer.parseInt(carro[i]);
            int codigo = Integer.parseInt(carro[i+1]);
            double precio = Double.parseDouble(carro[i+2]);
            Producto producto = new Producto(codigo,carro[i+3],precio,carro[i+4]);
            ElementoDeCarrito elemento = new ElementoDeCarrito(producto, cantidad);
            elementos.add(elemento);
        } 
      }
  }

  public Carrito(ArrayList<ElementoDeCarrito> elementos) {
    this.elementos = elementos;
  }

  public ArrayList<ElementoDeCarrito> getElementos() {
    return elementos;
  }

  public boolean existeElementoConCodigo(int codigo) {
    return this.posicionElementoConCodigo(codigo) != -1;
  }

  public void meteProductoConCodigo(int codigo) {
    if (this.existeElementoConCodigo(codigo)) {
      elementos.get(posicionElementoConCodigo(codigo)).incrementaCantidad(1);
    } else {
      Catalogo catalogo = new Catalogo();
      catalogo.cargaDatos();
      elementos.add(new ElementoDeCarrito(catalogo.productoConCodigo(codigo), 1));
    }
  }

  public void eliminaProductoConCodigo(int codigo) {
    if (existeElementoConCodigo(codigo)) {
      int i = 0;
      int posicion = 0;
      for (ElementoDeCarrito elemento : elementos) {
        if (elemento.getProducto().getCodigo() == codigo) {
          posicion = i;
        }
        i++;
      }
      elementos.remove(posicion);
    }
  }

  private int posicionElementoConCodigo(int codigo) {
    int i = 0;
    for (ElementoDeCarrito elemento : elementos) {
      if (elemento.getProducto().getCodigo() == codigo) {
        return i;
      }
      i++;
    }
    return -1;
  }
  
  @Override
  public String toString(){
      String carrito_serializado = "";
      for(ElementoDeCarrito e : elementos){
          carrito_serializado += e.getCantidad() +">"+e.getProducto().getCodigo()+
                                                 ">"+e.getProducto().getPrecio()+
                                                 ">"+e.getProducto().getNombre()+
                                                 ">"+e.getProducto().getImagen()+
                                                 ">";
      }
      
      return carrito_serializado;
  }
}
