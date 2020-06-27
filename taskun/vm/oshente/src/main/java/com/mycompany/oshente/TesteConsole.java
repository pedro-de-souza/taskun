/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oshente;

import java.util.Arrays;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;

/**
 *
 * @author pedro
 */
public class TesteConsole {
    
    

    public static void main(String[] args) {

        //http://oshi.github.io/oshi/apidocs/oshi/SystemInfo.html
        
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        GlobalMemory memoria = hal.getMemory();
        HWDiskStore[] hd = hal.getDiskStores();
        
        

        System.out.println("\n----------------------------------------");
        System.out.println("Sistema operacional:");
        System.out.println(si.getOperatingSystem().getFamily());
        System.out.println(si.getOperatingSystem());
        System.out.println(si.getOperatingSystem().getBitness());

        System.out.println("\nCPU");
        //System.out.println(cpu)
        System.out.println(cpu.getProcessorIdentifier());
        System.out.println(cpu.getProcessorIdentifier().getName());
       
            
        System.out.println("\nMemoria RAM:");
        System.out.println(String.format("%s", memoria.getAvailable()));
        System.out.println(String.format("%s", memoria));
        System.out.println(String.format("%.1f", (memoria.getTotal() / Math.pow(10, 9) - 0.5)));
        
        System.out.println("\nDisco Rígido:");
        System.out.println(hd[0].getSize());
        
      
       
        
//        System.out.println("Processos em execução:");
//        for (OSProcess process : si.getOperatingSystem().getProcesses()) {
//
//            System.out.println(process.getName());
//        }
        System.out.println("\n----------------------------------------Fim");
    }
    
}
