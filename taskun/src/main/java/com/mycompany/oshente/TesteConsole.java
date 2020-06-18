/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oshente;
import oshi.SystemInfo;


/**
 *
 * @author pedro
 */
public class TesteConsole {
    public static void main(String[] args) {
        SystemInfo si = new SystemInfo();
        CPU cpu = new CPU();
        RAM ram = new RAM();
        HD hd = new HD();


//        System.out.println(si.getCurrentPlatformEnum());
        System.out.println(si.getOperatingSystem().getManufacturer());
        System.out.println(si.getOperatingSystem().getFamily());
      
        //http://oshi.github.io/oshi/apidocs/oshi/SystemInfo.html
        System.out.println("\nCPU\n{");
        System.out.println(cpu.getProcessador()); 
        System.out.println(cpu.getUptime()); 
        System.out.println(cpu.getDesempenho()); 
        System.out.println(cpu.getClockSpeed()); 
        System.out.println("}\n");

        System.out.println("\nRAM\n{");
        System.out.println(ram.getTotalMemoria());
        System.out.print(ram.getUtilizavel());
        System.out.print("/");
        System.out.println(ram.getDisponivel());
        System.out.println(ram.getVirutalMemoriaTotal());
        System.out.println(ram.getNumerosDePentes());
        System.out.println(ram.getTipoMemoria());
        System.out.println(ram.getClockSpeed());
        System.out.println(ram.getDesempenhoMemoria());
        System.out.println("}\n");
        
        System.out.println("\nDisco Rigido\n{");

        System.out.println(hd.getQtdDisk());
        System.out.println(hd.getSizeDisk());
        System.out.println(hd.getFilesVolume());
        System.out.println(hd.getDiscoAlocado());
        System.out.println(hd.getDiscosTotalLivre());
        System.out.println(hd.getPorcentagemDisponivel());
        System.out.println(hd.getTipoSADisco());
        System.out.println(hd.getPorcentagemOcupada());

        System.out.println("}\n");
    }
}
