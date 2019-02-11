package model;

public class Laskin {

    private double tulos;  	// Muuttuja tulokselle
    
    public Laskin() {
    	
    }

    public void nollaa() {  // Nollaa tulosmuuttuja
        tulos = 0;
    }

    public double annaTulos() {
        return tulos;
    }

    public void lisaa(double n) {
        tulos = tulos + n;
    }

    public void vahenna(double n) {
        tulos = tulos - n;
    }

    public void kerro(double n) {
    	tulos = tulos * n;
    }

    public void jaa(double n) {
    	if (n==0) throw new ArithmeticException("Nollalla ei voi jakaa");
        tulos = tulos / n;
    }

    public void nelio(double n) {
        tulos = n * n;
    }

    public void neliojuuri(double n) {
    	// Ei vielä toteutettu
    	if (n < 0) throw new ArithmeticException("ei negatiivista neliojuurta");
    	tulos = Math.sqrt(n);
    }

    public void virtaON() {
        // Tähän voisi laittaa alkutoimet
        tulos = 0;
    }

    public void virtaOFF() {
        // Tähän voisi laittaa lopputoimet
    }
}
