/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Film;
import beans.Spettacolo;
import java.io.Serializable;

/**
 *
 * @author Paolo
 */
public class Classi {
    public static class FilmSpettacolo{
        private Film f;
        private Spettacolo s;

        public Film getF() {
            return f;
        }

        public void setF(Film f) {
            this.f = f;
        }

        public Spettacolo getS() {
            return s;
        }

        public void setS(Spettacolo s) {
            this.s = s;
        }
        
    }
}

