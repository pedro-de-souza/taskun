/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oshente;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

/**
 *
 * @author pedro
 */
public class Taskun extends javax.swing.JFrame {

    /**
     * Creates new form Tela
     */
    public Taskun() {
        initComponents();
        setResizable(false);

        OS os = new OS();
        CPU cpu = new CPU();
        RAM ram = new RAM();
        HD hd = new HD();
        GPU gpu = new GPU();

//        Conexao conectBanco = new Conexao();
        LocalDateTime dataHora = LocalDateTime.now();

        lblTempoAtividade.setText(os.getUptime());
        lblCPUDesempenho.setText(cpu.getDesempenho() + " %");
        lblRAMDesempenho.setText(ram.getDesempenhoMemoria() + " %");
        lblGPUDesempenho.setText(gpu.getDesempenho() + " %");
        lblHDOcupado.setText(hd.getPorcentagemOcupada() + " %");
        lblTempoTransferencia.setText(hd.getTempoTransferencia(os.hal.getDiskStores()) + " segundos");
        lblThreands.setText(cpu.getThreads());
        lblProcesses.setText(cpu.getProcesses());

        lblCPUDesempenho.setForeground(color(Integer.parseInt(cpu.getDesempenho()), "CPU"));
        lblRAMDesempenho.setForeground(color(Integer.parseInt(ram.getDesempenhoMemoria()), "RAM"));
        lblHDOcupado.setForeground(color(Integer.parseInt(hd.getPorcentagemOcupada()), "HD"));
        lblGPUDesempenho.setForeground(color(Integer.parseInt(gpu.getDesempenho()), "GPU"));

        lblDiscoRigido.setText(hd.getNomeModelo());
        Integer valueDisk = Integer.parseInt(hd.getPorcentagemOcupada());
        prgDisco.setValue(valueDisk);

        //Sistema Operacional
        lblSistema.setText(os.getOS());
        lblSistemBit.setText(String.format(" Sistema operacional de %s bits", os.getBitness()));

        //CPU
        lblCPUName.setText(cpu.getProcessador());

        //RAM
        lblRAMTotal.setText(ram.getTotalMemoria() + " GB");
        lblRAMUtilizavel.setText(ram.getUtilizavel() + " GB");
        lblRAMDisponivel.setText(ram.getDisponivel() + " GB");
        lblRAMTipo.setText(ram.getTipoMemoria());
        lblRAMClock.setText(ram.getClockSpeed() + " GHz");

        //HD
        lblDisco.setText(hd.getDiscoAlocado());
        lblDiscoTipo.setText(hd.getTipoSADisco());

        lblDiscoUtilizavel.setText(hd.getDiscosTotalUtilizavel() + " GB");
        lblDiscoDisponivel.setText(hd.getDiscosTotalDisponivel() + " GB");

        //GPU
        lblPlacaDeVideo.setText(gpu.getNomePlaca());
        lblVersaoGPU.setText(gpu.getVersaoGPU());
        lblMemoriaGPU.setText(gpu.getRAM() + " GB");

        ActionListener action = (ActionEvent actionEvent) -> {
//            System.out.println("Processando...");

            lblTempoAtividade.setText(os.getUptime());
            lblCPUDesempenho.setText(cpu.getDesempenho() + " %");
            lblRAMDisponivel.setText(ram.getDisponivel() + " GB");
            lblRAMDesempenho.setText(ram.getDesempenhoMemoria() + " %");
            lblHDOcupado.setText(hd.getPorcentagemOcupada() + " %");
            lblGPUDesempenho.setText(gpu.getDesempenho() + " %");
            lblTempoTransferencia.setText(hd.getTempoTransferencia(os.hal.getDiskStores()) + " segundos");
            lblThreands.setText(cpu.getThreads());
            lblProcesses.setText(cpu.getProcesses());

            lblCPUDesempenho.setForeground(color(Integer.parseInt(cpu.getDesempenho()), "CPU"));
            lblRAMDesempenho.setForeground(color(Integer.parseInt(ram.getDesempenhoMemoria()), "RAM"));
            lblHDOcupado.setForeground(color(Integer.parseInt(hd.getPorcentagemOcupada()), "HD"));
            lblGPUDesempenho.setForeground(color(Integer.parseInt(gpu.getDesempenho()), "GPU"));
        };

        Timer timer = new Timer(1000, action);
        timer.start();
        //Enviar os dados da maquina para o Banco
        //O verificarDadoTabela retorna um boolean, se o nome da maquina ja tiver cadastrado no banco
        //ele retorna True, e atualiza o banco caso qualquer hardware tenha sido trocado
//        if (conectBanco.verificarDadoTabela()) {
//            conectBanco.atualizarDadoTblMaquinas();//Atualiza o banco
//        } else {
//            conectBanco.addDadoTblMaquinas();//Adciona nova maquina e seus dados ao banco
//        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        jScrollBar1 = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblDisco = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblRAMTotal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblSistema = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblRAMUtilizavel = new javax.swing.JLabel();
        lblCPUName = new javax.swing.JLabel();
        lblRAMTipo = new javax.swing.JLabel();
        lblDiscoTipo = new javax.swing.JLabel();
        lblSistemBit = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblRAMDisponivel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblRAMClock = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblCPUDesempenho = new javax.swing.JLabel();
        lblRAMDesempenho = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblTempoAtividade = new javax.swing.JLabel();
        lblDiscoRigido = new javax.swing.JLabel();
        lblThreands = new javax.swing.JLabel();
        lblP = new javax.swing.JLabel();
        lblProcesses = new javax.swing.JLabel();
        prgDisco = new javax.swing.JProgressBar();
        lblHDOcupado = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblT1 = new javax.swing.JLabel();
        lblDiscoDisponivel = new javax.swing.JLabel();
        lblP1 = new javax.swing.JLabel();
        lblTempoTransferencia = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblDiscoUtilizavel = new javax.swing.JLabel();
        lblP2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblGPUDesempenho = new javax.swing.JLabel();
        lblP3 = new javax.swing.JLabel();
        lblPlacaDeVideo = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lblVersaoGPU = new javax.swing.JLabel();
        lblMemoriaGPU = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        rbtAlertSistema = new javax.swing.JRadioButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));

        jPanel1.setBackground(new java.awt.Color(44, 43, 43));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 160, 35, 14);

        lblDisco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDisco.setForeground(new java.awt.Color(255, 255, 255));
        lblDisco.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDisco.setText("_________");
        lblDisco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDisco.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(lblDisco);
        lblDisco.setBounds(500, 270, 120, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(87, 199, 186));
        jLabel7.setText("RAM _______________________________________________________");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 190, 414, 14);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Memória:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 220, 53, 14);

        lblRAMTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMTotal.setText("___________");
        jPanel1.add(lblRAMTotal);
        lblRAMTotal.setBounds(80, 220, 55, 16);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Utilizável:");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(20, 240, 55, 14);

        lblSistema.setForeground(new java.awt.Color(255, 255, 255));
        lblSistema.setText("____________________________");
        jPanel1.add(lblSistema);
        lblSistema.setBounds(150, 80, 140, 16);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(66, 142, 105));
        jLabel9.setText("Disco Rígido__________________________________________________");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 310, 418, 14);

        lblRAMUtilizavel.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMUtilizavel.setText("___________");
        jPanel1.add(lblRAMUtilizavel);
        lblRAMUtilizavel.setBounds(80, 240, 55, 16);

        lblCPUName.setForeground(new java.awt.Color(255, 255, 255));
        lblCPUName.setText("________________________________________");
        jPanel1.add(lblCPUName);
        lblCPUName.setBounds(70, 160, 200, 16);

        lblRAMTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMTipo.setText("__________");
        jPanel1.add(lblRAMTipo);
        lblRAMTipo.setBounds(60, 260, 50, 16);

        lblDiscoTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiscoTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoTipo.setText("_____");
        jPanel1.add(lblDiscoTipo);
        lblDiscoTipo.setBounds(630, 270, 40, 17);

        lblSistemBit.setForeground(new java.awt.Color(255, 255, 255));
        lblSistemBit.setText("_____");
        jPanel1.add(lblSistemBit);
        lblSistemBit.setBounds(120, 100, 200, 16);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tipo:");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 260, 27, 14);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sistema Operacional:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 80, 119, 14);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Disponível: ");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(160, 240, 64, 14);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(170, 64, 234));
        jLabel2.setText("Sistema ____________________________________________________");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 413, 14);

        lblRAMDisponivel.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMDisponivel.setText("__________");
        jPanel1.add(lblRAMDisponivel);
        lblRAMDisponivel.setBounds(230, 240, 50, 16);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo de sistema:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 100, 92, 14);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Velocidade:");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 280, 64, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(86, 148, 221));
        jLabel4.setText("Processador _________________________________________________");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 140, 416, 14);

        lblRAMClock.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMClock.setText("__________");
        jPanel1.add(lblRAMClock);
        lblRAMClock.setBounds(90, 280, 50, 16);

        jLabel5.setBackground(new java.awt.Color(228, 87, 12));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CPU");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(500, 170, 42, 17);

        lblCPUDesempenho.setBackground(new java.awt.Color(255, 255, 255));
        lblCPUDesempenho.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblCPUDesempenho.setForeground(new java.awt.Color(255, 255, 255));
        lblCPUDesempenho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCPUDesempenho.setText("10%");
        lblCPUDesempenho.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblCPUDesempenho);
        lblCPUDesempenho.setBounds(500, 140, 60, 25);

        lblRAMDesempenho.setBackground(new java.awt.Color(228, 87, 12));
        lblRAMDesempenho.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblRAMDesempenho.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMDesempenho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRAMDesempenho.setText("10%");
        jPanel1.add(lblRAMDesempenho);
        lblRAMDesempenho.setBounds(610, 140, 70, 25);

        jLabel13.setBackground(new java.awt.Color(228, 87, 12));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("RAM");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(620, 170, 42, 17);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tempo de atividade");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(640, 50, 135, 17);

        lblTempoAtividade.setFont(new java.awt.Font("Consolas", 0, 28)); // NOI18N
        lblTempoAtividade.setForeground(new java.awt.Color(255, 255, 255));
        lblTempoAtividade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTempoAtividade.setText("__:__:__:__");
        jPanel1.add(lblTempoAtividade);
        lblTempoAtividade.setBounds(490, 90, 430, 33);

        lblDiscoRigido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDiscoRigido.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoRigido.setText("________________________");
        jPanel1.add(lblDiscoRigido);
        lblDiscoRigido.setBounds(20, 330, 168, 14);

        lblThreands.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThreands.setForeground(new java.awt.Color(255, 255, 255));
        lblThreands.setText("_______");
        jPanel1.add(lblThreands);
        lblThreands.setBounds(580, 230, 56, 17);

        lblP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP.setForeground(new java.awt.Color(255, 255, 255));
        lblP.setText("Processos:");
        jPanel1.add(lblP);
        lblP.setBounds(690, 230, 74, 17);

        lblProcesses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblProcesses.setForeground(new java.awt.Color(255, 255, 255));
        lblProcesses.setText("_______");
        jPanel1.add(lblProcesses);
        lblProcesses.setBounds(790, 230, 56, 17);

        prgDisco.setForeground(new java.awt.Color(107, 209, 77));
        prgDisco.setValue(50);
        jPanel1.add(prgDisco);
        prgDisco.setBounds(500, 350, 403, 40);

        lblHDOcupado.setBackground(new java.awt.Color(228, 87, 12));
        lblHDOcupado.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblHDOcupado.setForeground(new java.awt.Color(255, 255, 255));
        lblHDOcupado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHDOcupado.setText("10%");
        jPanel1.add(lblHDOcupado);
        lblHDOcupado.setBounds(720, 140, 70, 25);

        jLabel16.setBackground(new java.awt.Color(228, 87, 12));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("HD");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(730, 170, 42, 17);

        lblT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblT1.setForeground(new java.awt.Color(255, 255, 255));
        lblT1.setText("Threands:");
        jPanel1.add(lblT1);
        lblT1.setBounds(500, 230, 70, 17);

        lblDiscoDisponivel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiscoDisponivel.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoDisponivel.setText("______");
        jPanel1.add(lblDiscoDisponivel);
        lblDiscoDisponivel.setBounds(730, 410, 48, 17);

        lblP1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP1.setForeground(new java.awt.Color(255, 255, 255));
        lblP1.setText("Tempo de transferência:");
        jPanel1.add(lblP1);
        lblP1.setBounds(500, 310, 168, 17);

        lblTempoTransferencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTempoTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        lblTempoTransferencia.setText("_______");
        jPanel1.add(lblTempoTransferencia);
        lblTempoTransferencia.setBounds(690, 310, 120, 20);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(107, 209, 77));
        jLabel17.setText("Placa de vídeo________________________________________________");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 360, 417, 14);

        lblDiscoUtilizavel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiscoUtilizavel.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoUtilizavel.setText("______");
        jPanel1.add(lblDiscoUtilizavel);
        lblDiscoUtilizavel.setBounds(570, 410, 48, 17);

        lblP2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP2.setForeground(new java.awt.Color(255, 255, 255));
        lblP2.setText("Utilizável:");
        jPanel1.add(lblP2);
        lblP2.setBounds(500, 410, 64, 17);

        jLabel18.setBackground(new java.awt.Color(228, 87, 12));
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("GPU");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(830, 170, 70, 17);

        lblGPUDesempenho.setBackground(new java.awt.Color(228, 87, 12));
        lblGPUDesempenho.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblGPUDesempenho.setForeground(new java.awt.Color(255, 255, 255));
        lblGPUDesempenho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGPUDesempenho.setText("10%");
        jPanel1.add(lblGPUDesempenho);
        lblGPUDesempenho.setBounds(830, 140, 80, 25);

        lblP3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP3.setForeground(new java.awt.Color(255, 255, 255));
        lblP3.setText("Disponível:");
        jPanel1.add(lblP3);
        lblP3.setBounds(640, 410, 74, 17);

        lblPlacaDeVideo.setForeground(new java.awt.Color(255, 255, 255));
        lblPlacaDeVideo.setText("________________________");
        jPanel1.add(lblPlacaDeVideo);
        lblPlacaDeVideo.setBounds(80, 380, 120, 16);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Versão:");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(20, 400, 42, 14);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Memória  disponivível: ");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(20, 420, 129, 14);

        lblVersaoGPU.setForeground(new java.awt.Color(255, 255, 255));
        lblVersaoGPU.setText("__________");
        jPanel1.add(lblVersaoGPU);
        lblVersaoGPU.setBounds(70, 400, 90, 16);

        lblMemoriaGPU.setForeground(new java.awt.Color(255, 255, 255));
        lblMemoriaGPU.setText("__________");
        jPanel1.add(lblMemoriaGPU);
        lblMemoriaGPU.setBounds(160, 420, 50, 16);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Modelo:");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(20, 380, 44, 14);

        jSeparator2.setBackground(new java.awt.Color(65, 65, 65));
        jSeparator2.setForeground(new java.awt.Color(65, 65, 65));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(460, 60, 10, 390);

        rbtAlertSistema.setBackground(new java.awt.Color(44, 43, 43));
        rbtAlertSistema.setForeground(new java.awt.Color(255, 255, 255));
        rbtAlertSistema.setBorder(null);
        rbtAlertSistema.setBorderPainted(true);
        rbtAlertSistema.setContentAreaFilled(false);
        rbtAlertSistema.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rbtAlertSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAlertSistemaActionPerformed(evt);
            }
        });
        jPanel1.add(rbtAlertSistema);
        rbtAlertSistema.setBounds(880, 430, 20, 30);

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Alerta do sistema:");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(760, 430, 130, 30);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Taskun");
        jPanel1.add(jLabel23);
        jLabel23.setBounds(0, 10, 930, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtAlertSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAlertSistemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtAlertSistemaActionPerformed

    /**
     * @param args the command line arguments
     */
    public void alerta(String componete) {
        OperatingSystem sistema = new SystemInfo().getOperatingSystem();
        if (rbtAlertSistema.isSelected()) {
            if (!componete.equals("HD")) {
                if (!sistema.getFamily().equals("Windows")) {
                    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imgs/icon.png"));

                    TrayIcon trayIcon = new TrayIcon(image);
                    if (SystemTray.isSupported()) {
                        SystemTray tray = SystemTray.getSystemTray();

                        try {
                            tray.add(trayIcon);
                            trayIcon.displayMessage("Sistema Taskun alerta", "O desempenho da "+componete+" está muito alto", TrayIcon.MessageType.NONE);
                        } catch (AWTException e) {
                            System.err.println(e);
                        }
                    }
                } else {
                    UIManager UI = new UIManager();
                    UI.put("OptionPane.background", new Color(232, 232, 232));
                    UI.put("Panel.background", new Color(232, 232, 232));
                    UI.put("OptionPane.border", BorderFactory.createMatteBorder(5, 15, 10, 7, new Color(232, 232, 232)));
                    UI.put("OptionPane.messageAreaBorder", BorderFactory.createMatteBorder(7, 7, 1, 7, new Color(232, 232, 232)));

                    JLabel message = new JLabel("<html>O desempenho da <b>" + componete + "</b> está muito alto <br></html>");
                    message.setBounds(10, 10, 470, 30);
                    message.setForeground(Color.black);
                    message.setFont(new Font("Arial", Font.PLAIN, 15));

                    ImageIcon icon = new ImageIcon(getToolkit().createImage(getClass().getResource("/imgs/alert.png")));
                    final JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, icon, new Object[]{}, null);
                    final JDialog d = pane.createDialog((JFrame) null, "Sistema Taskun alerta");

                    Timer timer = new Timer(5000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            d.setVisible(false);
                            d.dispose();
                        }
                    });
                    timer.start();
                    d.setLocation(1035, 50);
                    d.setAlwaysOnTop(false);
                    d.setModal(false);
                    d.setVisible(true);
                }
            }
        }
    }

    public Color color(Integer desempenho, String componete) {
        if (desempenho >= 0 && desempenho <= 25) {
            return new Color(23, 173, 0);
        } else if (desempenho > 25 && desempenho <= 50) {
            return new Color(245, 184, 0);
        } else if (desempenho > 50 && desempenho <= 75) {
            return new Color(255, 93, 5);
        } else {
            alerta(componete);
            return new Color(192, 22, 31);
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Taskun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Taskun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Taskun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Taskun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Taskun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblCPUDesempenho;
    private javax.swing.JLabel lblCPUName;
    private javax.swing.JLabel lblDisco;
    private javax.swing.JLabel lblDiscoDisponivel;
    private javax.swing.JLabel lblDiscoRigido;
    private javax.swing.JLabel lblDiscoTipo;
    private javax.swing.JLabel lblDiscoUtilizavel;
    private javax.swing.JLabel lblGPUDesempenho;
    private javax.swing.JLabel lblHDOcupado;
    private javax.swing.JLabel lblMemoriaGPU;
    private javax.swing.JLabel lblP;
    private javax.swing.JLabel lblP1;
    private javax.swing.JLabel lblP2;
    private javax.swing.JLabel lblP3;
    private javax.swing.JLabel lblPlacaDeVideo;
    private javax.swing.JLabel lblProcesses;
    private javax.swing.JLabel lblRAMClock;
    private javax.swing.JLabel lblRAMDesempenho;
    private javax.swing.JLabel lblRAMDisponivel;
    private javax.swing.JLabel lblRAMTipo;
    private javax.swing.JLabel lblRAMTotal;
    private javax.swing.JLabel lblRAMUtilizavel;
    private javax.swing.JLabel lblSistemBit;
    private javax.swing.JLabel lblSistema;
    private javax.swing.JLabel lblT1;
    private javax.swing.JLabel lblTempoAtividade;
    private javax.swing.JLabel lblTempoTransferencia;
    private javax.swing.JLabel lblThreands;
    private javax.swing.JLabel lblVersaoGPU;
    private javax.swing.JProgressBar prgDisco;
    private javax.swing.JRadioButton rbtAlertSistema;
    // End of variables declaration//GEN-END:variables
}
