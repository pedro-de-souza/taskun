/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oshente;

import java.util.ArrayList;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.util.FormatUtil;

/**
 *
 * @author pedro
 */
public class HD {

    private HWDiskStore[] hd;
    private FileSystem sistemaArquivo;
    private OSFileStore[] sisArray;
    private String hardDiskPrincipal;

    public HD() {
        hd = new SystemInfo().getHardware().getDiskStores();
        sistemaArquivo = new SystemInfo().getOperatingSystem().getFileSystem();
        sisArray = sistemaArquivo.getFileStores();
    }

    public String getQtdDisk() {
        Integer cont = 0;
        for (HWDiskStore disk : hd) {
            boolean discoNull = disk.getReads() > 0 || disk.getWrites() > 0;
            if (discoNull) {
                cont++;
            }
        }
        return String.format(" %d Discos disponíveis\n", cont);
    }

    public String getSizeDisk() {
        String sizeHds = "";
        for (Integer d = 0; d < hd.length; d++) {
            boolean discoNull = hd[d].getReads() > 0 || hd[d].getWrites() > 0;
            if (discoNull) {
                sizeHds = String.format(" [DISK %d : %s]\n", d, FormatUtil.formatBytesDecimal(hd[d].getSize()));
            }
        }
        return sizeHds;
    }

    public String getDiscoAlocado() {
        String discos = "";
        for (OSFileStore d : sisArray) {
            boolean discoNull = "".equals(d.getType());
            if (!discoNull) {
                discos = String.format(" %s %n", d.getName());
            }
        }
        return discos;
    }

    public String getTipoSADisco() {
        String tipos = "";
        for (OSFileStore t : sisArray) {
            boolean discoNull = "".equals(t.getType());
            if (!discoNull) {
//                tipos = String.format(" %s Tipo de sistema de arquivos: %s %n", t.getMount(), t.getType());
                tipos = String.format("%s %n", t.getType());
            }
        }
        return tipos;
    }

    public String getDiscosTotalLivre() {
        String livreTotal = "";
        for (OSFileStore d : sisArray) {
            boolean discoNull = "".equals(d.getType());
            if (!discoNull) {
                long livre = d.getUsableSpace();
//                utilizavelTotal += String.format(" %s Utilizável: %s Total: %s %n", d.getMount(),
//                        FormatUtil.formatBytes(utilizavel), FormatUtil.formatBytes(d.getTotalSpace())
                livreTotal = String.format(" %s livre de %s %n",
                        FormatUtil.formatBytesDecimal(livre), FormatUtil.formatBytesDecimal(d.getTotalSpace())
                );
            }
        }
        return livreTotal;
    }

//    public List<Long> getPorcentagemDisponivel() {
////        long desempenho = 0l;
//        List<Long> desempenho = new ArrayList<Long>();
//
//        for (OSFileStore p : sisArray) {
//               long utilizavel = p.getUsableSpace();
//            boolean discoNull = "".equals(p.getType());
//            if (utilizavel!=0 && !discoNull) {
////                long utilizavel = p.getUsableSpace();
////                Double porcentagem = (100d * utilizavel) / p.getTotalSpace();
//                Double porcentagem = (100d * utilizavel);
////                desempenho = String.format(" %s %.0f%% %n", p.getMount(), porcentagem);
//                desempenho.add(utilizavel);
//            }
//        }
//        return desempenho;
//    }
    public String getPorcentagemDisponivel() {
        String desempenho = "";
        for (OSFileStore p : sisArray) {
            boolean discoNull = "".equals(p.getType());
            long utilizavel = p.getUsableSpace();
            if (utilizavel!=0 && !discoNull) {
                Double porcentagem = (100d * utilizavel) / p.getTotalSpace();              
                desempenho = String.format(" %s %.0f%% %n", p.getMount(), porcentagem);
              
            }
        }
        return desempenho;
    }

    public String getPorcentagemOcupada() {
        String desempenho = "";
        for (OSFileStore p : sisArray) {
            boolean discoNull = "".equals(p.getType());
            long utilizavel = p.getUsableSpace();
            if (utilizavel!=0 && !discoNull) {
                Double dispo = (100d * utilizavel) / p.getTotalSpace();
                Double porcentagem = 100d - dispo;
                desempenho = String.format("%.0f%%", porcentagem);
            }
        }
        return desempenho;
    }

    public String getFilesVolume() {
        String volume = "";
        for (OSFileStore v : sisArray) {
            boolean discoNull = "".equals(v.getType());
            if (!discoNull) {
                volume = String.format(" %s %s\n", v.getMount(), v.getVolume()).replaceAll("\\\\", "").replaceAll("\\?", "");
            }
        }
        return volume;
    }

//    public List<String> getFilesVolume() {
//        List<String> valumesList = new ArrayList<String>();
//        for (OSFileStore v : sisArray) {
//            String volume;
//            volume = String.format("%s %s", v.getMount(), v.getVolume()).replaceAll("\\\\", "").replaceAll("\\?", "");
//            valumesList.add("\n" + volume);
//        }
//        return valumesList;
//    }
    private void hardDisk() {
        for (HWDiskStore disk : hd) {
            boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
            System.out.format(" Size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms%n",
                    disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
                    readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
                    readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
                    readwrite ? disk.getTransferTime() : "?");
            HWPartition[] partitions = disk.getPartitions();

            for (HWPartition part : partitions) {
                System.out.format(" |-- %s: %s (%s) Maj:Min=%d:%d, size: %s%s%n", part.getIdentification(),
                        part.getName(), part.getType(), part.getMajor(), part.getMinor(),
                        FormatUtil.formatBytesDecimal(part.getSize()),
                        part.getMountPoint().isEmpty() ? "" : " @ " + part.getMountPoint());
            }
        }
    }

    private void fileSystem() {
        System.out.println("File System:");

        System.out.format(" File Descriptors: %d/%d%n", sistemaArquivo.getOpenFileDescriptors(),
                sistemaArquivo.getMaxFileDescriptors());

        OSFileStore[] fsArray = sistemaArquivo.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            System.out.format(" %s (%s) [%s] %s of %s free (%.1f%%) is %s "
                    + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
                    + " and is mounted at %s%n", fs.getName(),
                    fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                    fs.getVolume(), fs.getLogicalVolume(), fs.getMount());
        }
    }

}
