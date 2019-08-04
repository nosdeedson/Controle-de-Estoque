package br.edson.helper;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GuardedObject;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.JOptionPane;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.edson.model.Produto;

public class GerarRelatorioProdutoPDF {
	
	public static void gerarRelatorioProdutos(List<Produto> listaProdutos, String nomeResponsavel) {
		Document doc = new Document();
		String arquivoPdf= " Quantidades produtos";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
			doc.open();
			Paragraph p = new Paragraph("Quantidade Produtos Retirados.");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);
			PdfPTable tabela = new PdfPTable(2);
			PdfPCell celula1 = new PdfPCell( new Paragraph("Nome"));
			PdfPCell celula2 = new PdfPCell(new Paragraph("Quantidade"));
			
			tabela.addCell(celula1);
			tabela.addCell(celula2);
			for (Produto element : listaProdutos) {
				celula1 = new PdfPCell( new Paragraph(element.getNome()));
				celula2 = new PdfPCell(new Paragraph( Integer.toString(element.getQuantidade())));
								
				tabela.addCell(celula1);
				tabela.addCell(celula2);
			}
			doc.add(tabela);
			for( int i = 0; i < 3; i++) {
				p = new Paragraph(" ");
				doc.add(p);
			}
			p = new Paragraph("___________________________");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph(nomeResponsavel);
			p.setAlignment(1);
			doc.add(p);
			doc.close();
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + "\nFeche o PDF aberto.");
		}
		
	}
	
	public static void gerarRelatorioProdutos(List<Produto> listaProdutos) {
		Document doc = new Document();
		String arquivoPdf= " Quantidades produtos";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
			doc.open();
			Paragraph p = new Paragraph("Quantidade Produtos");
			p.setAlignment(1);
			doc.add(p);
			p = new Paragraph(" ");
			doc.add(p);
			PdfPTable tabela = new PdfPTable(2);
			PdfPCell celula1 = new PdfPCell( new Paragraph("Nome"));
			PdfPCell celula2 = new PdfPCell(new Paragraph("Quantidade"));
			tabela.addCell(celula1);
			tabela.addCell(celula2);
			
			for (Produto element : listaProdutos) {
				celula1 = new PdfPCell( new Paragraph(element.getNome()));
				celula2 = new PdfPCell(new Paragraph( Integer.toString(element.getQuantidade())));
				
				tabela.addCell(celula1);
				tabela.addCell(celula2);
			
			}
			doc.add(tabela);
			doc.close();
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage() + "\nFeche o PDF aberto.");
		}
		
	}
}// fim classe
