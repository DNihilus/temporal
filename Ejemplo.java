/*
 * Esta aplicacion muestra como se puede instanciar un objeto
 * llamando a su constructor de defecto y, tambien llamando
 * a un constructor parametrizado 
 */

// Clase MiClase
class MiClase {
	/* ATRIBUTOS */
	int varInstancia;

	/* MÉTODOS */
	// Este es el constructor parametrizado
	MiClase( int dato ) {
		// rellenamos la variable de instancia con los datos
		// que se pasan al constructor
		varInstancia = dato;
    }

	void verVarInstancia() {
		System.out.println( "El Objeto contiene " + varInstancia );
    }
}

// Clase Ejemplo (clase principal)
class Ejemplo {
	public static void main( String args[] ) {
		System.out.println( "Lanzando la aplicacion" );

		// Instanciamos un objeto de este tipo llamando al constructor
		// de defecto (este no se definió en la clase Ejemplo)
		Ejemplo obj = new Ejemplo();

		// Llamamos a la funcion pasandole un constructor parametrizado
		// como parametro
		obj.miFuncion( new MiClase( 100 ) );
    }

	// Esta funcion recibe un objeto y llama a uno de sus metodos
	// para presentar en pantalla el dato que contiene el objeto
	void miFuncion( MiClase objeto){
		objeto.verVarInstancia();
	}
}