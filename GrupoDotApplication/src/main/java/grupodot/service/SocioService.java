/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupodot.service;

import grupodot.entity.Socio;
import grupodot.repository.SocioRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jhon Tenjo
 */
@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepo;

    /**
     *
     * Retorna un número redondeado a dos decimales
     *
     * @param num
     * @return num
     */
    private double redondearDecimales(double num) {
        return Math.round(num * 100.0) / 100.0;
    }

    /**
     *
     * Obtiene todos los socios de la base de datos
     *
     * @return
     */
    public ArrayList<Socio> obtenerTodos() {
        return (ArrayList<Socio>) socioRepo.findAll();
    }

    /**
     *
     * Busca entre todos los socios si existe alguno que tenga un monto igual o
     * más grande al que se envía como parámetro, si los encuentra, devuelve el
     * socio con menor tasa de ínteres, si no encuetra ninguno, retorna null
     *
     * @param monto
     * @return Socio
     */
    public Socio obtenerMejorSocio(long monto) {
        ArrayList<Socio> socios = obtenerTodos();
        //Ordena los socios
        //Ver la documentacion del método compareTo de Socio.java para 
        //comprender como funciona el ordenamiento
        Collections.sort(socios);

        //Búsqueda binaria para encontrar al socio
        int st = 0;
        int end = socios.size() - 1;
        int mid;
        while (st < end) {
            mid = (st + end) / 2;
            if (socios.get(mid).getMontoMaximo() >= monto) {
                end = mid;
            } else {
                st = mid + 1;
            }
        }

        Socio socioFinal = socios.get(end);
        if (socioFinal.getMontoMaximo() >= monto) {
            return socioFinal;
        } else {
            return null;
        }
    }

    /**
     *
     * Realiza la cotización en base a la fórmula del ínteres simple dado un
     * monto
     *
     * @param monto
     * @return String
     */
    public String realizarCotizacion(long monto) {
        Socio socio = obtenerMejorSocio(monto);
        if (socio == null) {
            return null;
        }

        double tasaDeInteres = ((double) socio.getTasa()) / 100;
        double total = redondearDecimales(monto * (1 + (tasaDeInteres * 36)));
        double cuota = redondearDecimales(total / 36);
        return "{\n"
                + "\"nombre\": \"" + socio.getNombre() + "\",\n"
                + "\"tasa\": " + socio.getTasa() + ",\n"
                + "\"total\": " + new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN).toString() + ",\n"
                + "\"cuota\": " + new BigDecimal(cuota).setScale(2, RoundingMode.HALF_EVEN).toString() + "\n"
                + "}";
    }
}
