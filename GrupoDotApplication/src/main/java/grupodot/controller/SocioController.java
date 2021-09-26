/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupodot.controller;

import grupodot.service.SocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jhon Tenjo
 */
@RestController
@RequestMapping("/cliente")
public class SocioController {

    @Autowired
    private SocioService socioService;

    /**
     *
     * Se envía por la url un monto mayor a cero y en caso de que exista algún
     * socio con un monto mayor o igual al requerido devuelve una cotización con
     * la menor tasa de interés, en caso de que no exista devuelve un mensaje
     * diciendo que no existe ningún socio disponible.
     *
     * @param monto
     * @return JSON
     */
    @RequestMapping(method = RequestMethod.GET, path = "cotizaciones/{monto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obtenerMejorCotizacion(@PathVariable long monto) {

        if (monto <= 0) {
            String error = "{\n"
                    + "\"error\": \"El monto debe ser mayor a 0\" \n"
                    + "}";
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        String resultadoCotizacion = socioService.realizarCotizacion(monto);

        if (resultadoCotizacion == null) {
            String error = "{\n"
                    + "\"mensaje\": \"No hay socio disponible\" \n"
                    + "}";
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

        return new ResponseEntity<>(resultadoCotizacion, HttpStatus.OK);
    }
}
