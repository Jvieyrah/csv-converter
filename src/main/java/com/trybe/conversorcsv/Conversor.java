package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Função utilizada apenas para validação da solução do desafio.
 *
 * @param args Não utilizado.
 * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os
 *         arquivos de saída.
 */
public class Conversor {
  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os
   *         arquivos de saída.
   */
  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");
    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  /**
   * Converte todos os arquivos CSV da pasta de entradas. Os resultados são gerados na pasta de
   * saídas, deixando os arquivos originais inalterados.
   *
   * @param pastaDeEntradas Pasta contendo os arquivos CSV gerados pela página web.
   * @param pastaDeSaidas Pasta em que serão colocados os arquivos gerados no formato requerido pelo
   *        subsistema.
   *
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os
   *         arquivos de saída.
   */
  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {
    // TODO: Implementar.
    try {
      if (pastaDeEntradas.isDirectory() && pastaDeEntradas.canRead()) {
        for (File arquivo : pastaDeEntradas.listFiles()) {
          fileAdapter(arquivo, pastaDeSaidas);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Função utilizada apenas para validação da solução do desafio. param args Não utilizado. *throws
   * IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os arquivos de
   * saída.
   */
  public String letraMiuscula(String campo) {
    if (campo.contentEquals("Nome completo")) {
      return campo;
    } else {
      return campo.toUpperCase();
    }
  }

  /**
   * Função utilizada apenas para validação da solução do desafio. param args Não utilizado. throws
   * IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os arquivos de
   * saída.
   */
  public String formadorData(String data) {
    if (data.contentEquals("Data de nascimento")) {
      return data;
    } else {
      String[] dismountData = data.split("/");
      return dismountData[2] + "-" + dismountData[1] + "-" + dismountData[0];
    }
  }

  /**
   * Função utilizada apenas para validação da solução do desafio. param args Não utilizado. throws
   * IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os arquivos de
   * saída.
   */
  public String cpfFormat(String numeros) {
    if (numeros.contentEquals("CPF")) {
      return numeros;
    } else {
      String parte = numeros.substring(0, 3) + "." + numeros.substring(3);
      String parte2 = parte.substring(0, 7) + "." + parte.substring(7);
      String parte3 =
          parte2.substring(0, parte2.length() - 2) + "-" + parte2.substring(parte2.length() - 2);
      return parte3;
    }
  }

  /**
   * Função utilizada apenas para validação da solução do desafio. param args Não utilizado. throws
   * IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os arquivos de
   * saída.
   */
  public void escreverString(String linha, File arquivo) throws IOException {
    FileWriter escritorArquivo = null;
    BufferedWriter bufferedEscritor = null;
    try {
      escritorArquivo = new FileWriter(arquivo, true); // Use FileWriter constructor with "append"
      bufferedEscritor = new BufferedWriter(escritorArquivo);
      System.out.println(linha);
      bufferedEscritor.write(linha);
      bufferedEscritor.write("\n"); // Add a new line after writing the current line
      bufferedEscritor.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (escritorArquivo != null) {
        escritorArquivo.close();
      }
      if (bufferedEscritor != null) {
        bufferedEscritor.close();
      }
    }
  }

  /**
   * Função utilizada apenas para validação da solução do desafio. param args Não utilizado. throws
   * IOException Caso ocorra algum problema ao ler os arquivos de entrada ou gravar os arquivos de
   * saída.
   */
  public void fileAdapter(File arquivo, File pastaDeSaidas) throws IOException {
    FileReader leitorArquivo = null;
    BufferedReader bufferedLeitor = null;
    File pastaSaida = new File(pastaDeSaidas.getAbsolutePath());
    pastaSaida.mkdirs();

    File arquivoDeEscrita = new File(pastaSaida, arquivo.getName());

    try {
      leitorArquivo = new FileReader(arquivo);
      bufferedLeitor = new BufferedReader(leitorArquivo);

      String conteudoLinha = bufferedLeitor.readLine();
      while (conteudoLinha != null) {
        StringBuilder returnedLine = new StringBuilder();
        String[] campos = conteudoLinha.split(",");
        for (int i = 0; i < campos.length; i++) {

          String toBeReturned;
          switch (i) {
            case 0:
              toBeReturned = letraMiuscula(campos[i]) + ",";
              break;
            case 1:
              toBeReturned = formadorData(campos[i]) + ",";
              break;
            case 3:
              toBeReturned = cpfFormat(campos[i]);
              break;
            default:
              toBeReturned = campos[i] + ",";
              break;
          }
          returnedLine.append(toBeReturned);
        }
        escreverString(returnedLine.toString(), arquivoDeEscrita);
        conteudoLinha = bufferedLeitor.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      leitorArquivo.close();
      bufferedLeitor.close();
    }
  }
}


