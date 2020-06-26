/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oshente;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.util.List;
import java.util.stream.IntStream;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.util.FormatUtil;
import oshi.util.Util;

/**
 *
 * @author buna_
 */
public class GPU extends Initialize {

    private GraphicsCard[] gpu = new SystemInfo().getHardware().getGraphicsCards();
    private Double calculo;
    private long oldTicks[];

    public GPU() {
        gpu = new SystemInfo().getHardware().getGraphicsCards();
        calculo = Math.pow(10, 9);
        oldTicks = new long[CentralProcessor.TickType.values().length];
    }

    public String getNomePlaca() {
        String nome = "";
        for (Integer d = 0; d < gpu.length; d++) {
            nome = gpu[0].getName();
        }
        return nome;
    }

    public String getRAM() {
        String ram = "";
        for (GraphicsCard graphicsCard : gpu) {
            Double r = Double.valueOf(FormatUtil.formatBytes(graphicsCard.getVRam()).replaceAll(" GiB", "").replaceAll(",", "."));
            ram = String.format("%.0f", Math.ceil(r));
        }
        return ram;
    }

    public String getDesempenho() {
        CentralProcessor cpu = new SystemInfo().getHardware().getProcessor();
        double dCp = cpu.getSystemCpuLoadBetweenTicks(oldTicks);
        oldTicks = cpu.getSystemCpuLoadTicks();
        
        String desempenho = "";
        for (Integer d = 0; d < gpu.length; d++) {
            Double c =  dCp * 100.0;
            Double g =  (gpu[0].getVRam()/calculo) >= 5?5:gpu[0].getVRam()/calculo;
            desempenho = String.format("%.0f", c / g);
        }
        return desempenho;
    }

    public String getVersaoGPU() {
        String versao = "";
//        for (GraphicsCard graphicsCard : gpu) {
        for (Integer d = 0; d < gpu.length; d++) {
            versao = gpu[0].getVersionInfo().replaceAll("DriverVersion=", "");
        }

        return versao;
    }

    public void sensores() {
        Components components = JSensors.get.components();

        //Lista de cpus
        List<Cpu> cpus = components.cpus;

        //Lista de gpus
        List<Gpu> gpus = components.gpus;

        if (gpus != null) {
            for (final Gpu gpu : gpus) {
                //Print temperatures
                List<Temperature> temps = gpu.sensors.temperatures;
                gpu.sensors.temperatures.forEach((temperatures) -> {
                    System.out.println("Temperatura: " + temperatures.value);
                });
//                    for (final Temperature temp : temps) {
//                        System.out.println(temp.name + ": " + temp.value + " C");

                //Print fan speed
                List<Fan> fans = gpu.sensors.fans;
                gpu.sensors.fans.forEach((fan) -> {
                    System.out.println(fan.value);
                });
            }
        }
    }

}
