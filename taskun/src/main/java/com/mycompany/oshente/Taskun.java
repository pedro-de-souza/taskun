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

        OperatingSystem sistema = new SystemInfo().getOperatingSystem();
        OS os = new OS();
        CPU cpu = new CPU();
        RAM ram = new RAM();
        HD hd = new HD();
        GPU gpu = new GPU();

        Conexao conectBanco = new Conexao();
        LocalDateTime dataHora = LocalDateTime.now();
        
        

        lblTempoAtividade.setText(os.getUptime());
        lblCPUDesempenho.setText(cpu.getDesempenho() + " %");
        lblRAMDesempenho.setText(ram.getDesempenhoMemoria() + " %");

        lblHDOcupado.setText(hd.getPorcentagemOcupada() + " %");
        lblTempoTransferencia.setText(hd.getTempoTransferencia(os.hal.getDiskStores()) + " segundos");
        lblThreands.setText(cpu.getThreads());
        lblProcesses.setText(cpu.getProcesses());

        lblCPUDesempenho.setForeground(color(Integer.parseInt(cpu.getDesempenho()), "CPU"));
        lblRAMDesempenho.setForeground(color(Integer.parseInt(ram.getDesempenhoMemoria()), "RAM"));
        lblHDOcupado.setForeground(color(Integer.parseInt(hd.getPorcentagemOcupada()), "HD"));
        if (sistema.getFamily().equals("Windows")) {
            lblGPUDesempenho.setForeground(color(Integer.parseInt(gpu.getDesempenho()), "GPU"));
            lblGPUDesempenho.setText(gpu.getDesempenho() + " %");
        } else {
            lblGPUDesempenho.setForeground(color(0, "GPU"));
            lblGPUDesempenho.setText("0 %");
        }

        lblDiscoRigido.setText(hd.getNomeModelo());
        Integer valueDisk = Integer.parseInt(hd.getPorcentagemOcupada());
        prgDisco.setValue(valueDisk);

        //Sistema Operacional
        lblSistema.setText(os.getOS());
        lblSistemBit.setText(String.format("%s bits", os.getBitness()));

        //CPU
        lblCPUName.setText(cpu.getProcessador());

        //RAM
        lblRAMTotal.setText(ram.getTotalMemoria() + " GB");
        lblRAMUtilizavel.setText(ram.getUtilizavel() + " GB");
        lblRAMDisponivel.setText(ram.getDisponivel() + " GB");
        try {
            lblRAMTipo.setText(ram.getTipoMemoria());
            lblRAMClock.setText(ram.getClockSpeed() + " GHz");
        } catch (ArrayIndexOutOfBoundsException ex) {
            lblRAMTipo.setText("unknown");
            lblRAMClock.setText("unknown");
        }

        //HD
        lblDisco.setText(hd.getDiscoAlocado());
        lblDiscoTipo.setText(hd.getTipoSADisco());

        lblDiscoUtilizavel.setText(hd.getDiscosTotalUtilizavel() + " GB");
        lblDiscoDisponivel.setText(hd.getDiscosTotalDisponivel() + " GB");

        //GPU
        lblPlacaDeVideo.setText(gpu.getNomePlaca());
        if (sistema.getFamily().equals("Windows")) {
            lblVersaoGPU.setText(gpu.getVersaoGPU());
            lblMemoriaGPU.setText(gpu.getRAM() + " GB");
        } else {
            lblVersaoGPU.setText(gpu.getVersaoGPU());
            lblMemoriaGPU.setText("unknown");
        }

        ActionListener action = (ActionEvent actionEvent) -> {
//            System.out.println("Processando...");

            lblTempoAtividade.setText(os.getUptime());
            lblCPUDesempenho.setText(cpu.getDesempenho() + " %");
            lblRAMDisponivel.setText(ram.getDisponivel() + " GB");
            lblRAMDesempenho.setText(ram.getDesempenhoMemoria() + " %");
            lblHDOcupado.setText(hd.getPorcentagemOcupada() + " %");
            lblTempoTransferencia.setText(hd.getTempoTransferencia(os.hal.getDiskStores()) + " segundos");
            lblThreands.setText(cpu.getThreads());
            lblProcesses.setText(cpu.getProcesses());
            if (sistema.getFamily().equals("Windows")) {
                lblGPUDesempenho.setText(gpu.getDesempenho() + " %");
            } else {
                lblGPUDesempenho.setText("0 %");
            }

            lblCPUDesempenho.setForeground(color(Integer.parseInt(cpu.getDesempenho()), "CPU"));
            lblRAMDesempenho.setForeground(color(Integer.parseInt(ram.getDesempenhoMemoria()), "RAM"));
            lblHDOcupado.setForeground(color(Integer.parseInt(hd.getPorcentagemOcupada()), "HD"));
            if (sistema.getFamily().equals("Windows")) {
                lblGPUDesempenho.setForeground(color(Integer.parseInt(gpu.getDesempenho()), "GPU"));
            } else {
                lblGPUDesempenho.setForeground(color(0, "GPU"));
            }
        };

        Timer timer = new Timer(1000, action);
        timer.start();
        //Enviar os dados da maquina para o Banco
        //O verificarDadoTabela retorna um boolean, se o nome da maquina ja tiver cadastrado no banco
        //ele retorna True, e atualiza o banco caso qualquer hardware tenha sido trocado
        if (conectBanco.verificarDadoTabela()) {
            conectBanco.atualizarDadoTblMaquinas();//Atualiza o banco
        } else {
            conectBanco.addDadoTblMaquinas();//Adciona nova maquina e seus dados ao banco
        }

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome:");

        lblDisco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDisco.setForeground(new java.awt.Color(255, 255, 255));
        lblDisco.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDisco.setText("_________");
        lblDisco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblDisco.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(87, 199, 186));
        jLabel7.setText("RAM _______________________________________________________");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Memória:");

        lblRAMTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMTotal.setText("___________");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Utilizável:");

        lblSistema.setForeground(new java.awt.Color(255, 255, 255));
        lblSistema.setText("____________________________");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(66, 142, 105));
        jLabel9.setText("Disco Rígido__________________________________________________");

        lblRAMUtilizavel.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMUtilizavel.setText("___________");

        lblCPUName.setForeground(new java.awt.Color(255, 255, 255));
        lblCPUName.setText("________________________________________");

        lblRAMTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMTipo.setText("__________");

        lblDiscoTipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiscoTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoTipo.setText("_____");

        lblSistemBit.setForeground(new java.awt.Color(255, 255, 255));
        lblSistemBit.setText("_____");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tipo:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sistema Operacional:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Disponível: ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(170, 64, 234));
        jLabel2.setText("Sistema ____________________________________________________");

        lblRAMDisponivel.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMDisponivel.setText("__________");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tipo de sistema:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Velocidade:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(86, 148, 221));
        jLabel4.setText("Processador _________________________________________________");

        lblRAMClock.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMClock.setText("__________");

        jLabel5.setBackground(new java.awt.Color(228, 87, 12));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CPU");

        lblCPUDesempenho.setBackground(new java.awt.Color(255, 255, 255));
        lblCPUDesempenho.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblCPUDesempenho.setForeground(new java.awt.Color(255, 255, 255));
        lblCPUDesempenho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCPUDesempenho.setText("10%");
        lblCPUDesempenho.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblRAMDesempenho.setBackground(new java.awt.Color(228, 87, 12));
        lblRAMDesempenho.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblRAMDesempenho.setForeground(new java.awt.Color(255, 255, 255));
        lblRAMDesempenho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRAMDesempenho.setText("10%");

        jLabel13.setBackground(new java.awt.Color(228, 87, 12));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("RAM");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tempo de atividade");

        lblTempoAtividade.setFont(new java.awt.Font("Consolas", 0, 28)); // NOI18N
        lblTempoAtividade.setForeground(new java.awt.Color(255, 255, 255));
        lblTempoAtividade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTempoAtividade.setText("__:__:__:__");

        lblDiscoRigido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDiscoRigido.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoRigido.setText("________________________");

        lblThreands.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblThreands.setForeground(new java.awt.Color(255, 255, 255));
        lblThreands.setText("_______");

        lblP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP.setForeground(new java.awt.Color(255, 255, 255));
        lblP.setText("Processos:");

        lblProcesses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblProcesses.setForeground(new java.awt.Color(255, 255, 255));
        lblProcesses.setText("_______");

        prgDisco.setForeground(new java.awt.Color(107, 209, 77));
        prgDisco.setValue(50);

        lblHDOcupado.setBackground(new java.awt.Color(228, 87, 12));
        lblHDOcupado.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblHDOcupado.setForeground(new java.awt.Color(255, 255, 255));
        lblHDOcupado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHDOcupado.setText("10%");

        jLabel16.setBackground(new java.awt.Color(228, 87, 12));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("HD");

        lblT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblT1.setForeground(new java.awt.Color(255, 255, 255));
        lblT1.setText("Threands:");

        lblDiscoDisponivel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiscoDisponivel.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoDisponivel.setText("______");

        lblP1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP1.setForeground(new java.awt.Color(255, 255, 255));
        lblP1.setText("Tempo de transferência:");

        lblTempoTransferencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTempoTransferencia.setForeground(new java.awt.Color(255, 255, 255));
        lblTempoTransferencia.setText("_______");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(107, 209, 77));
        jLabel17.setText("Placa de vídeo________________________________________________");

        lblDiscoUtilizavel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDiscoUtilizavel.setForeground(new java.awt.Color(255, 255, 255));
        lblDiscoUtilizavel.setText("______");

        lblP2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP2.setForeground(new java.awt.Color(255, 255, 255));
        lblP2.setText("Utilizável:");

        jLabel18.setBackground(new java.awt.Color(228, 87, 12));
        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("GPU");

        lblGPUDesempenho.setBackground(new java.awt.Color(228, 87, 12));
        lblGPUDesempenho.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblGPUDesempenho.setForeground(new java.awt.Color(255, 255, 255));
        lblGPUDesempenho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGPUDesempenho.setText("10%");

        lblP3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblP3.setForeground(new java.awt.Color(255, 255, 255));
        lblP3.setText("Disponível:");

        lblPlacaDeVideo.setForeground(new java.awt.Color(255, 255, 255));
        lblPlacaDeVideo.setText("________________________");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Versão:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Memória  disponivível: ");

        lblVersaoGPU.setForeground(new java.awt.Color(255, 255, 255));
        lblVersaoGPU.setText("__________");

        lblMemoriaGPU.setForeground(new java.awt.Color(255, 255, 255));
        lblMemoriaGPU.setText("__________");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Modelo:");

        jSeparator2.setBackground(new java.awt.Color(65, 65, 65));
        jSeparator2.setForeground(new java.awt.Color(65, 65, 65));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        rbtAlertSistema.setBackground(new java.awt.Color(102, 102, 102));
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

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Alerta do sistema:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Taskun");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(lblSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(lblSistemBit, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(lblCPUName))
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(33, 33, 33)
                        .addComponent(lblRAMTotal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(8, 8, 8)
                        .addComponent(lblRAMUtilizavel)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)
                        .addGap(6, 6, 6)
                        .addComponent(lblRAMDisponivel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRAMTipo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(16, 16, 16)
                        .addComponent(lblRAMClock))
                    .addComponent(jLabel9)
                    .addComponent(lblDiscoRigido)
                    .addComponent(jLabel17)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(9, 9, 9)
                        .addComponent(lblPlacaDeVideo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(29, 29, 29)
                        .addComponent(lblVersaoGPU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(12, 12, 12)
                        .addComponent(lblMemoriaGPU)))
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel15))
                    .addComponent(lblTempoAtividade, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCPUDesempenho, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblRAMDesempenho, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lblHDOcupado, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lblGPUDesempenho, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblDiscoTipo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(prgDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblP2)
                        .addGap(6, 6, 6)
                        .addComponent(lblDiscoUtilizavel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblP3)
                        .addGap(6, 6, 6)
                        .addComponent(lblDiscoDisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rbtAlertSistema))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lblP1)
                                .addGap(18, 18, 18)
                                .addComponent(lblTempoTransferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblT1)
                                .addGap(10, 10, 10)
                                .addComponent(lblThreands)
                                .addGap(64, 64, 64)
                                .addComponent(lblP)
                                .addGap(6, 6, 6)
                                .addComponent(lblProcesses))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1))
                            .addComponent(lblSistema))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel3))
                            .addComponent(lblSistemBit))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel6))
                            .addComponent(lblCPUName))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8))
                            .addComponent(lblRAMTotal))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRAMUtilizavel)
                            .addComponent(lblRAMDisponivel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel11))))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblRAMTipo))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(lblRAMClock))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addComponent(lblDiscoRigido)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel17)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(lblPlacaDeVideo))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel19))
                            .addComponent(lblVersaoGPU))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel20))
                            .addComponent(lblMemoriaGPU)))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(23, 23, 23)
                        .addComponent(lblTempoAtividade)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCPUDesempenho)
                            .addComponent(lblRAMDesempenho)
                            .addComponent(lblHDOcupado)
                            .addComponent(lblGPUDesempenho))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblT1)
                            .addComponent(lblThreands)
                            .addComponent(lblP)
                            .addComponent(lblProcesses))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDisco)
                            .addComponent(lblDiscoTipo))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblP1)
                            .addComponent(lblTempoTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(prgDisco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblP2)
                            .addComponent(lblDiscoUtilizavel)
                            .addComponent(lblP3)
                            .addComponent(lblDiscoDisponivel))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtAlertSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                if (sistema.getFamily().equals("Windows")) {
                    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png"));

                    TrayIcon trayIcon = new TrayIcon(image);
                    if (SystemTray.isSupported()) {
                        SystemTray tray = SystemTray.getSystemTray();

                        try {
                            tray.add(trayIcon);
                            trayIcon.displayMessage("Sistema Taskun alerta", "O desempenho da " + componete + " está muito alto", TrayIcon.MessageType.NONE);
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

                    ImageIcon icon = new ImageIcon(getToolkit().createImage(getClass().getResource("/img/alert.png")));
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
